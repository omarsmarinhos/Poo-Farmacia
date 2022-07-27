package clases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VentaDAO {
    
    public List<Venta> select(ArrayList<Empleado> empleados, ArrayList<Cliente> clientes, ArrayList<Producto> productos) throws SQLException;
    
    public void insert(Venta venta) throws SQLException;
    
    public List<Venta> search(ArrayList<Empleado> empleados, ArrayList<Cliente> clientes, ArrayList<Producto> productos, String buscar) throws SQLException;
}
