package conexion;

import clases.Empleado;
import clases.Usuario;
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

    @Override
    public ArrayList<Empleado> select() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try {
            Statement sql = Conexion.getConexion().createStatement();
            String query = SQL_SELECT;
            ResultSet rs = sql.executeQuery(query);

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
            sql.close();
            System.out.println("Empleados-obtenidos");
        } catch (SQLException ex) {
            System.out.println("Error-selectEmpleados" + ex.toString());
        }

        return empleados;
    }

    @Override
    public void insert(Empleado empleado){
        try {
            PreparedStatement stmt = Conexion.getConexion().prepareStatement(SQL_INSERT);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getDni());
            stmt.setString(4, empleado.getTipoEmpleado());
            stmt.setFloat(5, empleado.getSueldo());
            stmt.setString(6, empleado.getCargo());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Empleado-agregado");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void update(Empleado empleado){
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getConexion().prepareStatement(SQL_UPDATE);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getDni());
            stmt.setString(4, empleado.getTipoEmpleado());
            stmt.setFloat(5, empleado.getSueldo());
            stmt.setString(6, empleado.getCargo());
            stmt.setInt(7, empleado.getidEmpleado());

            stmt.executeUpdate();
            System.out.println("Empleado-actualizado:");
            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }

    @Override
    public void delete(Empleado empleado){
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getConexion().prepareStatement(SQL_DELETE);
            stmt.setInt(1, empleado.getidEmpleado());
            stmt.executeUpdate();
            System.out.println("Empleado-eliminado");
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
    @Override
    public String getEmpleadoActual(int id_empleado){
        
        String karma = "";
        
        try {
            Statement sql = Conexion.getConexion().createStatement();
            String query = "SELECT nombres, apellidos FROM empleados WHERE id_empleado = '" 
                    + id_empleado + "'";
            ResultSet rs = sql.executeQuery(query);

            while (rs.next()) {
                karma = rs.getString("nombres") + " " + rs.getString("apellidos");
            }
            rs.close();
            sql.close();
            System.out.println("Inicio-Sesion: " + karma);
        } catch (SQLException ex) {
            System.out.println("Error-getEmpleadoActual" + ex.toString());
        }
        
        return karma;
    }

}
