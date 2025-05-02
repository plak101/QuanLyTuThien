package charity.view.Login;

import charity.component.GPanel;
import charity.component.GButton;
import charity.controller.LoginController.LoginController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginFrame extends JFrame {

    private JLabel jlbHeader;
    private JLabel jlbUsername;
    private JLabel jlbPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton jbtLogin;
    private GButton gbtLogin;
    private JLabel jlbSignUp;
    private GPanel loginPanel;
    LoginController controller;

    public LoginFrame() {
        init();
        controller= new LoginController(this, txtUsername, txtPassword, gbtLogin, jlbSignUp);
        controller.setEvent();
    }

    public void init() {
        setSize(400, 470);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginPanel = new GPanel();
        loginPanel.setSize(400, 470);
        Font font28 = new Font("Segoe UI", Font.BOLD, 28);
        Font font14B = new Font("Segoe UI", Font.BOLD, 14);
        Font font12B = new Font("Segoe UI", Font.BOLD, 12);
        Font font14 = new Font("Segoe UI", Font.PLAIN, 14);

        loginPanel.setLayout(null);
        jlbHeader = new JLabel("ĐĂNG NHẬP");
        jlbHeader.setFont(new Font("Segoe UI", Font.BOLD, 28));
        jlbHeader.setBounds(0, 30, 400, 40);
        jlbHeader.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        loginPanel.add(jlbHeader);

        jlbUsername = new JLabel("Tên đăng nhập");
        jlbUsername.setFont(font14B);
        jlbUsername.setBounds(50, 100, (400 - 50 * 2), 30);
        loginPanel.add(jlbUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(50, 130, 400 - 50 * 2, 40);
        txtUsername.setFont(font14);
        loginPanel.add(txtUsername);

        jlbPassword = new JLabel("Mật khẩu");
        jlbPassword.setFont(font14B);
        jlbPassword.setBounds(50, 180, (400 - 50 * 2), 30);
        loginPanel.add(jlbPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 210, 400 - 50 * 2, 40);
        txtPassword.setFont(font14);
        loginPanel.add(txtPassword);

        gbtLogin = new GButton("Đăng nhập");
        gbtLogin.setBounds(60, 290, 400 - 60 * 2, 40);
        gbtLogin.setFont(font14B);
        gbtLogin.setBackground(Color.decode("#2d99ae"));
        loginPanel.add(gbtLogin);

        jlbSignUp = new JLabel("Bạn chưa có tài khoản ?");
        jlbSignUp.setFont(font12B);
        jlbSignUp.setBounds(60, 350, (400 - 60 * 2), 20);
        jlbSignUp.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        loginPanel.add(jlbSignUp);
        add(loginPanel);

    }


}
