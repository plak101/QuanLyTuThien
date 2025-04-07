/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.ui;

import charity.viewLogin.RegisterWindow;

/**
 *
 * @author Admin
 */
public class RegisterWindowRun {
    public static void main(String[] args) {
        RegisterWindow win = new RegisterWindow();
        win.setTitle("Đăng ký tài khoản");
        win.setResizable(false);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }
}
