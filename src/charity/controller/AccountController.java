/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.controller;

import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class AccountController {
    private Dialog dialog;
    private JButton btnSubmit;
    private JTextField jtfUsername;
    private JTextField jtfPassword;
    private JLabel jlbMsg;

    public AccountController(Dialog dialog, JButton btnSubmit, JTextField jtfUsername, JTextField jtfPassword, JLabel jlbMsg) {
        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.jtfUsername = jtfUsername;
        this.jtfPassword = jtfPassword;
        this.jlbMsg = jlbMsg;
    }
    
    private void setEvent(){
//        btnSubmit.addMouseListener(new MouseAdapter(){
//            
////            @Override
////            public void mouseClicked(MouseEvent e){
//////                if(jtfUsername.getText().length() == 0 || if(jtfPassword.getText().length() == 0 || ){
//////                     jlbMsg.setText("")
//////                }
////            }
//        }
    }
}
