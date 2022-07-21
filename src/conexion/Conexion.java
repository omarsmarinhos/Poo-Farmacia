package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static final String URL      = "jdbc:mysql://localhost:3306/";
    public static final String DB       = "farmacia";
    public static final String USER     = "root";
    public static final String PASSWORD = "";
    
    static Connection connection = null;
    
    public static Connection getConexion() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL + DB + "?useSSL=false", USER, PASSWORD);
//            System.out.println("conexion ok");
            return connection;
        } catch ( SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } 
        return null;  
    }

}
