package conexion;

import clases.Empleado;
import java.sql.SQLException;
import java.util.List;

public interface EmpleadoDAO {
    
    public List<Empleado> select() throws SQLException;
    
    public void insert(Empleado empleado) throws SQLException;
    
    public void update(Empleado empleado) throws SQLException;
    
    public void delete(Empleado empleado) throws SQLException;
    
    public String getEmpleadoActual(int id_empleado) throws SQLException;
    
    public List<Empleado> search(String buscar) throws SQLException;
    
}
