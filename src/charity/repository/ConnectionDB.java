package charity.repository;

import charity.utils.DBUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author phaml
 */
public class ConnectionDB {

    private static Properties properties;
    private static Connection connection;

    public ConnectionDB() { 
        getConnection();
    }

    public static Connection getConnection() {
        properties = new Properties();

        try {
            properties.load(new FileInputStream("resource\\database.properties"));
        } catch (IOException ex) {
            DBUtils.showError(ex, "Lỗi đọc file cấu hình database.properties");
            return null;
        }

        try {
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            return connection;

        } catch (ClassNotFoundException ex) {
            DBUtils.handleShowError(ex, "Thiếu thư viện JDBC");
            return null;
        } catch (SQLException ex) {
            DBUtils.handleShowError(ex, "Lỗi kết nối MYSQL");
            return null;
        }
    }

}
