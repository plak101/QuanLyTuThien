/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.ui;
import charity.controller.LoginWindow;

/**
 *
 * @author Admin
 */
public class LoginWindowRun {
    public static void main(String[] args) {
        LoginWindow window = new LoginWindow();
        window.setTitle("Đăng nhập hệ thống");
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
