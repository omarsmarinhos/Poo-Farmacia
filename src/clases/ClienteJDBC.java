package clases;

import conexion.Conexion;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteJDBC implements ClienteDAO{

    private static final String SQL_SELECT = "SELECT * FROM cliente";
    private static final String SQL_INSERT = "INSERT INTO cliente(tipo_persona, nombres, apellidos, dni, ruc, razon_social) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET tipo_persona=?, "
            + "nombres=?, apellidos=?, dni=?, ruc=?, razon_social=? "
            + "WHERE id_cliente = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente=?";
    
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    
    private boolean error = false;
    
    @Override
    public ArrayList<Cliente> select(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            connection = Conexion.getConexion();
            statement = connection.createStatement();
            String query = SQL_SELECT;
            ResultSet rs = statement.executeQuery(query);

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
            statement.close();
            connection.close();
            System.out.println("Clientes-obtenidos");
        } catch (SQLException ex) {
            System.out.println("Error-selectClientes" + ex.toString());
        }
        return clientes;
    }

    @Override
    public void insert(Cliente cliente){
        try {
            connection = Conexion.getConexion();        
            preparedStatement= connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, cliente.getTipoPersona());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getDni());
            preparedStatement.setString(5, cliente.getRuc());
            preparedStatement.setString(6, cliente.getRazonSocial());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Cliente-agregado");
            
        } catch (MySQLIntegrityConstraintViolationException e) {
            error = true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void update(Cliente cliente){
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, cliente.getTipoPersona());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getDni());
            preparedStatement.setString(5, cliente.getRuc());
            preparedStatement.setString(6, cliente.getRazonSocial());
            preparedStatement.setInt(7, cliente.getIdCliente());

            preparedStatement.executeUpdate();
            System.out.println("Cliente-actualizado");
            preparedStatement.close();
            connection.close();
        }catch (MySQLIntegrityConstraintViolationException e) {
            error = true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }

    @Override
    public void delete(Cliente cliente){
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, cliente.getIdCliente());
            
            preparedStatement.executeUpdate();
            System.out.println("Cliente-eliminado");
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
    @Override
    public ArrayList<Cliente> search(String string){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            connection = Conexion.getConexion();
            statement = connection.createStatement();
            String query = "SELECT * FROM cliente WHERE dni LIKE '" + string + "%'";
            ResultSet rs = statement.executeQuery(query);
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
            statement.close();
            connection.close();
            System.out.println("Producto-buscado");
        } catch (SQLException ex) {
            System.out.println("Error-buscarProdcuto" + ex.toString());
        }
        return clientes;
    }
    
    public boolean getError(){
        boolean aux = error;
        error = false;
        return aux;
    }
    
}
