/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.view.Login;

import charity.controller.LoginController.NewRegisterController; // Import Controller mới
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Admin
 */
public class NewRegister extends JFrame { // Đổi tên lớp thành NewRegister

    // Khai báo các thành phần giao diện người dùng cho đăng ký
    private JLabel jlbHeader;
    private JLabel jlbRegUsername;
    private JTextField txtRegUsername;
    private JLabel jlbRegEmail;
    private JTextField txtRegEmail;
    private JLabel jlbRegPassword;
    private JPasswordField txtRegPassword;
    private JLabel jlbRegConfirmPassword;
    private JPasswordField txtRegConfirmPassword;
    private JButton jbtRegister;
    private JLabel jlbBackToLogin;

    // Các panel chính
    private JPanel mainContentPanel;
    private JPanel illustrationPanel;
    private JPanel registerFormPanel; // Panel chứa form đăng ký

    // Khai báo đối tượng controller
    //private NewRegisterController controller;

    public NewRegister() {
        init(); // Gọi phương thức init() để khởi tạo giao diện
        // Khởi tạo controller và truyền các thành phần UI cần thiết
        NewRegisterController controller = new NewRegisterController(this, txtRegUsername, txtRegEmail, txtRegPassword, txtRegConfirmPassword, jbtRegister, jlbBackToLogin);
        controller.setEvent(); // Thiết lập các sự kiện qua controller
    }

    public void init() {
        setTitle("Đăng ký tài khoản"); // Tiêu đề cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550); // Kích thước tương tự màn hình đăng nhập
        setLocationRelativeTo(null);
        setResizable(false);

        // --- Main Content Panel (sẽ chứa illustration và register form) ---
        mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setBackground(Color.WHITE);

        // --- Left Illustration Panel: Dán ảnh vào đây ---
        illustrationPanel = new JPanel();
        illustrationPanel.setBackground(Color.WHITE);
        illustrationPanel.setPreferredSize(new Dimension(400, 550));

        String imagePath = "src/charity/image/logoCharity.jpg"; // Giữ nguyên đường dẫn ảnh
        ImageIcon imageIcon = new ImageIcon(imagePath);

        if (imageIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            illustrationPanel.add(imageLabel);
        } else {
            JLabel fallbackLabel = new JLabel("<html><center><br><br><br><br><br><br>" +
                                          "Không tìm thấy ảnh.<br>Hãy kiểm tra đường dẫn!" +
                                          "</center></html>");
            fallbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
            fallbackLabel.setVerticalAlignment(SwingConstants.CENTER);
            illustrationPanel.add(fallbackLabel);
            System.err.println("Lỗi: Không thể tải ảnh từ đường dẫn: " + imagePath);
        }
        mainContentPanel.add(illustrationPanel, BorderLayout.WEST);

        // --- Register Form Panel (bên phải) ---
        registerFormPanel = new JPanel(null); // Sử dụng null layout cho form đăng ký
        registerFormPanel.setBackground(Color.WHITE);

        // Định nghĩa các font chữ
        Font font22B = new Font("Arial", Font.BOLD, 22);
        Font font14B = new Font("Arial", Font.BOLD, 14);
        Font font14 = new Font("Arial", Font.PLAIN, 14);

        // Tiêu đề: ĐĂNG KÝ TÀI KHOẢN MỚI
        jlbHeader = new JLabel("ĐĂNG KÝ TÀI KHOẢN MỚI");
        jlbHeader.setFont(font22B);
        jlbHeader.setBounds(0, 30, 450, 40);
        jlbHeader.setHorizontalAlignment(SwingConstants.CENTER);
        registerFormPanel.add(jlbHeader);

        // Nhãn "Tên đăng nhập"
        jlbRegUsername = new JLabel("Tên đăng nhập");
        jlbRegUsername.setFont(font14B);
        jlbRegUsername.setBounds(50, 90, 350, 30);
        registerFormPanel.add(jlbRegUsername);

        // Trường nhập "Tên đăng nhập"
        txtRegUsername = new JTextField();
        txtRegUsername.setBounds(50, 120, 350, 35);
        txtRegUsername.setFont(font14);
        txtRegUsername.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        registerFormPanel.add(txtRegUsername);

        // Nhãn "Email"
        jlbRegEmail = new JLabel("Email");
        jlbRegEmail.setFont(font14B);
        jlbRegEmail.setBounds(50, 160, 350, 30);
        registerFormPanel.add(jlbRegEmail);

        // Trường nhập "Email"
        txtRegEmail = new JTextField();
        txtRegEmail.setBounds(50, 190, 350, 35);
        txtRegEmail.setFont(font14);
        txtRegEmail.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        registerFormPanel.add(txtRegEmail);

        // Nhãn "Mật khẩu"
        jlbRegPassword = new JLabel("Mật khẩu");
        jlbRegPassword.setFont(font14B);
        jlbRegPassword.setBounds(50, 230, 350, 30);
        registerFormPanel.add(jlbRegPassword);

        // Trường nhập "Mật khẩu"
        txtRegPassword = new JPasswordField();
        txtRegPassword.setBounds(50, 260, 350, 35);
        txtRegPassword.setFont(font14);
        txtRegPassword.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        registerFormPanel.add(txtRegPassword);

        // Nhãn "Nhập lại mật khẩu"
        jlbRegConfirmPassword = new JLabel("Nhập lại mật khẩu");
        jlbRegConfirmPassword.setFont(font14B);
        jlbRegConfirmPassword.setBounds(50, 300, 350, 30);
        registerFormPanel.add(jlbRegConfirmPassword);

        // Trường nhập "Nhập lại mật khẩu"
        txtRegConfirmPassword = new JPasswordField();
        txtRegConfirmPassword.setBounds(50, 330, 350, 35);
        txtRegConfirmPassword.setFont(font14);
        txtRegConfirmPassword.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        registerFormPanel.add(txtRegConfirmPassword);

        // Nút "ĐĂNG KÝ"
        jbtRegister = new JButton("ĐĂNG KÝ");
        jbtRegister.setFont(new Font("Arial", Font.BOLD, 16));
        jbtRegister.setBackground(new Color(46, 204, 113)); // Màu xanh lá cây cho nút đăng ký
        jbtRegister.setForeground(Color.WHITE);
        jbtRegister.setBounds(100, 400, 250, 45);
        jbtRegister.setFocusPainted(false);
        jbtRegister.setBorderPainted(false);
        jbtRegister.setOpaque(true);
        registerFormPanel.add(jbtRegister);

        // Nhãn "Đã có tài khoản? Quay lại Đăng nhập"
        jlbBackToLogin = new JLabel("Đã có tài khoản? Quay lại Đăng nhập");
        jlbBackToLogin.setForeground(Color.BLUE.darker()); // Màu ban đầu của link
        jlbBackToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlbBackToLogin.setFont(font14);
        jlbBackToLogin.setBounds(100, 455, 250, 20);
        jlbBackToLogin.setHorizontalAlignment(SwingConstants.CENTER);
        registerFormPanel.add(jlbBackToLogin);


        mainContentPanel.add(registerFormPanel, BorderLayout.CENTER);

        add(mainContentPanel);
        setVisible(true);
    }
    
    public void switchToLogin() {
        this.dispose(); // Đóng cửa sổ NewRegister hiện tại
        new charity.view.Login.NewLogin().setVisible(true); // Mở cửa sổ NewLogin mới
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewRegister::new);
    }
}