package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static final String URL      = "jdbc:mysql://localhost:3306/";
    public static final String DB       = "farmacia";
    public static final String USER     = "root";
    public static final String PASSWORD = "";
    
    public static Connection getConexion() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
            //System.out.println("conexion ok");
        } catch ( SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            return connection;
        }
        
    }
}
