
import charity.model.Donation;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DonationPDFExporter {
    public void export(String filePath, List<Donation> donations, String userName, String birthDate, String address, String phone) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Load font hỗ trợ tiếng Việt
            BaseFont baseFont = BaseFont.createFont(
                    "resource/font/arial.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );
            Font titleFont = new Font(baseFont, 20, Font.BOLD);
            Font subTitleFont = new Font(baseFont, 16, Font.BOLD);
            Font normalFont = new Font(baseFont, 13);
            Font tableHeaderFont = new Font(baseFont, 13, Font.BOLD, BaseColor.WHITE);

            // Thêm logo
            URL logoUrl = getClass().getClassLoader().getResource("charity/icon/logoCharity.jpg");
            if (logoUrl != null) {
                Image logo = Image.getInstance(logoUrl);
                logo.scaleAbsolute(80, 80);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            }

            // Tiêu đề tổ chức
            Paragraph orgTitle = new Paragraph("UTC2 CHARITY", titleFont);
            orgTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(orgTitle);

            document.add(new Paragraph("\n"));

            // Tiêu đề chứng nhận
            Paragraph certificateTitle = new Paragraph("CHỨNG NHẬN QUYÊN GÓP", subTitleFont);
            certificateTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(certificateTitle);

            document.add(new Paragraph("\n"));

            // Thông tin khách hàng
            Paragraph userInfo = new Paragraph(
                    "ID: 00001\n"
                    + "Tên khách hàng: " + userName + "\n"
                    + "Năm sinh: " + birthDate + "\n"
                    + "Địa chỉ: " + address + "\n"
                    + "SĐT: " + phone,
                    normalFont
            );
            userInfo.setAlignment(Element.ALIGN_RIGHT);
            document.add(userInfo);

            document.add(new Paragraph("\n"));

            // Bảng lịch sử quyên góp
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 3f, 3f, 2f, 3f, 4f});
            table.setSpacingBefore(10f);

            // Header bảng
            addTableHeader(table, tableHeaderFont);

            // Nội dung bảng
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Donation donation : donations) {
                table.addCell(new PdfPCell(new Phrase(String.valueOf(donation.getId()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(userName, normalFont)));
                table.addCell(new PdfPCell(new Phrase("Sự kiện " + donation.getEventId(), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.format("%,d", donation.getAmount()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(sdf.format(donation.getDonationDate()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(donation.getDescription(), normalFont)));
            }

            document.add(table);

            document.add(new Paragraph("\n"));

            // Lời cảm ơn
            Paragraph thankYou = new Paragraph("Xin chân thành cảm ơn sự đóng góp quý báu của bạn!", subTitleFont);
            thankYou.setAlignment(Element.ALIGN_CENTER);
            document.add(thankYou);

            document.close();
            System.out.println("Xuất file PDF thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table, Font font) {
        Stream.of("ID", "Người quyên góp", "Sự kiện", "Số tiền", "Ngày quyên góp", "Nội dung")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.GRAY);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setPadding(5);
                    header.setPhrase(new Phrase(columnTitle, font));
                    table.addCell(header);
                });
    }

    public static void main(String[] args) {
        DonationPDFExporter exporter = new DonationPDFExporter();
        List<Donation> donations = Arrays.asList(
                new Donation(2, 1, 1, 12222, Timestamp.valueOf("2025-02-12 00:00:00"), "ủng hộ trẻ em"),
                new Donation(5, 2, 1, 1212222, Timestamp.valueOf("2025-02-12 00:00:00"), "hỗ trợ thiên tai")
        );
        exporter.export("pdfExport/donation_certificate.pdf", donations, "Nguyễn Văn A", "26/04/2000", "An Giang", "01232134");
    }
}