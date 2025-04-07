package charity.utils;

import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author phaml
 */
public class ScannerUtils {
    
    public static boolean isEmpty(String s, String message) {
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(null, message);
            return true;
        }
        return false;
    }
    
    public static boolean isPasswordValid(String password, String passwordConfirm){
        if (password.length()==0 ||passwordConfirm.length()==0){
            JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống");
            return false;
        }
        if (password.length()<=5 ||! password.matches(".*\\d.*")){
            JOptionPane.showMessageDialog(null, "Mật khẩu phải lớn hơn 5 ký tự và có ít nhất một chữ số");
            return false;
        }
        if (!password.equals(passwordConfirm)){
            JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không khớp");
            return false;
        }
        return true;
    }
    
    public static boolean isEmpty(JTextField textField, String message) {
        if (textField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, message);
            return true;
        }
        return false;
    }

    public static boolean isDateNull(JDateChooser dateChooser, String message) {
        if (dateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(null, message);
            return true;
        }
        return false;
    }

    public static boolean isDateAfterToday(JDateChooser dateChooser, String message) {
        Date today = new Date();
        if (dateChooser.getDate().after(today)) {
            JOptionPane.showMessageDialog(null, message);
            return true;
        }
        return false;
    }

    public static boolean isPhoneValid(JTextField textField, String message) {
        String phone = textField.getText().trim();
        if (phone.isEmpty() || !phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, message);
            return false;
        }
        return true;
    }
    
    //kiem tra emai
    public static boolean isEmailVaid(JTextField textField){
        String email = textField.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập email!");
            return false;
        }else if (!email.matches("^[a-zA-Z0-9]+@gmail\\.com$")) {
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng (@gmail.com)!");
            return false;
        }
        return true;
    }
    
    public static boolean isGenderSelected(JRadioButton male, JRadioButton female, String message) {
        if (!male.isSelected() && !female.isSelected()) {
            JOptionPane.showMessageDialog(null, message);
            return false;
        }
        return true;
    }
}
