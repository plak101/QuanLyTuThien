package charity.loginController;

import charity.component.GButton;
import charity.model.Account;
import charity.model.Role;
import charity.service.AccountService;
import charity.viewLogin.LoginFrame;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author phaml
 */
public class RegisterController {

    private JFrame frame;
    private JTextField txtUsername, txtEmail, txtPassword, txtPassword2;
    private GButton gbtRegister;
    private JLabel jlbBackLogin;
    private AccountService accountService;

    public RegisterController(JFrame frame, JTextField txtUsername, JTextField txtEmail, JTextField txtPassword, JTextField txtPassword2, GButton gbtRegister, JLabel jlbBackLogin) {
        this.txtUsername = txtUsername;
        this.txtEmail = txtEmail;
        this.txtPassword = txtPassword;
        this.txtPassword2 = txtPassword2;
        this.gbtRegister = gbtRegister;
        this.jlbBackLogin = jlbBackLogin;
        this.frame = frame;
        accountService = new AccountService();
    }

    public void setEvent() {
        setJbtRegisterEvent();
        setJlbBackLogin();
    }

    public void setJbtRegisterEvent() {
        gbtRegister.addMouseListener(new MouseAdapter() {
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
                        frame.dispose();
                        new LoginFrame().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                gbtRegister.setForeground(Color.BLACK);
                gbtRegister.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtRegister.setForeground(Color.WHITE);
                gbtRegister.changeColor("#0c5776");
                
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

        // Kiểm tra password dài hơn 6 
        if (password.length() <= 4) {
            JOptionPane.showMessageDialog(null, "Mật khẩu phải lớn hơn 4 ký tự !", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //co it nhat ký tự và chứa ít nhất 1 số
        //^ — bắt đầu chuỗi
        //(?=.*[A-Za-z]) — chứa ít nhất một chữ cái (hoa hoặc thường)
        //(?=.*\\d) — chứa ít nhất một chữ số
        //.+ — ít nhất một ký tự bất kỳ để đảm bảo chuỗi không trống
        //$ — kết thúc chuỗi
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
            JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 1 cái và 1 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    private void setJlbBackLogin() {
        jlbBackLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new LoginFrame().setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlbBackLogin.setForeground(Color.BLACK);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jlbBackLogin.setForeground(Color.WHITE);
            }

        });

    }

}
