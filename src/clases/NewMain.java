package clases;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import conexion.ProductoJDBC;
import conexion.VentaJDBC;

public class NewMain {

    static ArrayList<Empleado> empleados = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Venta> ventas = new ArrayList<>();
    
    public static void main(String[] args) {
        
        VentaJDBC ventajdbc = new VentaJDBC();
        ProductoJDBC productoJDBC = new ProductoJDBC();
        productos = productoJDBC.select();
        //Obtener datos de la base de Datos
//        getProductosDB();
//        getEmpleadosDB();
        getClientesDB();
        
//                ventas = ventajdbc.selectVentaDia(empleados, clientes, productos, dia);

        //getVentasDB();
//        productos.forEach(producto -> System.out.println(producto));
//        empleados.forEach(empleado -> System.out.println(empleado));
//        clientes.forEach(cliente -> System.out.println(cliente));
         


        
        //mostrar todas las ventas
        //ventas.forEach(venta -> System.out.println(venta + "\n"));
        
        
        
    }

    //metodo de prueba
    static void registrarVenta(Venta venta) {
        try {
            String query = "CALL registrarVenta(?, ?, ?, ?)";
            PreparedStatement sql = Conexion.getConexion().prepareStatement(query);
            sql.setInt(1, venta.getEmpleado().getidEmpleado());
            sql.setInt(2, venta.getCliente().getIdCliente());
            sql.setFloat(3, venta.getImporteTotal());
            sql.setString(4, venta.getMetodoPago());
            sql.executeUpdate();
            sql.close();
            System.out.println("venta creada");
        } catch (SQLException ex) {
            System.out.println("Error-registrarVenta" + ex.toString());
        }

        try {
            for (DetalleVenta detalle : venta.getDetalles()) {
                String query = "CALL registrarDetalle(?, ?, ?)";
                PreparedStatement sql = Conexion.getConexion().prepareStatement(query);
                sql.setInt(1, detalle.getProducto().getIdProducto());
                sql.setInt(2, detalle.getCantidad());
                sql.setFloat(3, detalle.getImporte());
                sql.executeUpdate();
                sql.close();
            }
            System.out.println("detalle de venta agregado");
        } catch (SQLException ex) {
            System.out.println("Error-registrarDetalles" + ex.toString());
        }
    }

    //Obtiene los productos de la base de datos
    static void getProductosDB() {
        productos = new ArrayList<>();
        try {
            Statement sql = Conexion.getConexion().createStatement();
            String query = "SELECT * FROM productos";
            ResultSet rs = sql.executeQuery(query);

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("presentacion"),
                        rs.getString("nombre_producto"),
                        rs.getString("concentracion"),
                        rs.getInt("stock"),
                        rs.getFloat("precio_venta"),
                        rs.getString("fecha_vencimiento")));
            }
            rs.close();
            sql.close();
            System.out.println("Productos-obtenidos");
        } catch (SQLException ex) {
            System.out.println("Error-getProductosDB" + ex.toString());
        }
    }
    
    //Obtiene los empleados de la base de datos
//    static void getEmpleadosDB() {
//        empleados = new ArrayList<>();
//        try {
//            Statement sql = Conexion.getConexion().createStatement();
//            String query = "SELECT * FROM empleados";
//            ResultSet rs = sql.executeQuery(query);
//
//            while (rs.next()) {
//                empleados.add(new Empleado(
//                        rs.getInt("id_empleado"), 
//                        rs.getString("nombres"), 
//                        rs.getString("apellidos"), 
//                        rs.getString("dni"), 
//                        rs.getString("tipo_empleado"), 
//                        rs.getFloat("sueldo")));
//            }
//            rs.close();
//            sql.close();
//            System.out.println("Empleados-obtenidos");
//        } catch (SQLException ex) {
//            System.out.println("Error getEmpleadosDB" + ex.toString());
//        }
//    }
    
    //Obtiene los clientes de la base de datos
    static void getClientesDB() {
        clientes = new ArrayList<>();
        try {
            Statement sql = Conexion.getConexion().createStatement();
            String query = "SELECT * FROM cliente";
            ResultSet rs = sql.executeQuery(query);

            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id_cliente"), 
                        rs.getString("tipo_persona"), 
                        rs.getString("nombres"), 
                        rs.getString("apellidos"), 
                        rs.getString("dni"), 
                        rs.getString("ruc"), 
                        rs.getString("razon_social")));
            }
            rs.close();
            sql.close();
            System.out.println("Clientes-obtenidos");
        } catch (SQLException ex) {
            System.out.println("Error getClientesDB" + ex.toString());
        }
    }
    
    //Obtiene las ventas y los detalles de venta de la base de datos
    static void getVentasDB(){
        
        try {
            Statement sqlVenta = Conexion.getConexion().createStatement();
            String queryVenta = "SELECT * FROM ventas";
            ResultSet rsVenta = sqlVenta.executeQuery(queryVenta);

            while (rsVenta.next()) {
                int idVenta = rsVenta.getInt("id_venta");
                int idEmpleado = getIdEmpleado(empleados, rsVenta.getInt("id_empleado")); 
                int idCliente = getIdCliente(clientes, rsVenta.getInt("id_cliente"));
                String metodoPago = rsVenta.getString("metodo_pago");
                String fecha = rsVenta.getString("fecha");
                
                Statement sqlDetail = Conexion.getConexion().createStatement();
                String queryDetail = "SELECT id_producto, cantidad "
                        + "FROM detalles WHERE id_venta = " + idVenta;
                ResultSet rsDetail = sqlDetail.executeQuery(queryDetail);
                
                ArrayList<DetalleVenta> detalles = new ArrayList<>();
                while(rsDetail.next()){
                    int idProducto = getIdProducto(productos, rsDetail.getInt("id_producto"));
                    int cantidad = rsDetail.getInt("cantidad");
                    
                    detalles.add(new DetalleVenta(productos.get(idProducto), cantidad));
                }
                
//                ventas.add(new Venta(idVenta, empleados.get(idEmpleado), 
//                        clientes.get(idCliente), detalles, metodoPago, fecha));
//                rsDetail.close();
//                sqlDetail.close();
            }
            sqlVenta.close();
            rsVenta.close();
            System.out.println("ventas-obtenidass");
        } catch (SQLException ex) {
            System.out.println("mostrar-ventas" + ex.toString());
        }
    }
    
    /*
    metodos auxiliares para obtener el id
    */
    static int getIdProducto(ArrayList<Producto> productos, int idProducto){
        int id = 0;
        for (int i = 0; i < productos.size(); i++) {
            if (idProducto == productos.get(i).getIdProducto()) {
                id = i;
                break;
            }
        }
        return id;
    }
    
    static int getIdEmpleado(ArrayList<Empleado> empleados, int idEmpleado){
        int id = 0;
        for (int i = 0; i < empleados.size(); i++) {
            if (idEmpleado == empleados.get(i).getidEmpleado()) {
                id = i;
                break;
            }
        }
        return id;
    }
    
    static int getIdCliente(ArrayList<Cliente> clientes, int idCliente){
        int id = 0;
        for (int i = 0; i < clientes.size(); i++) {
            if (idCliente == clientes.get(i).getIdCliente()) {
                id = i;
                break;
            }
        }
        return id;
    }
}
