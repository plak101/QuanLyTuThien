package charity.utils;

import javax.swing.JOptionPane;

public class MessageDialog {
    
    // Thông báo thành công
    public static void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    // Thông báo lỗi
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Thông báo cảnh báo
    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
    }

    // Thông báo hỏi người dùng
    public static void showQuestion(String message) {
        JOptionPane.showMessageDialog(null, message, "Câu hỏi", JOptionPane.QUESTION_MESSAGE);
    }

    // Thông báo đơn giản không icon
    public static void showPlain(String message) {
        JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.PLAIN_MESSAGE);
    }

    //thong bao xac nhan
    public static boolean showConfirmDialog(String message, String title) {
        int result = JOptionPane.showConfirmDialog(
            null, 
            message, 
            title,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        return result == JOptionPane.YES_OPTION;
    }
}
