package charity.view.Login;

import charity.component.GButton;
import charity.component.GPanel;
import charity.controller.LoginController.LoginController;
import charity.controller.LoginController.RegisterController;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class RegisterFrame extends JFrame{
    private JLabel jlbHeader;
    private JLabel jlbUsername;
    private JLabel jlbEmail;
    private JLabel jlbPassword;
    private JLabel jlbPasswordConfirm;
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtPasswordConfirm;
    private GButton gbtRegister;
    private JLabel jlbLogin;
    private GPanel loginPanel;
    RegisterController controller;

    public RegisterFrame(){
        init();
        controller= new RegisterController(this, txtUsername, txtEmail, txtPassword, txtPasswordConfirm, gbtRegister, jlbLogin);
        controller.setEvent();
    }
    
    
    public void init() {
        setSize(420, 540);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginPanel = new GPanel();
        loginPanel.setSize(420, 500);
        Font font28 = new Font("Segoe UI", Font.BOLD, 28);
        Font font14B = new Font("Segoe UI", Font.BOLD, 14);
        Font font12B = new Font("Segoe UI", Font.BOLD, 12);
        Font font14 = new Font("Segoe UI", Font.PLAIN, 14);

        loginPanel.setLayout(null);
        jlbHeader = new JLabel("ĐĂNG KÝ");
        jlbHeader.setFont(new Font("Segoe UI", Font.BOLD, 28));
        jlbHeader.setBounds(0, 20, 420, 40);
        jlbHeader.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        loginPanel.add(jlbHeader);

        jlbUsername = new JLabel("Tên đăng nhập");
        jlbUsername.setFont(font14B);
        jlbUsername.setBounds(50, 70, (400 - 50 * 2), 30);
        loginPanel.add(jlbUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(50, 100, 400 - 50 * 2, 40);
        txtUsername.setFont(font14);
        loginPanel.add(txtUsername);
        
        jlbEmail = new JLabel("Email");
        jlbEmail.setFont(font14B);
        jlbEmail.setBounds(50, 150, (400 - 50 * 2), 30);
        loginPanel.add(jlbEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(50, 180, 400 - 50 * 2, 40);
        txtEmail.setFont(font14);
        loginPanel.add(txtEmail);

        jlbPassword = new JLabel("Mật khẩu");
        jlbPassword.setFont(font14B);
        jlbPassword.setBounds(50, 230, (400 - 50 * 2), 30);
        loginPanel.add(jlbPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 260, 400 - 50 * 2, 40);
        txtPassword.setFont(font14);
        loginPanel.add(txtPassword);
        
        jlbPasswordConfirm = new JLabel("Xác nhận mật khẩu");
        jlbPasswordConfirm.setFont(font14B);
        jlbPasswordConfirm.setBounds(50, 310, (400 - 50 * 2), 30);
        loginPanel.add(jlbPasswordConfirm);

        txtPasswordConfirm = new JPasswordField();
        txtPasswordConfirm.setBounds(50, 340, 400 - 50 * 2, 40);
        txtPasswordConfirm.setFont(font14);
        loginPanel.add(txtPasswordConfirm);

        gbtRegister = new GButton("Đăng ký");
        gbtRegister.setBounds(60, 410, 400 - 60 * 2, 40);
        gbtRegister.setFont(font14B);
        gbtRegister.setBackground(Color.decode("#2d99ae"));
        loginPanel.add(gbtRegister);

        jlbLogin = new JLabel("Bạn đã có tài khoản ?");
        jlbLogin.setFont(font12B);
        jlbLogin.setBounds(60, 460, (400 - 60 * 2), 20);
        jlbLogin.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        loginPanel.add(jlbLogin);
        
        add(loginPanel);

    }
}
