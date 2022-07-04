package conexion;

import clases.Cliente;
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
    
    @Override
    public ArrayList<Cliente> select(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Statement sql = Conexion.getConexion().createStatement();
            String query = SQL_SELECT;
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
            System.out.println("Error-selectClientes" + ex.toString());
        }

        return clientes;
    }

    @Override
    public void insert(Cliente cliente){
        try {
            PreparedStatement stmt = Conexion.getConexion().prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getTipoPersona());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDni());
            stmt.setString(5, cliente.getRuc());
            stmt.setString(6, cliente.getRazonSocial());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Cliente-agregado");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void update(Cliente cliente){
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getConexion().prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getTipoPersona());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getDni());
            stmt.setString(5, cliente.getRuc());
            stmt.setString(6, cliente.getRazonSocial());
            stmt.setInt(7, cliente.getIdCliente());

            stmt.executeUpdate();
            System.out.println("Cliente-actualizado");
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }

    @Override
    public void delete(Cliente cliente){
        try {
            PreparedStatement stmt = Conexion.getConexion().prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.executeUpdate();
            System.out.println("Cliente-eliminado");
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
}
