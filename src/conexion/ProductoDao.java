package conexion;

import clases.Producto;
import java.sql.SQLException;
import java.util.List;

public interface ProductoDao {
    
    public List<Producto> select() throws SQLException;
    
    public void insert(Producto producto) throws SQLException;
    
    public void update(Producto producto) throws SQLException;
    
    public void delete(Producto producto) throws SQLException;
    
    public List<Producto> search(String buscar) throws SQLException;
}
