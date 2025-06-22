package charity.component;

import com.itextpdf.text.BaseColor;
import java.awt.Color;

/**
 *
 * @author phaml
 */
public class ColorCustom {
// Thêm mới

    public static Color colorBtnAdd() {
        return new Color(46, 204, 113);
    }

    public static Color colorBtnAddHover() {
        return new Color(39, 174, 96); // Đậm hơn 1 chút
    }

// Cập nhật
    public static Color colorBtnUpdate() {
        return new Color(52, 152, 219);
    }

    public static Color colorBtnUpdateHover() {
        return new Color(41, 128, 185);
    }

// Xóa
    public static Color colorBtnDelete() {
        return new Color(231, 76, 60);
    }

    public static Color colorBtnDeleteHover() {
        return new Color(192, 57, 43);
    }

// Reset
    public static Color colorBtnReset() {
        return new Color(149, 165, 166);
    }

    public static Color colorBtnResetHover() {
        return new Color(127, 140, 141);
    }

    public static Color backgroundTable() {
        return new Color(240, 247, 250);
    }

    public static Color backgroundTableHeader() {
        return Color.decode("#B4EBE6");
    }

    public static Color backroundHeaderTitle() {
        return new Color(33, 123, 191);
    }

    public static Color colorBtnEdit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static Color colorBtnInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static BaseColor tableHeader(){
        return new BaseColor(0, 168,232);
    }
    
    public static Color defaultBtn(){
        return Color.decode("#2d99ae");
    }
    public static Color defaultBtnHover(){
        return Color.decode("#5dc1d3");
    }
}
