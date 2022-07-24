package clases;

import conexion.ClienteJDBC;
import conexion.Conexion;
import conexion.EmpleadoJDBC;
import conexion.ProductoJDBC;
import conexion.VentaJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NewMain {

    static Pass encriptar = new Pass();

    public static void main(String[] args) {

//        VentaJDBC ventaJDBC = new VentaJDBC();
//        EmpleadoJDBC empleadoJDBC = new EmpleadoJDBC();
//        ClienteJDBC clienteJDBC = new ClienteJDBC();
//        ProductoJDBC productoJDBC = new ProductoJDBC();
//        
//        ArrayList<Venta> ventas = new ArrayList<>();
//        ArrayList<Empleado> empleados = new ArrayList<>();
//        ArrayList<Cliente> clientes = new ArrayList<>();
//        ArrayList<Producto> productos = new ArrayList<>();
//
//        
//        empleados = empleadoJDBC.select();
//        clientes = clienteJDBC.select();
//        productos = productoJDBC.select();
//        ventas = ventaJDBC.select(empleados, clientes, productos);
//        
//        ventas.forEach(venta ->{
//            System.out.println(venta);
//        });

//            try {
//            Statement sql = Conexion.getConexion().createStatement();
//            String queryVenta = "SELECT CURRENT_TIMESTAMP()";
//            ResultSet rs = sql.executeQuery(queryVenta);
//            
//            while (rs.next()) {
//                
//                String fecha = rs.getString(1);
//		fecha = fecha.substring(0, fecha.length()-2);
//		System.out.println(fecha);
//            }
//            sql.close();
//            rs.close();
//        } catch (SQLException ex) {
//            System.out.println("" + ex.toString());
//        }

    }

}
