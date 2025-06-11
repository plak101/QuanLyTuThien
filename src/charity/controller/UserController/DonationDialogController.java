/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.controller.UserController;

import charity.component.GButton;
import charity.component.IFormatData;
import charity.component.ImageIconCustom;
import charity.model.CharityEvent;
import charity.model.Donation;
import charity.service.AccountService;
import charity.service.CategoryService;
import charity.service.CharityEventService;
import charity.service.DonationService;
import charity.service.OrganizationService;
import charity.utils.MessageDialog;
import charity.utils.MomoApiHelper;
import charity.view.User.UserUI;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author phaml
 */
public class DonationDialogController implements IFormatData {

    private int accountId, userId;
    private CharityEvent event;
    private CharityEventService eventService = new CharityEventService();
    private DonationService donationService = new DonationService();
    private AccountService accountService = new AccountService();
    private OrganizationService organizationService = new OrganizationService();
    private CategoryService categoryService = new CategoryService();

    private JTextField txtEventId;
    private JTextField txtEventName;
    private JTextField txtCategory;
    private JTextField txtTargetAmount;
    private JTextField txtCurrentAmount;
    private JLabel txtProgress;
    private JTextField txtDateBegin;
    private JTextField txtDateEnd;
    private JTextArea txtDescription;
    private JTextField txtMoney;
    private JTextField txtOrganiation;
    private GButton gbtDonate;
    private JLabel jlbImage;
    private JTextArea txtMessage;
    private JProgressBar jpbProgress;
    private JFrame parent;
    private Long money;
//    private CharityEvent event2 = eventService.getEventById(event.getId());

    public DonationDialogController(JFrame parent, int accountId, int userId, CharityEvent event, JTextField txtEventId, JTextField txtEventName, JTextField txtCategory, JTextField txtTargetAmount, JTextField txtCurrentAmount, JLabel txtProgress, JTextField txtDateBegin, JTextField txtDateEnd, JTextArea txtDescription, JTextField txtMoney, GButton gbtDonate, JTextField txtOrganization, JLabel jlbImage, JTextArea txtMessage, JProgressBar jpbProgress) {
        this.accountId = accountId;
        this.userId = userId;
        this.event = event;
        this.txtEventId = txtEventId;
        this.txtEventName = txtEventName;
        this.txtCategory = txtCategory;
        this.txtTargetAmount = txtTargetAmount;
        this.txtCurrentAmount = txtCurrentAmount;
        this.txtProgress = txtProgress;
        this.txtDateBegin = txtDateBegin;
        this.txtDateEnd = txtDateEnd;
        this.txtDescription = txtDescription;
        this.txtMoney = txtMoney;
        this.gbtDonate = gbtDonate;
        this.txtOrganiation = txtOrganization;
        this.txtMessage = txtMessage;
        this.jlbImage = jlbImage;
        this.jpbProgress = jpbProgress;
        this.parent = parent;
    }

    public void loadEventData() {
        CharityEvent event2 = eventService.getEventById(event.getId());
        event = event2;
        txtEventId.setText(String.valueOf(event2.getId()));
        txtEventName.setText((event2.getName()));
        String categoryName = categoryService.getCategoryNameById(event2.getCategoryId());
        txtCategory.setText(categoryName);
        txtCurrentAmount.setText((String.valueOf(moneyFormat.format(event2.getCurrentAmount()))));
        txtTargetAmount.setText((String.valueOf(moneyFormat.format(event2.getTargetAmount()))));
        txtDateBegin.setText(dateFormat.format(event2.getDateBegin()));
        txtDateEnd.setText(dateFormat.format(event2.getDateEnd()));
        txtProgress.setText(String.format("%.2f%%", (float) event2.getCurrentAmount() / event2.getTargetAmount() * 100));
        txtDescription.setText(event2.getDescription());
        String organizationName = organizationService.getNameById(event2.getOrganizationId());
        txtOrganiation.setText(organizationName);
        String url = event2.getImageUrl();
        jlbImage.setIcon(ImageIconCustom.getSmoothIcon(url, 170, 142));

        jpbProgress.setMinimum(0);
        jpbProgress.setMaximum((int) event2.getTargetAmount());
        jpbProgress.setValue((int) event2.getCurrentAmount());
    }

    public void settingTxtMoney() {
        txtMoney.setText("0");
        txtMoney.setForeground(Color.GRAY);

        txtMoney.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtMoney.getText().equals("0")) {
                    txtMoney.setText("");
                    txtMoney.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtMoney.getText().equals("")) {
                    txtMoney.setText("0");
                    txtMoney.setForeground(Color.GRAY);
                }
            }
        });
    }

    public void setJbtDonateEvent() {
        gbtDonate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!accountService.isUserExist(accountId)) {
                    MessageDialog.showError("Bạn chưa xác thực thông tin");
                    return;
                }

                //kiem tra ngay có hết hạn không
                if (new java.util.Date().after(event.getDateEnd())) {
                    MessageDialog.showPlain("Sự kiện đã quá hạn quyên góp");
                    return;
                }

                String moneyStr = txtMoney.getText().trim();
                //kiem tra chuoi rong
                if (moneyStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền quyên góp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //kiem tra so tien hop le
                if (moneyStr.matches(".*[a-zA-Z].*")) {
                    JOptionPane.showMessageDialog(null, "Số tiền nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                money = Long.parseLong(moneyStr);

                if (money < 2000) {
                    JOptionPane.showMessageDialog(null, "Số tiền quyên góp không bé hơn 2 000 !", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //hien thi thong bao xac nhan
                int accept = JOptionPane.showConfirmDialog(null, "Xác nhận quyên góp!", "Xác nhận quyên góp!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (accept == JOptionPane.OK_OPTION) { //Dong y
                    //goi api tao ma qr momo
                    MomoApiHelper.MomoQRResult qrResult = MomoApiHelper.createQRCode(money, event.getId(), userId);
                    //kiem tra qr ton tai
                    if (qrResult == null || qrResult.qrCodeUrl == null || qrResult.qrCodeUrl.isEmpty()) {
                        MessageDialog.showError("Không thể tạo mã QR Momo");
                        return;
                    }
                    showMomoQRCodeDialog(qrResult.qrCodeUrl, qrResult.orderId, qrResult.requestId, money);
                }
            }
        });
    }

    public void setTxtMoneyKeyListener() {
        txtMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    e.consume();//bỏ qua nếu không phải số
                }
            }

        });
    }

    public void setHoverButtonEvent() {
        gbtDonate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtDonate.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtDonate.changeColor("#0c5776");
            }
        });
    }

    //kiem tra trang thai giao dich
    private boolean checkMomoPaymentStatus(String orderId, String requestId) {
        try {
            String response = MomoApiHelper.checkMomoTransactionStatus(orderId, requestId);
            return response.contains("\"resultCode\":0");
        } catch (Exception e) {
            MessageDialog.showError("Không thể kiểm tra trạng thái giao dịch Momo!");
            return false;
        }
    }

    //hien qr thanh toan
    private void showMomoQRCodeDialog(String qrCodeUrl, String orderId, String requestId, long money) {
        JDialog qrDialog = new JDialog(parent, "Quét mã QR Momo", true);
        qrDialog.setLayout(new BorderLayout());
        qrDialog.setSize(350, 400);
        qrDialog.setLocationRelativeTo(parent);

        JLabel qrLabel = null;
        try {
            String filePath = "src/charity/image/qrCode.png";
            createQRCodeImage(qrCodeUrl, filePath);
            ImageIcon qrIcon = new ImageIcon(filePath);
            qrLabel = new JLabel();
            qrLabel.setIcon(qrIcon);
            qrDialog.add(qrLabel, BorderLayout.CENTER);
        } catch (Exception ex) {
            MessageDialog.showError("Không thể tải mã QR");
            qrDialog.dispose();
            return;
        }

        //Poilling kiem tra trang thai giao dich moi 3 giay
        Timer timer = new Timer(3000, null);
        timer.addActionListener(e -> {
            if (checkMomoPaymentStatus(orderId, requestId)) {
                timer.stop();
                qrDialog.dispose();

                //ghi nhan quyen 
                doDonate();
            }
        });

        //dong timer khi tat dialog
        qrDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                timer.stop();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
            }

        });

        timer.start();
        qrDialog.setVisible(true);
    }

    //ghi nhan quyen gop
    private void doDonate() {
        //lấy thời gian hiện tại
        long currentTimeMillis = Calendar.getInstance().getTimeInMillis();

        // Tạo timestamp của SQL
        Timestamp currentTimestamp = new Timestamp(currentTimeMillis);

        Donation donation = new Donation(event.getId(), userId, money, currentTimestamp, txtMessage.getText());
        //them vao danh sach quyen gop
        if (donationService.addDonation(donation)) {
            loadEventData();//cập nhật giao diện
            if (parent instanceof UserUI) {
                ((UserUI) parent).getController().reloadMainPanel();
            }
            txtMoney.setText("");
            txtMessage.setText("");
            JOptionPane.showMessageDialog(null, "Quyên góp thành công! Cảm ơn sự đóng góp của bạn!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm quyên góp thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createQRCodeImage(String qrCodeURL, String filePath) {
        String charset = "UTF-8";
        System.out.println(filePath);
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeURL.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE,
                    300,
                    300,
                    hintMap
            );

            File file = new File(filePath);

            System.out.println("Absolute path: " + file.getAbsolutePath());
            //ghi ma tran ra file
            MatrixToImageWriter.writeToFile(
                    matrix,
                    filePath.substring(filePath.lastIndexOf(".") + 1),
                    file);

        } catch (WriterException ex) {
            Logger.getLogger(DonationDialogController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DonationDialogController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DonationDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
