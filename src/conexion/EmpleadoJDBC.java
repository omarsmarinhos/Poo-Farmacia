package conexion;

import clases.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoJDBC implements EmpleadoDAO {

    private static final String SQL_SELECT = "SELECT * FROM empleados";
    private static final String SQL_INSERT = "INSERT INTO empleados( nombres, apellidos, dni, tipo_empleado, sueldo, cargo) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE empleados SET nombres=?, "
            + "apellidos=?, dni=?, tipo_empleado=?, sueldo=?, cargo=? "
            + "WHERE id_empleado = ?";
    private static final String SQL_DELETE = "DELETE FROM empleados WHERE id_empleado=?";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    
    @Override
    public ArrayList<Empleado> select() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            connection = Conexion.getConexion();
            statement = connection.createStatement();
            String query = SQL_SELECT;
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                empleados.add(new Empleado(
                        rs.getInt("id_empleado"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("dni"),
                        rs.getString("tipo_empleado"),
                        rs.getFloat("sueldo"),
                        rs.getString("cargo")));
            }
            rs.close();
            statement.close();
            connection.close();
            System.out.println("Empleados-obtenidos");
        } catch (SQLException ex) {
            System.out.println("Error-selectEmpleados" + ex.toString());
        }

        return empleados;
    }

    @Override
    public void insert(Empleado empleado){
        try {
            connection = Conexion.getConexion();
            preparedStatement  = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getDni());
            preparedStatement.setString(4, empleado.getTipoEmpleado());
            preparedStatement.setFloat(5, empleado.getSueldo());
            preparedStatement.setString(6, empleado.getCargo());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Empleado-agregado");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void update(Empleado empleado){
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getDni());
            preparedStatement.setString(4, empleado.getTipoEmpleado());
            preparedStatement.setFloat(5, empleado.getSueldo());
            preparedStatement.setString(6, empleado.getCargo());
            preparedStatement.setInt(7, empleado.getidEmpleado());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Empleado-actualizado:");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }

    @Override
    public void delete(Empleado empleado){
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, empleado.getidEmpleado());
            preparedStatement.executeUpdate();
            System.out.println("Empleado-eliminado");
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
    @Override
    public String getEmpleadoActual(int id_empleado){
        String karma = "";
        
        try {
            connection = Conexion.getConexion();
            statement = connection.createStatement();
            String query = "SELECT nombres, apellidos FROM empleados WHERE id_empleado = '" 
                    + id_empleado + "'";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                karma = rs.getString("nombres") + " " + rs.getString("apellidos");
            }
            rs.close();
            statement.close();
            connection.close();
            System.out.println("Inicio-Sesion: " + karma);
        } catch (SQLException ex) {
            System.out.println("Error-getEmpleadoActual" + ex.toString());
        }
        
        return karma;
    }

}
