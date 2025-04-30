package charity.controller;

import charity.ui.Login;
import charity.viewLogin.LoginFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class AdminUIController {
    private JLabel jlbLogout;

    public AdminUIController(JLabel jlbLogout) {
        this.jlbLogout = jlbLogout;
        setEvent();
    }
    
    private void setEvent(){
        setJlbLogoutEvent();
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
