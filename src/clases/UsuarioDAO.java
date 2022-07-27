package clases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UsuarioDAO {
    
    public List<Usuario> select(ArrayList<Empleado> empleados) throws SQLException;
    
    public void insert(Usuario empleado) throws SQLException;
    
    public void update(Usuario empleado) throws SQLException;
    
    public void delete(Usuario empleado) throws SQLException;
    
}
