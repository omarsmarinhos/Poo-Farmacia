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
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(
//                    "jdbc:mysql://7qtgzkyctnod.us-east-3.psdb.cloud/poo-lianfarma?useSSL=true&sslMode=VERIFY_IDENTITY",
//                    "jyw8llfvew1w",
//                    "pscale_pw_k3x3W3R3oYK03yZd4-m5iSXeHIWV1plcTmu-6gWhVls");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL + DB + "?useSSL=false", USER, PASSWORD);
//            System.out.println("conexion ok");
        } catch ( SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            return connection;
        }
        
    }

}
