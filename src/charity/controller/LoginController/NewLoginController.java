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
import java.security.SecureRandom;
import java.util.Base64;
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

//            @Override
//            public void mouseClicked(MouseEvent e) {
//                ForgotPasswordDialog2 dialog = new ForgotPasswordDialog2(loginView);
//                dialog.setVisible(true);
//                if (dialog.isSubmitted()) {
//                    String username = dialog.getUsername();
//                    String email = dialog.getEmail();
//                    // Kiểm tra username và email trong database
//                    Account acc = accountService.findByUsernameAndEmail(username, email);
//                    if (acc != null) {
//                        // Sinh mật khẩu mới
//                        String newPassword = generateRandomPassword();
//                        // Cập nhật mật khẩu mới vào DB
//                        accountService.updatePassword(acc.getId(), newPassword);
//                        // Gửi email
//                        EmailHelper.send(email, "Mật khẩu mới", "Mật khẩu mới của bạn là: " + newPassword);
//                        JOptionPane.showMessageDialog(loginView, "Mật khẩu mới đã được gửi về email!");
//                    } else {
//                        JOptionPane.showMessageDialog(loginView, "Tên đăng nhập hoặc email không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    }
//                }
//            }
//        });

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

    private void handleForgotPassword() {
        ForgotPasswordDialog forgotPasswordDialog = new ForgotPasswordDialog(loginView);
        forgotPasswordDialog.showPanel(ForgotPasswordDialog.PANEL_EMAIL_INPUT); // Hiển thị panel nhập email
        forgotPasswordDialog.setVisible(true); // Hiển thị dialog và chờ nó đóng

        String email = forgotPasswordDialog.getEnteredEmail();
        if (email != null && !email.isEmpty()) {
            // Kiểm tra email có tồn tại trong database không
            if (accountService.isEmailExist(email)) {
                // Tạo mật khẩu tạm thời
                String temporaryPassword = generateTemporaryPassword();

                // Tìm tài khoản bằng email
                Account accountToUpdate = accountService.getAccountByEmail(email); // Sử dụng phương thức mới

                if (accountToUpdate != null) {
                    // Cập nhật mật khẩu của tài khoản bằng mật khẩu tạm thời
                    accountToUpdate.setPassword(temporaryPassword);
                    boolean updated = accountService.updateAccount(accountToUpdate);

                    if (updated) {
                        // Hiển thị mật khẩu tạm thời cho người dùng
                        ForgotPasswordDialog displayTempPwdDialog = new ForgotPasswordDialog(loginView);
                        displayTempPwdDialog.setTemporaryPassword(temporaryPassword);
                        displayTempPwdDialog.showPanel(ForgotPasswordDialog.PANEL_TEMPORARY_PASSWORD);
                        displayTempPwdDialog.setVisible(true);

                        JOptionPane.showMessageDialog(loginView,
                                "Mật khẩu tạm thời đã được tạo và hiển thị. Vui lòng sử dụng mật khẩu này để đăng nhập và thay đổi mật khẩu của bạn sau.",
                                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        txtUsername.setText(accountToUpdate.getUsername()); // Tự động điền username
                        txtPassword.setText(temporaryPassword); // Tự động điền mật khẩu tạm thời
                    } else {
                        JOptionPane.showMessageDialog(loginView, "Không thể cập nhật mật khẩu tạm thời. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(loginView, "Không tìm thấy tài khoản với email này.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(loginView, "Email này không tồn tại trong hệ thống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Phương thức trợ giúp để tạo mật khẩu tạm thời an toàn
    private String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8]; // 8 bytes = 64 bits, sẽ tạo ra chuỗi khoảng 10-11 ký tự trong Base64
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
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