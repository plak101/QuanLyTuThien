package charity.utils;

import charity.service.AccountService;
import charity.service.UserService;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author phaml
 */
public class ScannerUtils {
    private final static AccountService accountService = new AccountService();
    private final static UserService userService = new UserService();
            
    public static boolean isEmpty(String s, String message) {
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(null, message);
            return true;
        }
        return false;
    }
    
    public static boolean isPasswordValid(String password){
        StringBuilder errorMessage = new StringBuilder("Mật khẩu phải có:");

        boolean hasLength = password.length() >= 8;
        boolean hasLowerCase = password.matches(".*[a-z].*");
        boolean hasUpperCase = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[@#$%^&+=!].*");

        boolean isValid = true;

        if (!hasLength) {
            errorMessage.append("\n- Ít nhất 8 ký tự");
            isValid = false;
        }
        if (!hasLowerCase) {
            errorMessage.append("\n- Chữ cái thường");
            isValid = false;
        }
        if (!hasUpperCase) {
            errorMessage.append("\n- Chữ cái in hoa");
            isValid = false;
        }
        if (!hasDigit) {
            errorMessage.append("\n- Chữ số");
            isValid = false;
        }
        if (!hasSpecialChar) {
            errorMessage.append("\n- Ký tự đặc biệt (@#$%^&+=!)");
            isValid = false;
        }

        if (!isValid) {
            JOptionPane.showMessageDialog(null, errorMessage.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        return isValid;
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
    public static boolean isDateAfterToday(Date date) {
        Date today = new Date();
        if (date.after(today)) {
//            JOptionPane.showMessageDialog(null, "Ngày đã chọn không được lớn hơn ngày hiện tại");
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
    
    public static boolean isPhoneValid(String phone){
        if (phone.isEmpty()){
            MessageDialog.showPlain("Số điện thoại không được để trống");
            return false;
        }
        return true;
    }
    public static boolean isPhoneNumberExist(String phone){
        if (userService.isPhoneNumberExist(phone)){
            MessageDialog.showPlain("Số điện thoại đã tồn tại");
            return true;
        }
        return false;
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
    public static boolean isEmailVaid(String email){
        if (email.isEmpty()) {
            MessageDialog.showPlain("Email không được để trống");
            return false;
        }else if (!email.matches("^[a-zA-Z0-9]+@gmail\\.com$")) {
            MessageDialog.showPlain("Vui lòng nhập Email đúng định dạng (@gmail.com)");
            return false;
        }
        return true;
    }
    
    public static boolean isEmailExist(String email){
        if (accountService.isEmailExist(email)){
            MessageDialog.showPlain("Email đã tồn tại.");
            return true;
        }
        return false;
    }
    
    public static boolean isGenderSelected(JRadioButton male, JRadioButton female, String message) {
        if (!male.isSelected() && !female.isSelected()) {
            JOptionPane.showMessageDialog(null, message);
            return false;
        }
        return true;
    }
    
    public static void setOnlyInputNumber(JTextField txt){
        txt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c!='\b'){
                    e.consume();
                }
            }
            
        });
    }
}
