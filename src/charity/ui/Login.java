/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.ui;

import charity.component.FormSignUp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class Login extends JFrame {

//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JButton loginButton;
//    private JLabel messageLabel;

    public Login() {
        super.setUndecorated(true);
        super.setSize(400, 470);
        super.setShape(new RoundRectangle2D.Double(0, 0, 400, 470, 40, 40));
        super.setLocationRelativeTo(null);
//        FormSignUp form = new FormSignUp();
//        add(form);
    }

    public static void main(String args[]) {
        new Login().setVisible(true);
    }
}
