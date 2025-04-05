package charity.ui;
import javax.swing.*;
import java.awt.*;

public class Test extends JDialog { // Sửa thành JDialog nếu cần

    public Test(Frame owner, boolean modal) { // Thêm tham số nếu cần
        super(owner, modal); // Gọi constructor của JDialog nếu cần
        khoiTaoGiaoDien();
    }

    private void khoiTaoGiaoDien() {
        setTitle("Đăng nhập hệ thống");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Tên đăng nhập:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        JPasswordField passwordText = new JPasswordField();
        JButton loginButton = new JButton("Đăng nhập");

        add(userLabel);
        add(userText);
        add(passwordLabel);
        add(passwordText);
        add(loginButton);

        setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
    }
}
