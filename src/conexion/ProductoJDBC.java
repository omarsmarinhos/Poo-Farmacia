package conexion;

import clases.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoJDBC {

    private static final String SQL_SELECT = "SELECT * FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos( presentacion, nombre_producto, concentracion, stock, precio_venta, fecha_vencimiento) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE productos SET presentacion=?, "
            + "nombre_producto=?, concentracion=?, stock=?, precio_venta=?, fecha_vencimiento=? "
            + "WHERE id_producto = ?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE id_producto=?";

    public ArrayList<Producto> select() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            Statement sql = Conexion.getConexion().createStatement();
            String query = SQL_SELECT;
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

        return productos;
    }

    public void insert(Producto producto) {
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getConexion().prepareStatement(SQL_INSERT);
            stmt.setString(1, producto.getPresentacion());
            stmt.setString(2, producto.getNombreProducto());
            stmt.setString(3, producto.getConcentracion());
            stmt.setInt(4, producto.getStock());
            stmt.setFloat(5, producto.getPrecioVenta());
            stmt.setString(6, producto.getFechaVencimiento());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Producto-agregado:");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }

    public void update(Producto producto) {
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getConexion().prepareStatement(SQL_UPDATE);
            stmt.setString(1, producto.getPresentacion());
            stmt.setString(2, producto.getNombreProducto());
            stmt.setString(3, producto.getConcentracion());
            stmt.setInt(4, producto.getStock());
            stmt.setFloat(5, producto.getPrecioVenta());
            stmt.setString(6, producto.getFechaVencimiento());
            stmt.setInt(7, producto.getIdProducto());

            stmt.executeUpdate();
            System.out.println("Prodcuto-actualizado:");
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 

    }

    public void delete(Producto producto) {
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getConexion().prepareStatement(SQL_DELETE);
            stmt.setInt(1, producto.getIdProducto());
            stmt.executeUpdate();
            System.out.println("Producto-eliminado");
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
    public ArrayList<Producto> search(String string){
        ArrayList<Producto> productos = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = Conexion.getConexion().createStatement();
            String query = "SELECT * FROM productos WHERE nombre_producto LIKE '" + string + "%'";
            ResultSet rs = stmt.executeQuery(query);
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
            stmt.close();
            System.out.println("Producto-buscado");
        } catch (SQLException ex) {
            System.out.println("Error-buscarProdcuto" + ex.toString());
        }
        return productos;
    }
}
