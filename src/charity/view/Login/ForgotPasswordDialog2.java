package charity.view.Login;

import javax.swing.*;
import java.awt.*;

public class ForgotPasswordDialog2 extends JDialog {

    private JTextField txtUsername = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private boolean submitted = false;

    public ForgotPasswordDialog2(JFrame parent) {
        super(parent, "Quên mật khẩu", true);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.anchor = GridBagConstraints.WEST;

        // Label Tên đăng nhập
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Tên đăng nhập:"), gbc);

        // TextField Tên đăng nhập
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtUsername, gbc);

        // Label Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Email:"), gbc);

        // TextField Email
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtEmail, gbc);

        // Nút gửi lại mật khẩu (nằm giữa)
        JButton btnOK = new JButton("Gửi lại mật khẩu");
        btnOK.addActionListener(e -> {
            submitted = true;
            setVisible(false);
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(btnOK, gbc);

        btnOK.setPreferredSize(new Dimension(150, 30));
        txtEmail.setPreferredSize(new Dimension(0, 30));
        txtUsername.setPreferredSize(new Dimension(0, 30));

        setContentPane(panel);
        setResizable(false);
        pack();
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return txtUsername.getText().trim();
    }

    public String getEmail() {
        return txtEmail.getText().trim();
    }

    public boolean isSubmitted() {
        return submitted;
    }
}
