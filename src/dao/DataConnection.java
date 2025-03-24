package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author phaml
 */
public class DataConnection {
    
    //#KET NOI DATABASE
    public static Connection getConnection(){
        String url ="jdbc:mysql://localhost:3306/qltt";
        String user = "root";
        String password = "root";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối MYSQL thành công");
            return connection;
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Thiếu thư viện JDBC");
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối MYSQL");
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    //#HÀM CHẠY CÂU LỆNH SELECT
    public static ResultSet thucThiLenhSelect(String cauLenhSQL) {
        Connection connection = DataConnection.getConnection(); // Đảm bảo có kết nối
        ResultSet resultSet = null;
        
        try {
            if (connection != null) {
                Statement stm = connection.createStatement();
                resultSet = stm.executeQuery(cauLenhSQL);
            } else {
                System.out.println("Lỗi: Không thể kết nối cơ sở dữ liệu!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);        
        } 
        
        return resultSet; // Trả về ResultSet để xử lý bên ngoài
    }
    
}
