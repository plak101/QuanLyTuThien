/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.controller.LoginController;
import charity.view.Login.NewRegister; // Import lớp NewRegister (view)
import charity.view.Login.NewLogin;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color; // Import Color

// THÊM CÁC IMPORTS SAU
import charity.model.Account; // Import lớp Account
import charity.model.Role; // Import lớp Role
import charity.service.AccountService; // Import lớp AccountService
// HẾT PHẦN THÊM IMPORTS

/**
 *
 * @author Admin
 */
public class NewRegisterController {

    private NewRegister registerView; // Tham chiếu đến đối tượng giao diện (NewRegister.java)
    private JTextField txtRegUsername;
    private JTextField txtRegEmail;
    private JPasswordField txtRegPassword;
    private JPasswordField txtRegConfirmPassword;
    private JButton jbtRegister;
    private JLabel jlbBackToLogin;

    
    // THÊM THUỘC TÍNH accountService
    private AccountService accountService; // Khai báo đối tượng AccountService
    // HẾT PHẦN THÊM THUỘC TÍNH

    
    // Constructor của Controller
    public NewRegisterController(NewRegister registerView, JTextField txtRegUsername, JTextField txtRegEmail,
                                 JPasswordField txtRegPassword, JPasswordField txtRegConfirmPassword,
                                 JButton jbtRegister, JLabel jlbBackToLogin) {
        this.registerView = registerView;
        this.txtRegUsername = txtRegUsername;
        this.txtRegEmail = txtRegEmail;
        this.txtRegPassword = txtRegPassword;
        this.txtRegConfirmPassword = txtRegConfirmPassword;
        this.jbtRegister = jbtRegister;
        this.jlbBackToLogin = jlbBackToLogin;
        
        // KHỞI TẠO accountService TRONG CONSTRUCTOR
        accountService = new AccountService(); // Khởi tạo AccountService
        // HẾT PHẦN KHỞI TẠO
    }

    // Phương thức để thiết lập các sự kiện cho các thành phần UI
    public void setEvent() {
        // Xử lý sự kiện cho nút Đăng ký
        jbtRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkInput()) { // Nếu checkInput() trả về true (tất cả hợp lệ)
                    handleRegister(); // Thì mới thực hiện logic đăng ký
                }
            }
        });

        // Xử lý sự kiện click cho nhãn "Quay lại Đăng nhập"
        jlbBackToLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleBackToLogin(); // Gọi phương thức xử lý quay lại đăng nhập
            }
        });
        
        // Thêm MouseListener cho jlbBackToLogin theo yêu cầu
        jlbBackToLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerView.switchToLogin(); // Gọi phương thức của view để chuyển màn hình
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jlbBackToLogin.setForeground(Color.BLUE); // Đổi màu khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Đổi lại màu ban đầu của link khi di chuột ra
                jlbBackToLogin.setForeground(Color.BLACK.darker());
            }
        });
    }
    
    private boolean checkInput() {
        String username = txtRegUsername.getText().trim();
        String email = txtRegEmail.getText().trim();
        String password = String.valueOf(txtRegPassword.getPassword());
        String confirmPassword = String.valueOf(txtRegConfirmPassword.getPassword());

        // 1. Kiểm tra các trường không được để trống
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(registerView, "Không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            if (username.isEmpty()) txtRegUsername.requestFocus();
            else if (email.isEmpty()) txtRegEmail.requestFocus();
            else if (password.isEmpty()) txtRegPassword.requestFocus();
            else txtRegConfirmPassword.requestFocus();
            return false;
        }

        // 2. Kiểm tra tên đăng nhập (chỉ kiểm tra không trống, có thể thêm min length nếu muốn)
        if (username.length() < 8) {
            JOptionPane.showMessageDialog(registerView, "Tên đăng nhập phải có ít nhất 8 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtRegUsername.requestFocus(); // Đặt focus vào trường tên đăng nhập
            return false;
        }

        // 3. Kiểm tra định dạng email (kiểm tra cơ bản có '@' và '.' sau '@')
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        if (atIndex < 0 || dotIndex < atIndex || dotIndex == email.length() - 1 || email.indexOf(" ") != -1) {
            JOptionPane.showMessageDialog(registerView, "Email không hợp lệ! Vui lòng nhập đúng định dạng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtRegEmail.requestFocus();
            return false;
        }


        // 4. Kiểm tra độ dài mật khẩu (tối thiểu 8 ký tự)
//        if (password.length() < 8) {
//            JOptionPane.showMessageDialog(registerView, "Mật khẩu phải ít nhất phải 8 ký tự !", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            txtRegPassword.setText(""); // Xóa mật khẩu đã nhập
//            txtRegConfirmPassword.setText(""); // Xóa xác nhận mật khẩu
//            txtRegPassword.requestFocus();
//            return false;
//        }
            

            if (password.length() < 8) {
                JOptionPane.showMessageDialog(registerView, "Mật khẩu phải ít nhất 8 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtRegPassword.setText(""); // Xóa mật khẩu đã nhập
                txtRegConfirmPassword.setText(""); // Xóa xác nhận mật khẩu
                txtRegPassword.requestFocus();
                return false;
            }

            // Kiểm tra từng điều kiện cụ thể của mật khẩu
            boolean hasUppercase = false;
            boolean hasLowercase = false;
            boolean hasDigit = false;
            boolean hasSpecialChar = false;

            // Duyệt qua từng ký tự trong mật khẩu để kiểm tra
            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasUppercase = true;
                } else if (Character.isLowerCase(c)) {
                    hasLowercase = true;
                } else if (Character.isDigit(c)) {
                    hasDigit = true;
                } else if ("!@#$%^&*()_+-=[]{};':\"\\|,.<>/?".indexOf(c) >= 0) { // Danh sách các ký tự đặc biệt
                    hasSpecialChar = true;
                }
            }

            // Xây dựng thông báo lỗi chi tiết
            StringBuilder errorMessage = new StringBuilder("Mật khẩu phải bao gồm:");
            boolean isValid = true;

            if (!hasUppercase) {
                errorMessage.append("\n- Ít nhất 1 chữ hoa");
                isValid = false;
            }
            if (!hasLowercase) {
                errorMessage.append("\n- Ít nhất 1 chữ thường");
                isValid = false;
            }
            if (!hasDigit) {
                errorMessage.append("\n- Ít nhất 1 số");
                isValid = false;
            }
            if (!hasSpecialChar) {
                errorMessage.append("\n- Ít nhất 1 ký tự đặc biệt (!@#$%^&*...)");
                isValid = false;
            }

            if (!isValid) {
                JOptionPane.showMessageDialog(registerView, errorMessage.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtRegPassword.setText(""); // Xóa mật khẩu đã nhập
                txtRegConfirmPassword.setText(""); // Xóa xác nhận mật khẩu
                txtRegPassword.requestFocus();
                return false;
            }

            

        // 5. Kiểm tra mật khẩu và xác nhận mật khẩu khớp nhau
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(registerView, "Mật khẩu nhập lại không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtRegPassword.setText("");
            txtRegConfirmPassword.setText("");
            txtRegPassword.requestFocus();
            return false;
        }
        
        // 6. Kiểm tra tên đăng nhập đã tồn tại chưa
        if (accountService.isUsernameTaken(username)) { // Gọi phương thức isUsernameTaken từ AccountService để kiểm tra tên người dùng
            JOptionPane.showMessageDialog(registerView, "Tên đăng nhập đã tồn tại! Vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE); // Hiển thị thông báo lỗi
            txtRegUsername.requestFocus(); // Đặt focus vào trường tên người dùng
            return false; // Trả về false
        }
        
        return true; // Tất cả điều kiện hợp lệ
    }
    
    
    // Phương thức xử lý logic đăng ký
    private void handleRegister() {
        String username = txtRegUsername.getText();
        String email = txtRegEmail.getText();
        String password = new String (txtRegPassword.getPassword());
        String confirmPassword = new String (txtRegConfirmPassword.getPassword());

//        if (username.isEmpty() || email.isEmpty() || password.length == 0 || confirmPassword.length == 0) {
//            JOptionPane.showMessageDialog(registerView, "Vui lòng điền đầy đủ thông tin.", "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

        if (!String.valueOf(password).equals(String.valueOf(confirmPassword))) {
            JOptionPane.showMessageDialog(registerView, "Mật khẩu và xác nhận mật khẩu không khớp.", "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
            // Xóa trường mật khẩu để người dùng nhập lại
            txtRegPassword.setText("");
            txtRegConfirmPassword.setText("");
            return;
        }
        
        
        // BẮT ĐẦU CHỨC NĂNG LƯU TÀI KHOẢN
        // Tạo một đối tượng Account mới để lưu thông tin người dùng
        Account newAccount = new Account(); // Tạo đối tượng Account
        newAccount.setUsername(username); // Thiết lập tên người dùng
        newAccount.setEmail(email); // Thiết lập email
        newAccount.setPassword(password); // Thiết lập mật khẩu
        newAccount.setRole(Role.User); // Thiết lập vai trò mặc định là User

        // Gọi AccountService để thêm tài khoản vào cơ sở dữ liệu
        boolean success = accountService.addAccount(newAccount); // Gọi addAccount để lưu tài khoản

        if (success) { // Nếu thêm tài khoản thành công
            JOptionPane.showMessageDialog(registerView, "Đăng ký tài khoản mới thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE); // Hiển thị thông báo thành công

            // Xóa dữ liệu sau khi đăng ký thành công
            txtRegUsername.setText(""); // Xóa trường tên người dùng
            txtRegEmail.setText(""); // Xóa trường email
            txtRegPassword.setText(""); // Xóa trường mật khẩu
            txtRegConfirmPassword.setText(""); // Xóa trường xác nhận mật khẩu

            // Chuyển người dùng về màn hình đăng nhập
            // Sử dụng phương thức của view để chuyển màn hình, thay vì tạo NewLogin trực tiếp nếu NewRegister đã có phương thức này
            registerView.switchToLogin(); // Gọi phương thức của view để chuyển màn hình
            // registerView.dispose(); // Cần đảm bảo rằng switchToLogin() cũng đóng cửa sổ hiện tại.
                                     // Nếu không, cần gọi dispose() ở đây.
        } else { // Nếu thêm tài khoản thất bại (ví dụ: lỗi cơ sở dữ liệu)
            JOptionPane.showMessageDialog(registerView, "Đăng ký thất bại! Vui lòng thử lại.", "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE); // Hiển thị thông báo lỗi
        }
        // KẾT THÚC CHỨC NĂNG LƯU TÀI KHOẢN
        
        
        // Logic đăng ký tài khoản
        // Đây là nơi bạn sẽ gọi đến Model (ví dụ: database) để lưu thông tin người dùng mới
        JOptionPane.showMessageDialog(registerView,"Đăng ký tài khoản mới thành công!\n");
    }

    // Phương thức xử lý logic quay lại màn hình đăng nhập
    private void handleBackToLogin() {
        //JOptionPane.showMessageDialog(registerView, "");
    }
}
