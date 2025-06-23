package charity.utils;

import charity.component.ColorCustom;
import charity.model.Donation;
import charity.model.User;
import charity.service.CharityEventService;
import charity.service.DonationService;
import charity.service.UserService;
import charity.utils.MessageDialog;
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
import java.util.stream.Stream;
import javax.swing.JTable;

/**
 *
 * @author phaml
 */
public class PDFExporter {

    private final Locale vn = new Locale("vi", "VN");
    private SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    private final SimpleDateFormat ntnf = new SimpleDateFormat("EEEE, d 'tháng' M 'năm' yyyy", vn);
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DonationService donationService = new DonationService();
    private UserService userService = new UserService();
    private CharityEventService eventService = new CharityEventService();

    public void exportMyDonate(String filePath, int userId) {
        List<Donation> donations = new ArrayList<>();
        donations = donationService.getDonationByUserId(userId);
        User user = userService.getUserById(userId);
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
            Font normalFont = new Font(bf, 13, Font.NORMAL);
            Font font13B = new Font(bf, 13, Font.BOLD);
            Font tableHeaderFont = new Font(bf, 13, Font.BOLD, BaseColor.WHITE);

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

            // Thông tin khách hàng
            Paragraph userInfo = new Paragraph(
                    "ID: " + userId + "\n"
                    + "Tên khách hàng: " + user.getName() + "\n"
                    + "Năm sinh: " + sdf.format(user.getBirthday()) + "\n"
                    + "Địa chỉ: " + user.getAddress() + "\n"
                    + "SĐT: " + user.getPhone(),
                    normalFont
            );
            userInfo.setAlignment(Element.ALIGN_LEFT);
            document.add(userInfo);

            document.add(new Paragraph("\n"));

            // Bảng lịch sử quyên góp
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 3f, 3f, 2f, 3f, 4f});
            table.setSpacingBefore(10f);

            // Header bảng
            Stream.of("ID", "Người quyên góp", "Sự kiện", "Số tiền", "Ngày quyên góp", "Nội dung")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(ColorCustom.tableHeader());
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setPadding(5);
                        header.setPhrase(new Phrase(columnTitle, tableHeaderFont));
                        table.addCell(header);
                    });

            // Nội dung bảng
            for (Donation donation : donations) {
                table.addCell(new PdfPCell(new Phrase(String.valueOf(donation.getId()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(user.getName(), normalFont)));
                table.addCell(new PdfPCell(new Phrase(eventService.getEventNameById(donation.getEventId()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(String.format("%,d", donation.getAmount()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(dtf.format(donation.getDonationDate()), normalFont)));
                table.addCell(new PdfPCell(new Phrase(donation.getDescription(), normalFont)));
            }

            document.add(table);

            document.add(new Paragraph("\n"));

            // Lời cảm ơn
            Paragraph thankYou = new Paragraph("Xin chân thành cảm ơn sự đóng góp quý báu của bạn!", subTitleFont);
            thankYou.setAlignment(Element.ALIGN_CENTER);
            document.add(thankYou);

            document.close();
            MessageDialog.showSuccess("Xuất danh sách thành công!");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.showError("Xuất danh sách thất bại");

        } catch (DocumentException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            MessageDialog.showError("Xuất danh sách thất bại");
        }
    }

    public void exportDonate(String filePath, JTable table) {
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
            Font normalFont = new Font(bf, 13, Font.NORMAL);
            Font font13B = new Font(bf, 13, Font.BOLD);
            Font tableHeaderFont = new Font(bf, 13, Font.BOLD, BaseColor.WHITE);

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

            //5.Ngay thang nam
            String now = ntnf.format(new Date());
            System.out.println(now);
            Paragraph ntn = new Paragraph(now, font13B);
            ntn.setAlignment(Element.ALIGN_RIGHT);
            document.add(ntn);

            document.add(new Paragraph("\n"));

            ///4. Tiêu đề chứng nhận
            Paragraph certificateTitle = new Paragraph("DANH SÁCH QUYÊN GÓP", subTitleFont);
            certificateTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(certificateTitle);

            document.add(new Paragraph("\n"));
            // Thông tin khách hàng
//            Paragraph userInfo = new Paragraph(
//                    "ID: "+ userId+"\n"
//                    + "Tên khách hàng: " + user.getName() + "\n"
//                    + "Năm sinh: " + sdf.format(user.getBirthday()) + "\n"
//                    + "Địa chỉ: " + user.getAddress() + "\n"
//                    + "SĐT: " + user.getPhone(),
//                    normalFont
//            );
//            userInfo.setAlignment(Element.ALIGN_LEFT);
//            document.add(userInfo);
//
//            document.add(new Paragraph("\n"));

            // Bảng lịch sử quyên góp
            PdfPTable pdfTable = new PdfPTable(6);
            pdfTable.setWidthPercentage(100);
            pdfTable.setWidths(new float[]{1f, 3f, 3f, 2f, 3f, 4f});
            pdfTable.setSpacingBefore(10f);

            // Header bảng
            Stream.of("ID", "Người quyên góp", "Sự kiện", "Số tiền", "Ngày quyên góp", "Nội dung")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(ColorCustom.tableHeader());
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setPadding(5);
                        header.setPhrase(new Phrase(columnTitle, tableHeaderFont));
                        pdfTable.addCell(header);
                    });

            // Nội dung bảng
            int rowCount = table.getRowCount();
            int colCount = table.getColumnCount();
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    Object value = table.getValueAt(row, col);
                    PdfPCell cell = new PdfPCell(new Phrase(value != null ? value.toString() : "", normalFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setPadding(5);
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
            document.add(new Paragraph("\n"));

            document.close();
            MessageDialog.showSuccess("Xuất danh sách thành công!");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.showError("Xuất danh sách thất bại");

        } catch (DocumentException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.showError("Xuất danh sách thất bại");
        } catch (Exception e) {
            MessageDialog.showError("Xuất danh sách thất bại");
        }
    }

    public void exportOrganization(String filePath, JTable table) {
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
            Font normalFont = new Font(bf, 13, Font.NORMAL);
            Font font13B = new Font(bf, 13, Font.BOLD);
            Font tableHeaderFont = new Font(bf, 13, Font.BOLD, BaseColor.WHITE);

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

            //5.Ngay thang nam
            String now = ntnf.format(new Date());
            System.out.println(now);
            Paragraph ntn = new Paragraph(now, font13B);
            ntn.setAlignment(Element.ALIGN_RIGHT);
            document.add(ntn);

            document.add(new Paragraph("\n"));

            ///4. Tiêu đề chứng nhận
            Paragraph certificateTitle = new Paragraph("DANH SÁCH TỔ CHỨC", subTitleFont);
            certificateTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(certificateTitle);

            document.add(new Paragraph("\n"));
            // Thông tin khách hàng
//            Paragraph userInfo = new Paragraph(
//                    "ID: "+ userId+"\n"
//                    + "Tên khách hàng: " + user.getName() + "\n"
//                    + "Năm sinh: " + sdf.format(user.getBirthday()) + "\n"
//                    + "Địa chỉ: " + user.getAddress() + "\n"
//                    + "SĐT: " + user.getPhone(),
//                    normalFont
//            );
//            userInfo.setAlignment(Element.ALIGN_LEFT);
//            document.add(userInfo);
//
//            document.add(new Paragraph("\n"));

            // Bảng lịch sử quyên góp
            PdfPTable pdfTable = new PdfPTable(6);
            pdfTable.setWidthPercentage(100);
            pdfTable.setWidths(new float[]{1f, 3f, 3f, 2f, 3f, 1f});
            pdfTable.setSpacingBefore(10f);

            // Header bảng
            Stream.of("ID", "Tên tổ chức", "Email", "Hotline", "Địa chỉ", "Số sự kiện")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(ColorCustom.tableHeader());
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        if (columnTitle.equals("Số sự kiện")) {
                            header.setPadding(2);
                        } else {
                            header.setPadding(5);
                        }
                        header.setPhrase(new Phrase(columnTitle, tableHeaderFont));
                        pdfTable.addCell(header);
                    });

            // Nội dung bảng
            int rowCount = table.getRowCount();
            int colCount = table.getColumnCount();
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    Object value = table.getValueAt(row, col);
                    PdfPCell cell = new PdfPCell(new Phrase(value != null ? value.toString() : "", normalFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    if (col == 5) {
                        cell.setPadding(2);
                    } else {
                        cell.setPadding(5);
                    }
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
            document.add(new Paragraph("\n"));

            document.close();
            MessageDialog.showSuccess("Xuất danh sách thành công!");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.showError("Xuất danh sách thất bại");
        } catch (DocumentException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.showError("Xuất danh sách thất bại");
        } catch (Exception e) {
            MessageDialog.showError("Xuất danh sách thất bại");
        }
    }
    public void exportUser(String filePath, JTable table) {
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
            Font normalFont = new Font(bf, 13, Font.NORMAL);
            Font font13B = new Font(bf, 13, Font.BOLD);
            Font tableHeaderFont = new Font(bf, 13, Font.BOLD, BaseColor.WHITE);

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

            //5.Ngay thang nam
            String now = ntnf.format(new Date());
            System.out.println(now);
            Paragraph ntn = new Paragraph(now, font13B);
            ntn.setAlignment(Element.ALIGN_RIGHT);
            document.add(ntn);

            document.add(new Paragraph("\n"));

            ///4. Tiêu đề chứng nhận
            Paragraph certificateTitle = new Paragraph("DANH SÁCH Người Quyên Góp", subTitleFont);
            certificateTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(certificateTitle);

            document.add(new Paragraph("\n"));
            // Thông tin khách hàng
//            Paragraph userInfo = new Paragraph(
//                    "ID: "+ userId+"\n"
//                    + "Tên khách hàng: " + user.getName() + "\n"
//                    + "Năm sinh: " + sdf.format(user.getBirthday()) + "\n"
//                    + "Địa chỉ: " + user.getAddress() + "\n"
//                    + "SĐT: " + user.getPhone(),
//                    normalFont
//            );
//            userInfo.setAlignment(Element.ALIGN_LEFT);
//            document.add(userInfo);
//
//            document.add(new Paragraph("\n"));

            // Bảng lịch sử quyên góp
            PdfPTable pdfTable = new PdfPTable(7);
            pdfTable.setWidthPercentage(100);
            pdfTable.setWidths(new float[]{1f, 1f, 3f, 2f, 2f, 2f,2f});
            pdfTable.setSpacingBefore(10f);

            // Header bảng
            Stream.of("ID", "ID tài khoản","Họ tên", "Địa chỉ", "Số điện thoại", "Năm sinh", "Giới tính")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(ColorCustom.tableHeader());
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                            header.setPadding(5);
                        header.setPhrase(new Phrase(columnTitle, tableHeaderFont));
                        pdfTable.addCell(header);
                    });

            // Nội dung bảng
            int rowCount = table.getRowCount();
            int colCount = table.getColumnCount();
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    Object value = table.getValueAt(row, col);
                    PdfPCell cell = new PdfPCell(new Phrase(value != null ? value.toString() : "", normalFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    if (col == 0 || col==1) {
                        cell.setPadding(2);
                    } else {
                        cell.setPadding(5);
                    }
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
            document.add(new Paragraph("\n"));

            document.close();
            MessageDialog.showSuccess("Xuất danh sách thành công!");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.showError("Xuất danh sách thất bại");
        } catch (DocumentException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFExporter.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.showError("Xuất danh sách thất bại");
        } catch (Exception e) {
            MessageDialog.showError("Xuất danh sách thất bại");
        }
    }

    public static void main(String[] args) {
        PDFExporter exporter = new PDFExporter();
        int userId = 2;
        exporter.exportMyDonate("pdfExport/MyDonation.pdf", userId);
    }
}
