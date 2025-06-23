/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.controller.LoginController;

import charity.model.Account; // Import Account model
import charity.model.Role;   // Import Role enum
import charity.service.AccountService; // Import AccountService
import charity.view.Admin.AdminUI; // Import AdminUI
import charity.view.Login.ForgotPasswordDialog;
import charity.view.Login.ForgotPasswordDialog2;
import charity.view.User.UserUI;     // Import UserUI
import charity.view.Login.NewLogin;
import charity.view.Login.NewRegister;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame; // Import JFrame for dispose()

public class NewLoginController {

    private NewLogin loginView; // Tham chiếu đến đối tượng giao diện (NewLogin.java)
    private JTextField txtUsername; //
    private JPasswordField txtPassword; //
    private JButton jbtLogin; //
    private JLabel jlbForgotPassword; //
    private JLabel jlbSignUpLink; //

    private AccountService accountService; // Khai báo AccountService

    public NewLoginController(NewLogin loginView, JTextField txtUsername, JPasswordField txtPassword, JButton jbtLogin, JLabel jlbForgotPassword, JLabel jlbSignUpLink) {
        this.loginView = loginView;
        this.txtUsername = txtUsername;
        this.txtPassword = txtPassword;
        this.jbtLogin = jbtLogin;
        this.jlbForgotPassword = jlbForgotPassword;
        this.jlbSignUpLink = jlbSignUpLink;
        this.accountService = new AccountService(); // Khởi tạo AccountService
    }

    public void setEvent() {
        jbtLogin.addActionListener(new ActionListener() { //
            @Override
            public void actionPerformed(ActionEvent e) { //
                handleLogin(); //
            }
        });

        jlbForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ForgotPasswordDialog2 dialog = new ForgotPasswordDialog2(loginView);
                dialog.setVisible(true);
                if (dialog.isSubmitted()) {
                    String username = dialog.getUsername();
                    String email = dialog.getEmail();
                    // Kiểm tra username và email trong database
                    Account acc = accountService.findByUsernameAndEmail(username, email);
                    if (acc != null) {
                        // Sinh mật khẩu mới
                        String newPassword = generateRandomPassword();
                        // Cập nhật mật khẩu mới vào DB
                        accountService.updatePassword(acc.getId(), newPassword);
                        // Gửi email
                        EmailHelper.send(email, "Mật khẩu mới", "Mật khẩu mới của bạn là: " + newPassword);
                        JOptionPane.showMessageDialog(loginView, "Mật khẩu mới đã được gửi về email!");
                    } else {
                        JOptionPane.showMessageDialog(loginView, "Tên đăng nhập hoặc email không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        jlbSignUpLink.addMouseListener(new MouseAdapter() { //
            @Override
            public void mouseClicked(MouseEvent e) { //
                loginView.switchToRegister(); // Gọi phương thức của view để chuyển màn hình
            }

            @Override
            public void mouseEntered(MouseEvent e) { //
                jlbSignUpLink.setForeground(new Color(46, 204, 113)); // Đổi màu khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) { //
                // Đổi lại màu ban đầu của link khi di chuột ra
                jlbSignUpLink.setForeground(Color.BLUE.darker()); //
            }
        });
    }

    private void handleLogin() {
        String username = txtUsername.getText(); // Lấy tên đăng nhập từ JTextField
        String password = new String(txtPassword.getPassword()); // Lấy mật khẩu từ JPasswordField

        // 1. Kiểm tra rỗng tương tự LoginController.java
        if (username.isEmpty() || password.isEmpty()) { // Sử dụng isEmpty() thay vì length() == 0 cho chuỗi
            JOptionPane.showMessageDialog(loginView, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu !", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; // Dừng xử lý nếu có trường rỗng
        }

        // 2. Sử dụng AccountService để kiểm tra tài khoản
        Account account = accountService.checkAccount(username, password); //

        if (account == null) { // Nếu tài khoản không tìm thấy hoặc mật khẩu sai
            JOptionPane.showMessageDialog(loginView, "Tên đăng nhập hoặc mật khẩu không đúng.", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE); //
        } else { // Đăng nhập thành công
            loginView.dispose(); // Đóng cửa sổ đăng nhập hiện tại

            // 3. Mở giao diện người dùng tương ứng với vai trò
            if (account.getRole() == Role.User) { // Nếu vai trò là người dùng thông thường
                UserUI ui = new UserUI(account.getId()); // Mở UserUI và truyền ID tài khoản
                ui.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
                ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đặt hành vi đóng ứng dụng
                ui.setVisible(true); // Hiển thị UserUI
            } else { // Nếu vai trò là quản trị viên
                AdminUI ui = new AdminUI(account.getId()); // Mở AdminUI và truyền ID tài khoản
                ui.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
                ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đặt hành vi đóng ứng dụng
                ui.setVisible(true); // Hiển thị AdminUI
            }
        }

        // Xóa mật khẩu khỏi bộ nhớ sau khi sử dụng
        // Chú ý: .getPassword() trả về char[], nên để xóa cần thao tác trên mảng đó
        char[] pwdChars = txtPassword.getPassword();
        for (int i = 0; i < pwdChars.length; i++) {
            pwdChars[i] = 0; // Ghi đè bằng 0
        }
    }
}









/**
 *
 * @author Admin
 */





//public class NewLoginController {
//
//    private NewLogin loginView; // Tham chiếu đến đối tượng giao diện (NewLogin.java)
//    private JTextField txtUsername;
//    private JPasswordField txtPassword;
//    private JButton jbtLogin;
//    private JLabel jlbForgotPassword;
//    private JLabel jlbSignUpLink;
//
//    public NewLoginController(NewLogin loginView, JTextField txtUsername, JPasswordField txtPassword, JButton jbtLogin, JLabel jlbForgotPassword, JLabel jlbSignUpLink) {
//        this.loginView = loginView;
//        this.txtUsername = txtUsername;
//        this.txtPassword = txtPassword;
//        this.jbtLogin = jbtLogin;
//        this.jlbForgotPassword = jlbForgotPassword;
//        this.jlbSignUpLink = jlbSignUpLink; // Khởi tạo biến mới
//    }
//
//    public void setEvent() {
//        jbtLogin.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                handleLogin();
//            }
//        });
//
//        jlbForgotPassword.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                handleForgotPassword();
//            }
//        });
//        
//        jlbSignUpLink.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                loginView.switchToRegister(); // Gọi phương thức của view để chuyển màn hình
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                jlbSignUpLink.setForeground(Color.ORANGE); // Đổi màu khi di chuột vào
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                // Đổi lại màu ban đầu của link khi di chuột ra
//                jlbSignUpLink.setForeground(Color.BLUE.darker());
//            }
//        });
//    }
//
//    private void handleLogin() {
//        String username = txtUsername.getText();
//        char[] password = txtPassword.getPassword();
//
//        if (username.equals("admin") && String.valueOf(password).equals("123")) {
//            JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công!");
//            // loginView.dispose();
//            // new MainFrame().setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(loginView, "Tên đăng nhập hoặc mật khẩu không đúng.", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
//        }
//
//        for (int i = 0; i < password.length; i++) {
//            password[i] = 0;
//        }
//    }
//
//    private void handleForgotPassword() {
//        JOptionPane.showMessageDialog(loginView, "Bạn đã nhấp vào Quên mật khẩu! (Logic xử lý quên mật khẩu sẽ ở đây)");
//    }
//}