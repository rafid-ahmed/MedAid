/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medaid;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MSI
 */
public class Conn {
    
    private Conn(){}

    public static Connection getConnection() throws SQLException{
        
            return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/medaid", "root", "root");
            
    }

}
