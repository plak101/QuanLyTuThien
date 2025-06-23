/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.view.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Admin
 */
public class ForgotPasswordDialog extends JDialog {
    private JTextField emailField;
    private JButton sendOtpButton;
    private JTextField otpField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton resetPasswordButton;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private String enteredEmail;
    private String enteredOtp;
    private String newPassword;

    public static final String PANEL_EMAIL_INPUT = "EmailInput";
    public static final String PANEL_OTP_RESET = "OtpReset";

    public ForgotPasswordDialog(JFrame parent) {
        super(parent, "Quên mật khẩu", true); // Modal dialog
        setSize(450, 300); // Kích thước điều chỉnh
        setLocationRelativeTo(parent);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // --- Panel Nhập Email ---
        JPanel emailInputPanel = new JPanel(new BorderLayout(10, 10));
        emailInputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel emailFieldPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        emailFieldPanel.add(new JLabel("Nhập email của bạn để nhận mã OTP:"));
        emailField = new JTextField(25);
        emailFieldPanel.add(emailField);
        emailInputPanel.add(emailFieldPanel, BorderLayout.CENTER);

        JPanel emailButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sendOtpButton = new JButton("Gửi OTP");
        emailButtonPanel.add(sendOtpButton);
        emailInputPanel.add(emailButtonPanel, BorderLayout.SOUTH);
        mainPanel.add(emailInputPanel, PANEL_EMAIL_INPUT);

        // --- Panel Nhập OTP và Mật khẩu mới ---
        JPanel otpResetPanel = new JPanel(new BorderLayout(10, 10));
        otpResetPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel otpResetFieldPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        otpResetFieldPanel.add(new JLabel("Nhập mã OTP từ email:"));
        otpField = new JTextField(10);
        otpResetFieldPanel.add(otpField);

        otpResetFieldPanel.add(new JLabel("Mật khẩu mới:"));
        newPasswordField = new JPasswordField(20);
        otpResetFieldPanel.add(newPasswordField);

        otpResetFieldPanel.add(new JLabel("Xác nhận mật khẩu mới:"));
        confirmPasswordField = new JPasswordField(20);
        otpResetFieldPanel.add(confirmPasswordField);

        otpResetPanel.add(otpResetFieldPanel, BorderLayout.CENTER);

        JPanel otpResetButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        resetPasswordButton = new JButton("Đặt lại mật khẩu");
        otpResetButtonPanel.add(resetPasswordButton);
        otpResetPanel.add(otpResetButtonPanel, BorderLayout.SOUTH);
        mainPanel.add(otpResetPanel, PANEL_OTP_RESET);

        // Thêm Listener cho nút "Gửi OTP"
        sendOtpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredEmail = emailField.getText().trim();
                if (enteredEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(ForgotPasswordDialog.this, "Vui lòng nhập email của bạn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Signal to the controller that OTP request was made
                    // The controller will then handle sending OTP and showing the next panel
                    dispose(); // Close this dialog, controller will reopen/handle
                }
            }
        });

        // Thêm Listener cho nút "Đặt lại mật khẩu"
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredOtp = otpField.getText().trim();
                newPassword = new String(newPasswordField.getPassword());
                String confirmNewPassword = new String(confirmPasswordField.getPassword());

                if (enteredOtp.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(ForgotPasswordDialog.this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!newPassword.equals(confirmNewPassword)) {
                    JOptionPane.showMessageDialog(ForgotPasswordDialog.this, "Mật khẩu mới và xác nhận mật khẩu không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Signal to the controller that password reset was requested
                // The controller will then handle OTP verification and password update
                dispose(); // Close this dialog, controller will handle reset
            }
        });
    }

    public String getEnteredEmail() {
        return enteredEmail;
    }

    public String getEnteredOtp() {
        return enteredOtp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    // Phương thức để chuyển đổi giữa các panel
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}
