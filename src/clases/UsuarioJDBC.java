package clases;

import conexion.Conexion;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioJDBC implements UsuarioDAO {

    private static final String SQL_SELECT = "SELECT * FROM usuarios";
    private static final String SQL_INSERT = "INSERT INTO usuarios( id_empleado, user, password) "
            + "VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuarios SET user=?, "
            + "password = ? "
            + "WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id_usuario=?";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    private boolean error = false;

    @Override
    public ArrayList<Usuario> select(ArrayList<Empleado> empleados) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            connection = Conexion.getConexion();
            statement = connection.createStatement();
            String query = SQL_SELECT;
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int idEmpleado = getIdEmpleado(empleados, rs.getInt("id_empleado"));
                usuarios.add(new Usuario(
                        rs.getInt("id_usuario"),
                        empleados.get(idEmpleado),
                        rs.getString("user"),
                        rs.getString("password")));
            }
            rs.close();
            statement.close();
            connection.close();
            System.out.println("Usuarios-obtenidos");
        } catch (SQLException ex) {
            System.out.println("Error-selectUsuarios" + ex.toString());
        }

        return usuarios;
    }

    @Override
    public void insert(Usuario usuario) {
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, usuario.getEmpleado().getidEmpleado());
            preparedStatement.setString(2, usuario.getUser());
            preparedStatement.setString(3, usuario.getPassword());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Usuario-agregado");

        } catch (MySQLIntegrityConstraintViolationException e) {
            error = true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void update(Usuario usuario) {
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, usuario.getUser());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.setInt(3, usuario.getIdUsuario());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Usuario-actualizado");

        } catch (MySQLIntegrityConstraintViolationException e) {
            error = true;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void delete(Usuario usuario) {
        try {
            connection = Conexion.getConexion();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, usuario.getIdUsuario());
            preparedStatement.executeUpdate();
            System.out.println("Usuario-eliminado");
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private int getIdEmpleado(ArrayList<Empleado> empleados, int idEmpleado) {
        int id = 0;
        for (int i = 0; i < empleados.size(); i++) {
            if (idEmpleado == empleados.get(i).getidEmpleado()) {
                id = i;
                break;
            }
        }
        return id;
    }

    public boolean getError() {
        boolean aux = error;
        error = false;
        return aux;
    }

}
