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
    private JButton sendTemporaryPasswordButton; // Đã đổi tên nút
    private JLabel temporaryPasswordLabel; // Thành phần mới để hiển thị mật khẩu tạm thời
    private JButton closeButton; // Nút mới để đóng dialog

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private String enteredEmail;
    private String temporaryPassword; // Để lưu trữ mật khẩu tạm thời đã tạo

    public static final String PANEL_EMAIL_INPUT = "EmailInput";
    public static final String PANEL_TEMPORARY_PASSWORD = "TemporaryPassword"; // Hằng số panel mới

    public ForgotPasswordDialog(JFrame parent) {
        super(parent, "Quên mật khẩu", true); // Dialog modal
        setSize(450, 250); // Điều chỉnh kích thước
        setLocationRelativeTo(parent);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // --- Panel Nhập Email ---
        JPanel emailInputPanel = new JPanel(new BorderLayout(10, 10));
        emailInputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel emailFieldPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        emailFieldPanel.add(new JLabel("Nhập email của bạn để nhận mật khẩu tạm thời:")); // Cập nhật văn bản
        emailField = new JTextField(25);
        emailFieldPanel.add(emailField);
        emailInputPanel.add(emailFieldPanel, BorderLayout.CENTER);

        JPanel emailButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sendTemporaryPasswordButton = new JButton("Gửi mật khẩu tạm thời"); // Cập nhật văn bản nút
        emailButtonPanel.add(sendTemporaryPasswordButton);
        emailInputPanel.add(emailButtonPanel, BorderLayout.SOUTH);
        mainPanel.add(emailInputPanel, PANEL_EMAIL_INPUT);

        // --- Panel Hiển thị Mật khẩu Tạm thời ---
        JPanel temporaryPasswordPanel = new JPanel(new BorderLayout(10, 10));
        temporaryPasswordPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel tempPwdDisplayPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        tempPwdDisplayPanel.add(new JLabel("Mật khẩu tạm thời của bạn là:"));
        temporaryPasswordLabel = new JLabel("<html><b><font color='blue'></font></b></html>"); // Để hiển thị mật khẩu
        temporaryPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        temporaryPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        tempPwdDisplayPanel.add(temporaryPasswordLabel);
        temporaryPasswordPanel.add(tempPwdDisplayPanel, BorderLayout.CENTER);

        JPanel tempPwdButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        closeButton = new JButton("Đóng");
        tempPwdButtonPanel.add(closeButton);
        temporaryPasswordPanel.add(tempPwdButtonPanel, BorderLayout.SOUTH);
        mainPanel.add(temporaryPasswordPanel, PANEL_TEMPORARY_PASSWORD);

        // Thêm Listener cho nút "Gửi mật khẩu tạm thời"
        sendTemporaryPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredEmail = emailField.getText().trim();
                if (enteredEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(ForgotPasswordDialog.this, "Vui lòng nhập email của bạn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose(); // Đóng dialog để controller có thể xử lý
                }
            }
        });

        // Thêm Listener cho nút "Đóng" trên panel hiển thị mật khẩu tạm thời
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng dialog
            }
        });
    }

    public String getEnteredEmail() {
        return enteredEmail;
    }

    public void setTemporaryPassword(String tempPassword) {
        this.temporaryPassword = tempPassword;
        temporaryPasswordLabel.setText("<html><b><font color='blue'>" + tempPassword + "</font></b></html>");
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}
