
package charity.view.Login;
import javax.swing.*;

public class ForgotPasswordDialog2 extends JDialog {
    private JTextField txtUsername = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private boolean submitted = false;

    public ForgotPasswordDialog2(JFrame parent) {
        super(parent, "Quên mật khẩu", true);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Tên đăng nhập:"));
        panel.add(txtUsername);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);

        JButton btnOK = new JButton("Gửi lại mật khẩu");
        btnOK.addActionListener(e -> {
            submitted = true;
            setVisible(false);
        });
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnOK);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    public String getUsername() { return txtUsername.getText().trim(); }
    public String getEmail() { return txtEmail.getText().trim(); }
    public boolean isSubmitted() { return submitted; }
}