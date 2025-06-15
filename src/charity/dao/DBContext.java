package charity.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;

public class DBContext {
    private static String url;
    private static String username;
    private static String password;

    static {
        try {            // Load properties
            Properties props = new Properties();
            String propertiesPath = "resource/database.properties";
            props.load(new FileInputStream(propertiesPath));

            // Get connection properties
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");

            // Register driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
