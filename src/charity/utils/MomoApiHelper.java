package charity.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class MomoApiHelper {

    private static final String PARTNER_CODE = "MOMO4LG620250601";
    private static final String ACCESS_KEY = "LB2klcaIQxLuRFXy";
    private static final String SECRET_KEY = "ftATrLAywRiudzTYXoC1TdMIGpa1F3LV";
    private static final String QUERY_URL = "https://payment.momo.vn/v2/gateway/api/query";
    private static final String CREATE_URL = "https://payment.momo.vn/v2/gateway/api/create";

    //lop ket qua tra ve khi tao qr
    public static class MomoQRResult {

        public String qrCodeUrl;
        public String orderId;
        public String requestId;

        public MomoQRResult(String qrCodeUrl, String orderId, String requestId) {
            this.qrCodeUrl = qrCodeUrl;
            this.orderId = orderId;
            this.requestId = requestId;
        }

    }

    //tao ma qr
    public static MomoQRResult createQRCode(long amount, int eventId, int userId) {
        try {
            String orderId = "EV" + eventId + "_USER" + userId + System.currentTimeMillis();
            String requestId = "REQ" + System.currentTimeMillis();
            String orderInfo = "Quyen gop tu thien";
            String redirectUrl = "https://momo.vn"; // Có thể để tạm, test không cần đúng
            String ipnUrl = "https://momo.vn";
            String storeId = "MyCharity";
            String partnerName = "My Charity";
            String lang = "vi";
            boolean autoCapture = true;
            String requestType = "captureWallet";
            String extraData = "";

            String rawSignature = "accessKey=" + ACCESS_KEY
                    + "&amount=" + amount
                    + "&extraData=" + extraData
                    + "&ipnUrl=" + ipnUrl
                    + "&orderId=" + orderId
                    + "&orderInfo=" + orderInfo
                    + "&partnerCode=" + PARTNER_CODE
                    + "&redirectUrl=" + redirectUrl
                    + "&requestId=" + requestId
                    + "&requestType=" + requestType;

            String signature = hmacSHA256(rawSignature, SECRET_KEY);
            // tạo JSON body đầy đủ
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("partnerCode", PARTNER_CODE);
            jsonBody.put("accessKey", ACCESS_KEY);
            jsonBody.put("requestId", requestId);
            jsonBody.put("amount", String.valueOf(amount));
            jsonBody.put("orderId", orderId);
            jsonBody.put("orderInfo", orderInfo);
            jsonBody.put("redirectUrl", redirectUrl);
            jsonBody.put("ipnUrl", ipnUrl);
            jsonBody.put("extraData", extraData);
            jsonBody.put("requestType", requestType);
            jsonBody.put("storeId", storeId);
            jsonBody.put("partnerName", partnerName);
            jsonBody.put("lang", lang);
            jsonBody.put("autoCapture", autoCapture);
            jsonBody.put("signature", signature);

            URL url = new URL(CREATE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // parse json
            JSONObject json = new JSONObject(response.toString());

            String link;
            if (json.has("deeplink")) {//neu co deep link khong thi lay payurl
                link = json.getString("deeplink");
            } else {
                link = json.getString("payUrl");

            }
            return new MomoQRResult(link, orderId, requestId);

        } catch (MalformedURLException ex) {
            Logger.getLogger(MomoApiHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MomoApiHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String hmacSHA256(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] hash = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
            return bytesToHex(hash);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MomoApiHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MomoApiHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(MomoApiHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (Byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String checkMomoTransactionStatus(String orderId, String requestId) {
        //tao raw signature
        String rawSignature = "accessKey=" + ACCESS_KEY
                + "&orderId=" + orderId
                + "&partnerCode=" + PARTNER_CODE
                + "&requestId=" + requestId;

        String signature = hmacSHA256(rawSignature, SECRET_KEY);

        //tao JSON body
        String jsonBody = "{"
                + "\"partnerCode\":\"" + PARTNER_CODE + "\","
                + "\"requestId\":\"" + requestId + "\","
                + "\"orderId\":\"" + orderId + "\","
                + "\"lang\":\"vi\","
                + "\"signature\":\"" + signature + "\""
                + "}";

        try {
            //gui post request
            URL url = new URL(QUERY_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            //gui du lieu
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // doc response
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            return response.toString();

        } catch (MalformedURLException ex) {
            Logger.getLogger(MomoApiHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MomoApiHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
