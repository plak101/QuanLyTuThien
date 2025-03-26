package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author phaml
 */
public class DataConnection {
    private Properties properties;
    private Connection conn;
    
    
    //#KET NOI DATABASE
//    public  void connectionDB() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{
//        properties = new Properties();
//        properties.load(new FileInputStream("resource\\database.properties"));
//        String url = properties.getProperty("url");
//        String username = properties.getProperty("username");
//        String password = properties.getProperty("password");
//        String driver = properties.getProperty("driver");
//        conn = DriverManager.getConnection(url, username, password);
//        Class.forName(driver);
//        System.out.println("Thanh cong");
//    }
    
    
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
