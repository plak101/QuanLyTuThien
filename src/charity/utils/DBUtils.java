package charity.utils;

import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author phaml
 */
public class DBUtils {

    //-------------------------------------------------------
    //|     Hiển thị thông báo lỗi SQL dưới dạng Dialog     |
    //-------------------------------------------------------
    public static void showSQLError(SQLException ex, String message) {
        Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, message, ex);

        String errorMessage = "Lỗi CSDL: " + ex.getMessage();
        JOptionPane.showMessageDialog(null, errorMessage, "Lỗi SQL", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleShowSQLError(SQLException ex, String message) {
        Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, message, ex);

        String errorMessage = "Lỗi CSDL: " + message;
        JOptionPane.showMessageDialog(null, errorMessage, "Lỗi SQL", JOptionPane.ERROR_MESSAGE);
    }

    //-------------------------------------------------------
    //|         Hiển thị thông báo lỗi chung                |
    //-------------------------------------------------------
    public static void showError(Exception ex, String message) {
        Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, message, ex);
        String errorMessage = "Lỗi hệ thống: " + ex.getMessage();
        JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleShowError(Exception ex, String message) {
        Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, message, ex);
        String errorMessage = "Lỗi hệ thống: " + message;
        JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void handleDuplicateEntry(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            String message = e.getMessage();
            if (message.contains("account.email")) {
                JOptionPane.showMessageDialog(null, "Email đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (message.contains("organization.email")) {
                JOptionPane.showMessageDialog(null, "Email tổ chức đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (message.contains("user.phone") || message.contains("organization.hotline")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Dữ liệu bị trùng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lỗi không xác định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
