package charity.controller;

import charity.model.Donation;
import charity.service.DonationService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.*;

/**
 *
 * @author phaml
 */
public class PDFExporter {

    private final Locale vn = new Locale("vi", "VN");
    private SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    private final SimpleDateFormat ntnf = new SimpleDateFormat("EEEE, d 'tháng' M 'năm' yyyy", vn);
    private DonationService donationService = new DonationService();

    public void exportMyDonate(String filePath, int userId) {
        List<Donation> donations = new ArrayList<>();
        donations = donationService.getDonationByUserId(userId);
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            //1. Load font
            BaseFont bf = BaseFont.createFont(
                    "resource/font/arial.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );
            Font titleFont = new Font(bf, 20, Font.BOLD);
            Font subTitleFont = new Font(bf, 16, Font.BOLD);
            Font nomalFont = new Font(bf, 13, Font.NORMAL);
            Font font13B = new Font(bf, 13, Font.BOLD);
            Font tableHeader = new Font(bf, 13, Font.BOLD, BaseColor.WHITE);

            //2. logo
            URL logoUrl = getClass().getClassLoader().getResource("charity/icon/logoCharity.jpg");
            if (logoUrl != null) {
                Image logo = Image.getInstance(logoUrl);
                logo.scaleAbsolute(90, 90);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            } else {
                System.err.println("Không tìm thấy logo" + "charity/icon/logoCharity.jpg");
            }

            //3.Tiêu đêf
            Paragraph orgTitle = new Paragraph("UTC2 CHARITY", titleFont);
            orgTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(orgTitle);

            document.add(new Paragraph("\n"));

            ///4. Tiêu đề chứng nhận
            Paragraph certificateTitle = new Paragraph("CHỨNG NHẬN QUYÊN GÓP", subTitleFont);
            certificateTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(certificateTitle);

            document.add(new Paragraph("\n"));

            //5.Ngay thang nam
            String now = ntnf.format(new Date());
            System.out.println(now);
            Paragraph ntn = new Paragraph(now, font13B);
            ntn.setAlignment(Element.ALIGN_RIGHT);
            document.add(ntn);

            document.close();
            System.out.println("Xuat file pdf thanh cong");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        PDFExporter exporter = new PDFExporter();
        int userId = 2;
        exporter.exportMyDonate("pdfExport/MyDonation.pdf", userId);
    }
}
