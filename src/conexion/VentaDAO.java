package conexion;

import clases.Cliente;
import clases.Empleado;
import clases.Producto;
import clases.Venta;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VentaDAO {
    
    public List<Venta> select(ArrayList<Empleado> empleados, ArrayList<Cliente> clientes, ArrayList<Producto> productos) throws SQLException;
    
    public void insert(Venta venta) throws SQLException;
    
    public void update(Venta venta) throws SQLException;
    
    public void delete(Venta venta) throws SQLException;
}
