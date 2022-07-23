package conexion;

import clases.Empleado;
import clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioJDBC implements UsuarioDAO{

    private static final String SQL_SELECT = "SELECT * FROM usuarios";
    private static final String SQL_INSERT = "INSERT INTO usuarios( id_empleado, user, password) "
            + "VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuarios SET user=?, "
            + "password=? "
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
    public void insert(Usuario empleado) {
        
    }

    @Override
    public void update(Usuario empleado) {
        
    }

    @Override
    public void delete(Usuario empleado) {
        
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
    
}
