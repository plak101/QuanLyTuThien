package charity.loginController;

import charity.model.Account;
import charity.model.Role;
import charity.service.AccountService;
import charity.viewMain.AdminUI;
import charity.viewUser.UserUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginController {

    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton jbtLogin;
    private JButton jbtRegister;
    private JDialog dialog;

    private AccountService accountService;

    public LoginController(JDialog dialog, JTextField txtUsername, JTextField txtPassword, JButton jbtLogin, JButton jbtRegister) {
        this.txtUsername = txtUsername;
        this.txtPassword = txtPassword;
        this.jbtLogin = jbtLogin;
        this.jbtRegister = jbtRegister;
        this.dialog = dialog;
        accountService = new AccountService();
    }

    public void setJbtLoginEvent() {
        jbtLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //kiem tra rong
                if (txtUsername.getText().length() == 0 || txtPassword.getText().length() == 0) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập dữ liệu bắt buộc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    Account account = accountService.checkAccount(txtUsername.getText(), txtPassword.getText());
                    if (account == null) {
                        JOptionPane.showMessageDialog(dialog, "Tên đăng nhập và mật khẩu không đúng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                        JOptionPane.showMessageDialog(dialog, account.getUsername()+ account.getPassword(), "Thông báo", JOptionPane.WARNING_MESSAGE);
                        System.out.println(txtUsername.getText());
                        System.out.println(txtPassword.getText());
                    } else {
                        dialog.dispose();

                        if (account.getRole() == Role.User) {
                            //mo user ui
                            UserUI ui = new UserUI(account.getId());
                            ui.setLocationRelativeTo(null);
                            ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ui.setVisible(true);
                        } else {
                            //mo admin ui
                            AdminUI ui = new AdminUI(account.getId());
                            ui.setLocationRelativeTo(null);
                            ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            ui.setVisible(true);
                        }
                    }

                }

            }
        });
    }
    
    public void setJbtRegister(){
        jbtRegister.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
                
                
            }
            
        });
    }
}
