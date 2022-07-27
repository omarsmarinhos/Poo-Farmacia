package clases;

import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;

public class ProductoJDBC implements ProductoDao{

    private static final String SQL_SELECT = "SELECT * FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos( presentacion, nombre_producto, concentracion, stock, precio_venta, fecha_vencimiento) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE productos SET presentacion=?, "
            + "nombre_producto=?, concentracion=?, stock=?, precio_venta=?, fecha_vencimiento=? "
            + "WHERE id_producto = ?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE id_producto=?";
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    @Override
    public ArrayList<Producto> select() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            connection = Conexion.getConexion();
            statement = connection.createStatement();
            String query = SQL_SELECT;
            ResultSet rs = statement.executeQuery(query);

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
            statement.close();
            connection.close();
            System.out.println("Productos-obtenidos");
        } catch (SQLException ex) {
            System.out.println("Error-getProductosDB" + ex.toString());
        }

        return productos;
    }

    @Override
    public void insert(Producto producto) {
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, producto.getPresentacion());
            preparedStatement.setString(2, producto.getNombreProducto());
            preparedStatement.setString(3, producto.getConcentracion());
            preparedStatement.setInt(4, producto.getStock());
            preparedStatement.setFloat(5, producto.getPrecioVenta());
            preparedStatement.setString(6, producto.getFechaVencimiento());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Producto-agregado:");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }

    @Override
    public void update(Producto producto) {
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, producto.getPresentacion());
            preparedStatement.setString(2, producto.getNombreProducto());
            preparedStatement.setString(3, producto.getConcentracion());
            preparedStatement.setInt(4, producto.getStock());
            preparedStatement.setFloat(5, producto.getPrecioVenta());
            preparedStatement.setString(6, producto.getFechaVencimiento());
            preparedStatement.setInt(7, producto.getIdProducto());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Prodcuto-actualizado:");

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 

    }

    @Override
    public void delete(Producto producto) {
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, producto.getIdProducto());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            connection.close();
            System.out.println("Producto-eliminado");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
    @Override
    public ArrayList<Producto> search(String string){
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            connection = Conexion.getConexion();
            statement = connection.createStatement();
            String query = "SELECT * FROM productos WHERE nombre_producto LIKE '" + string + "%'";
            ResultSet rs = statement.executeQuery(query);
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
            statement.close();
            connection.close();
            System.out.println("Producto-buscado");
        } catch (SQLException ex) {
            System.out.println("Error-buscarProdcuto" + ex.toString());
        }
        return productos;
    }
}
