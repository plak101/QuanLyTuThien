/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.controller.LoginController;
import charity.view.Login.NewLogin;
import charity.view.Login.NewRegister;
import java.awt.Color;
//import charity.view.login.NewLogin; // <<< THAY ĐỔI Ở ĐÂY: import lớp NewLogin (view) từ package charity.view.login
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Admin
 */
public class NewLoginController {

    private NewLogin loginView; // Tham chiếu đến đối tượng giao diện (NewLogin.java)
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton jbtLogin;
    private JLabel jlbForgotPassword;
    private JLabel jlbSignUpLink;

    public NewLoginController(NewLogin loginView, JTextField txtUsername, JPasswordField txtPassword, JButton jbtLogin, JLabel jlbForgotPassword, JLabel jlbSignUpLink) {
        this.loginView = loginView;
        this.txtUsername = txtUsername;
        this.txtPassword = txtPassword;
        this.jbtLogin = jbtLogin;
        this.jlbForgotPassword = jlbForgotPassword;
        this.jlbSignUpLink = jlbSignUpLink; // Khởi tạo biến mới
    }

    public void setEvent() {
        jbtLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        jlbForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleForgotPassword();
            }
        });
        
        jlbSignUpLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginView.switchToRegister(); // Gọi phương thức của view để chuyển màn hình
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jlbSignUpLink.setForeground(Color.ORANGE); // Đổi màu khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Đổi lại màu ban đầu của link khi di chuột ra
                jlbSignUpLink.setForeground(Color.BLUE.darker());
            }
        });
    }

    private void handleLogin() {
        String username = txtUsername.getText();
        char[] password = txtPassword.getPassword();

        if (username.equals("admin") && String.valueOf(password).equals("123")) {
            JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công!");
            // loginView.dispose();
            // new MainFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(loginView, "Tên đăng nhập hoặc mật khẩu không đúng.", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
        }

        for (int i = 0; i < password.length; i++) {
            password[i] = 0;
        }
    }

    private void handleForgotPassword() {
        JOptionPane.showMessageDialog(loginView, "Bạn đã nhấp vào Quên mật khẩu! (Logic xử lý quên mật khẩu sẽ ở đây)");
    }
}