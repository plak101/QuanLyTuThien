package charity.loginController;

import charity.model.Account;
import charity.model.Role;
import charity.service.AccountService;
import charity.viewLogin.LoginWindow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author phaml
 */
public class RegisterController {

    private JTextField txtUsername, txtEmail, txtPassword, txtPassword2;
    private JButton jbtRegister, jbtBackLogin;

    private AccountService accountService;

    public RegisterController(JTextField txtUsername, JTextField txtEmail, JTextField txtPassword, JTextField txtPassword2, JButton jbtRegister, JButton jbtBackLogin) {
        this.txtUsername = txtUsername;
        this.txtEmail = txtEmail;
        this.txtPassword = txtPassword;
        this.txtPassword2 = txtPassword2;
        this.jbtRegister = jbtRegister;
        this.jbtBackLogin = jbtBackLogin;

        accountService = new AccountService();
    }

    public void setEvent() {
        setJbtRegisterEvent();
        setJbtBackLogin();
    }

    public void setJbtRegisterEvent() {
        jbtRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = txtUsername.getText().trim();
                String email = txtEmail.getText().trim();
                String password = txtPassword.getText();
                String password2 = txtPassword2.getText();
                //check Validate input
                if (checkInput()) {
                    // Tạo tài khoản mới
                    Account account = new Account();
                    account.setUsername(username);
                    account.setEmail(email);
                    account.setPassword(password);
                    account.setRole(Role.User); // Set quyền mặc định là USER

                    boolean success = accountService.addAccount(account);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Đăng ký thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        // clear form
                        txtUsername.setText("");
                        txtEmail.setText("");
                        txtPassword.setText("");
                        txtPassword2.setText("");

                        //chuuyen sang login
                        LoginWindow ui = new LoginWindow();
                        ui.setVisible(true);
                        SwingUtilities.getWindowAncestor(jbtRegister).dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }

    public boolean checkInput() {
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();
        String password2 = txtPassword2.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra username dài hơn 4 ký tự
        if (username.length() <= 3) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập phải lớn hơn 3 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra email hợp lệ và phải có đuôi @gmail.com
        if (!email.matches("^[\\w-\\.]+@gmail\\.com$")) {
            JOptionPane.showMessageDialog(null, "Email phải có định dạng @gmail.com!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra password dài hơn 6 ký tự và chứa ít nhất 1 số
        if (password.length() <= 5 || !password.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Mật khẩu phải lớn hơn 5 ký tự và chứa ít nhất 1 số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //kiem tra pass2
        if (!password.equals(password2)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (accountService.isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void setJbtBackLogin() {
        jbtBackLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginWindow ui = new LoginWindow();
                ui.setVisible(true);
                SwingUtilities.getWindowAncestor(jbtRegister).dispose();
            }
        });

    }

}
