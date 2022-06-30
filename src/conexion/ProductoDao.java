package conexion;

import clases.Producto;
import java.sql.SQLException;
import java.util.List;

public interface ProductoDao {
    
    public List<Producto> select() throws SQLException;
    
    public int insert(Producto producto) throws SQLException;
    
    public int update(Producto producto) throws SQLException;
    
    public int delete(Producto producto) throws SQLException;
}
