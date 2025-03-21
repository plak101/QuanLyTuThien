
package dao;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author phaml
 */
public class DataConnection {
    public static Connection getJDBConnection(){
        
        String url ="jdbc:mysql://localhost:3306/qltt";
        String user = "root";
        String password = "root";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        Connection connection = getJDBConnection();
        if(connection!=null){
            System.out.println("Thanh cong");
        }else{
            System.out.println("That bai");
        }
    }
}
