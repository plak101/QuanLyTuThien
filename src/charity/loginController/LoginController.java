package charity.loginController;

import charity.model.Account;
import charity.model.Role;
import charity.service.AccountService;
import charity.component.GButton;
import charity.viewLogin.RegisterFrame;
import charity.viewMain.AdminUI;
import charity.viewUser.UserUI;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginController {

    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton jbtRegister;
    private JDialog dialog;
    private JFrame frame;
    private GButton gbtLogin;

    private AccountService accountService;
    private JLabel jlbSignUp;

    public LoginController(JFrame frame, JTextField txtUsername, JTextField txtPassword, GButton gbtLogin, JLabel jlbSignUp) {
        this.frame = frame;
        this.txtUsername = txtUsername;
        this.txtPassword = txtPassword;
        this.gbtLogin = gbtLogin;
        this.jlbSignUp = jlbSignUp;
        accountService = new AccountService();
    }

    public void setEvent() {
        setGbtLoginEvent();
        setJlbSignUp();
    }

    public void setGbtLoginEvent() {
        gbtLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //kiem tra rong
                if (txtUsername.getText().length() == 0 || txtPassword.getText().length() == 0) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập dữ liệu !", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    Account account = accountService.checkAccount(txtUsername.getText(), txtPassword.getText());
                    if (account == null) {
                        JOptionPane.showMessageDialog(dialog, "Tên đăng nhập và mật khẩu không đúng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    } else {
                        frame.dispose();
                        if (account.getRole() == Role.User) {
                            //mo user ui
                            UserUI ui = new UserUI(account.getId());
                            ui.setLocationRelativeTo(null);
                            ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ui.setVisible(true);
                        } else {
//                            mo admin ui
                            AdminUI ui = new AdminUI(account.getId());
                            ui.setLocationRelativeTo(null);
                            ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ui.setVisible(true);
                        }
                    }

                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                gbtLogin.setForeground(Color.BLACK);
                gbtLogin.setBackground(Color.decode("#2d99ae"));
                gbtLogin.changeColor("#2d99ae");

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtLogin.setForeground(Color.WHITE);
                gbtLogin.changeColor("#0c5776");

            }
        });
    }

    public void setJlbSignUp() {
        jlbSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new RegisterFrame().setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jlbSignUp.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlbSignUp.setForeground(Color.BLACK);

            }

        });
    }

}
