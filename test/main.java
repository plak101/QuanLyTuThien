import charity.model.Gender;
import charity.model.User;
import charity.service.UserService;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class main {
    public main() {
        try {
            User user = new User();
            user.setName("Nguyễn Văn A");
            user.setAddress("123 Đường ABC");
            user.setPhone("0987654321");
            user.setGender("Nam"); // Dùng Enum

            // Chuyển từ String thành java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = sdf.parse("10/01/2005");
            
            // Chuyển từ java.util.Date thành java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            // Set birthday cho user
            user.setBirthday(sqlDate);

            // Thêm vào database
            UserService u = new UserService();
            boolean success = u.addUser(user);
            if (success) {
                System.out.println("Thêm người dùng thành công!");
            } else {
                System.out.println("Lỗi khi thêm người dùng.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new main();
    }
}
