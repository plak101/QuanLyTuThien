/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.view;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.controller.AdminController.Program;
import charity.utils.MessageDialog;
import charity.view.Login.NewLogin;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author phaml
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            MessageDialog.showError("Không thể khởi tạo FlatLaf");
        }
        
        UIManager.put("Table.showVerticalLines", false);// tat vien cot bang
        UIManager.put("Table.showHorizontalLines", true); //hien vien hang bang
        UIManager.put("ScrollBar.thumbArc", 999); //lam dau cuon bo tron toi da
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));//Đặt khoảng trống giữa viền và khung;
        UIManager.put("Table.selectionBackground", ColorCustom.backgroundTable());
        UIManager.put("Table.selectionForeground", Color.BLACK);
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.font", FontCustom.Arial13B());
        UIManager.put("TableHeader.background", ColorCustom.backgroundTableHeader());
        UIManager.put("TableHeader.separatorColor", Color.decode("#B4EBE6")); // mau duong phan cach tieu de trung voi mau backgorund tieu de
        UIManager.put("TableHeader.bottomSeparatorColor", Color.decode("#B4EBE6")); // mau duong phan cach tieu de trung voi mau backgorund tieu de
        UIManager.put("Button.arc", 5);
        Program program =new Program();
//        NewLogin login = new NewLogin();
//        login.setVisible(true);
        
    }

}
