/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.view.Login;

import charity.controller.LoginController.NewLoginController;
import com.formdev.flatlaf.FlatClientProperties;

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
public class NewLogin extends JFrame {

    // Khai báo các thành phần giao diện người dùng
    private JLabel jlbHeader;
    private JLabel jlbUsername;
    private JLabel jlbPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton jbtLogin;
    private JLabel jlbForgotPassword;
    private JLabel jlbSignUpLink;

    // Các panel chính
    private JPanel mainContentPanel; // Panel chính chứa cả illustration và login form
    private JPanel illustrationPanel; // Panel chứa hình ảnh minh họa
    private JPanel loginFormPanel;    // Panel chứa form đăng nhập

    public NewLogin() {
        init(); // Gọi phương thức init() để khởi tạo giao diện
        NewLoginController controller = new NewLoginController(this, txtUsername, txtPassword, jbtLogin, jlbForgotPassword, jlbSignUpLink);
        controller.setEvent();
    }

    public void init() {
        setTitle("Đăng nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550); // Giữ nguyên kích thước ban đầu để chứa illustration
        setLocationRelativeTo(null);
        setResizable(false); // Có thể đặt lại thành true nếu muốn

        // --- Main Content Panel (sẽ chứa illustration và login form) ---
        mainContentPanel = new JPanel(new BorderLayout()); // Sử dụng BorderLayout để chia WEST và CENTER
        mainContentPanel.setBackground(Color.WHITE);

        // --- Left Illustration Panel: Dán ảnh vào đây ---
        illustrationPanel = new JPanel();
        illustrationPanel.setBackground(Color.WHITE);
        illustrationPanel.setPreferredSize(new Dimension(400, 550)); // Kích thước cố định cho illustration

        String imagePath = "src/charity/image/loginLogo.jpg";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        

        // Logo bên trái bản gốc
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
        mainContentPanel.add(illustrationPanel, BorderLayout.WEST); // Thêm illustration vào phía TÂY

        // --- Login Form Panel (bên phải, tương ứng với LoginFrame) ---
        loginFormPanel = new JPanel(null); // Sử dụng null layout cho form đăng nhập
        loginFormPanel.setBackground(Color.WHITE);

        // Định nghĩa các font chữ
        Font font28 = new Font("Arial", Font.BOLD, 22); // Điều chỉnh font cho khớp với Login.java ban đầu
        Font font14B = new Font("Arial", Font.BOLD, 14);
        Font font14 = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Tiêu đề: ĐĂNG NHẬP VÀO HỆ THỐNG
        jlbHeader = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        jlbHeader.setFont(font28);
        jlbHeader.setBounds(0, 30, 450, 40); // Điều chỉnh bounds cho phù hợp với kích thước mới
        jlbHeader.setHorizontalAlignment(SwingConstants.CENTER);
        loginFormPanel.add(jlbHeader);

        // Nhãn "Tên đăng nhập"
        jlbUsername = new JLabel("Tên đăng nhập");
        jlbUsername.setFont(font14B);
        jlbUsername.setBounds(50, 100, 350, 30); // Điều chỉnh bounds
        loginFormPanel.add(jlbUsername);

        // Trường nhập "Tên đăng nhập"
        txtUsername = new JTextField();
        txtUsername.setBounds(50, 130, 350, 35); // Điều chỉnh bounds và kích thước
        txtUsername.setFont(font14);
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        loginFormPanel.add(txtUsername);

        // Nhãn "Mật khẩu"
        jlbPassword = new JLabel("Mật khẩu");
        jlbPassword.setFont(font14B);
        jlbPassword.setBounds(50, 180, 350, 30); // Điều chỉnh bounds
        loginFormPanel.add(jlbPassword);

        // Trường nhập "Mật khẩu"
        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 210, 350, 35); // Điều chỉnh bounds và kích thước
        txtPassword.setFont(font14);
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""+"showRevealButton:true;");
        loginFormPanel.add(txtPassword);

        // Nhãn "Quên mật khẩu"
        jlbForgotPassword = new JLabel("Quên mật khẩu");
        jlbForgotPassword.setForeground(Color.BLUE.darker());
        jlbForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlbForgotPassword.setFont(font14);
        
        // Đặt vị trí bên phải của trường mật khẩu
        jlbForgotPassword.setBounds(300, 255, 100, 20); // Điều chỉnh vị trí
        loginFormPanel.add(jlbForgotPassword);

        // Thêm trình nghe sự kiện chuột cho nhãn "Quên mật khẩu"
        jlbForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(NewLogin.this, "Bạn đã nhấp vào Quên mật khẩu!");
            }
        });

        // Nút "ĐĂNG NHẬP"
        jbtLogin = new JButton("ĐĂNG NHẬP");
        jbtLogin.setFont(new Font("Arial", Font.BOLD, 16)); // Giữ font của nút đăng nhập ban đầu
        jbtLogin.setBackground(new Color(23, 32, 42));
        //jbtLogin.setBackground(Color.BLUE);
        jbtLogin.setForeground(Color.WHITE);
        jbtLogin.setBounds(100, 320, 250, 45); // Điều chỉnh bounds và kích thước, căn giữa theo panel
        jbtLogin.setFocusPainted(false);
        jbtLogin.setBorderPainted(false);
        jbtLogin.setOpaque(true);
        loginFormPanel.add(jbtLogin);

        // Thêm trình nghe sự kiện hành động cho nút "ĐĂNG NHẬP"
        


        // Liên kết Đăng ký mới
        jlbSignUpLink = new JLabel("Chưa có tài khoản? Đăng ký ngay");
        jlbSignUpLink.setFont(font14);
        jlbSignUpLink.setForeground(Color.BLUE.darker()); // Màu ban đầu của link
        jlbSignUpLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlbSignUpLink.setBounds(100, 380, 250, 20);
        jlbSignUpLink.setHorizontalAlignment(SwingConstants.CENTER);
        loginFormPanel.add(jlbSignUpLink);

        mainContentPanel.add(loginFormPanel, BorderLayout.CENTER); // Thêm loginFormPanel vào giữa

        add(mainContentPanel); // Thêm panel chứa cả hai phần vào JFrame
        setVisible(true);
    }

    
    public void switchToRegister() {
        this.dispose(); // Đóng cửa sổ NewLogin hiện tại
        new charity.view.Login.NewRegister().setVisible(true); // Mở cửa sổ NewRegister mới
    }
    // Phương thức main để chạy ứng dụng
    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewLogin::new);
    }
}