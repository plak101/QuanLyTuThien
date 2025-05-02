package charity.controller.AdminController;

import charity.service.UserService;
import charity.view.Login.LoginFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AdminUIController {
    private JLabel jlbLogout;
    private int accountId;
    private JTextField txtName;
    private UserService userService= new UserService();
    public AdminUIController(int accountId, JLabel jlbLogout,JTextField txtName) {
        this.jlbLogout = jlbLogout;
        this.accountId = accountId;
        this.txtName = txtName;
        setEvent();
    }
    
    private void setEvent(){
        setJlbLogoutEvent();
        txtName.setText(userService.getUserNameById(accountId));
    }
    
    private void setJlbLogoutEvent(){
        jlbLogout.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(jlbLogout);
                currentFrame.dispose();
                
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
            }
            
        });
    }
}
