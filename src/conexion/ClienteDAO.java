package conexion;

import clases.Cliente;
import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
    
    public List<Cliente> select() throws SQLException;
    
    public void insert(Cliente cliente) throws SQLException;
    
    public void update(Cliente cliente) throws SQLException;
    
    public void delete(Cliente cliente) throws SQLException;
    
}
