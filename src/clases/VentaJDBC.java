package clases;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VentaJDBC implements VentaDAO {

    @Override
    public ArrayList<Venta> select(ArrayList<Empleado> empleados, ArrayList<Cliente> clientes, ArrayList<Producto> productos) {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            Statement sqlVenta = Conexion.getConexion().createStatement();
            String queryVenta = "SELECT * FROM ventas";
            ResultSet rsVenta = sqlVenta.executeQuery(queryVenta);

            while (rsVenta.next()) {
                int idVenta = rsVenta.getInt("id_venta");
                int idEmpleado = getIdEmpleado(empleados, rsVenta.getInt("id_empleado"));
                int idCliente = getIdCliente(clientes, rsVenta.getInt("id_cliente"));
                String metodoPago = rsVenta.getString("metodo_pago");
                String fecha = rsVenta.getString("fecha");

                Statement sqlDetail = Conexion.getConexion().createStatement();
                String queryDetail = "SELECT id_producto, cantidad "
                        + "FROM detalles WHERE id_venta = " + idVenta;
                ResultSet rsDetail = sqlDetail.executeQuery(queryDetail);

                ArrayList<DetalleVenta> detalles = new ArrayList<>();
                while (rsDetail.next()) {
                    int idProducto = getIdProducto(productos, rsDetail.getInt("id_producto"));
                    int cantidad = rsDetail.getInt("cantidad");

                    detalles.add(new DetalleVenta(productos.get(idProducto), cantidad));
                }

                ventas.add(new Venta(idVenta, empleados.get(idEmpleado),
                        clientes.get(idCliente), detalles, metodoPago, fecha));
                rsDetail.close();
                sqlDetail.close();
            }
            sqlVenta.close();
            rsVenta.close();
            Conexion.getConexion().close();
            System.out.println("ventas-obtenidass");
        } catch (SQLException ex) {
            System.out.println("mostrar-ventas" + ex.toString());
        }
        return ventas;
    }

    public ArrayList<Venta> selectVentaDia(ArrayList<Empleado> empleados, ArrayList<Cliente> clientes, ArrayList<Producto> productos, String dia) {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            Statement sqlVenta = Conexion.getConexion().createStatement();
            String queryVenta = "SELECT * FROM ventas WHERE fecha LIKE '" + dia + "%'";
            ResultSet rsVenta = sqlVenta.executeQuery(queryVenta);

            while (rsVenta.next()) {
                int idVenta = rsVenta.getInt("id_venta");
                int idEmpleado = getIdEmpleado(empleados, rsVenta.getInt("id_empleado"));
                int idCliente = getIdCliente(clientes, rsVenta.getInt("id_cliente"));
                String metodoPago = rsVenta.getString("metodo_pago");
                String fecha = rsVenta.getString("fecha");

                Statement sqlDetail = Conexion.getConexion().createStatement();
                String queryDetail = "SELECT id_producto, cantidad "
                        + "FROM detalles WHERE id_venta = " + idVenta;
                ResultSet rsDetail = sqlDetail.executeQuery(queryDetail);

                ArrayList<DetalleVenta> detalles = new ArrayList<>();
                while (rsDetail.next()) {
                    int idProducto = getIdProducto(productos, rsDetail.getInt("id_producto"));
                    int cantidad = rsDetail.getInt("cantidad");

                    detalles.add(new DetalleVenta(productos.get(idProducto), cantidad));
                }

                ventas.add(new Venta(idVenta, empleados.get(idEmpleado),
                        clientes.get(idCliente), detalles, metodoPago, fecha));
                rsDetail.close();
                sqlDetail.close();
            }
            sqlVenta.close();
            rsVenta.close();
            Conexion.getConexion().close();
            System.out.println("ventas-obtenidass");
        } catch (SQLException ex) {
            System.out.println("mostrar-ventas" + ex.toString());
        }
        return ventas;
    }

    public ArrayList<Venta> selectVentaPorDias(ArrayList<Empleado> empleados, ArrayList<Cliente> clientes, ArrayList<Producto> productos, int dias) {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            Statement sqlVenta = Conexion.getConexion().createStatement();
            String queryVenta
                    = "SELECT * FROM ventas WHERE fecha BETWEEN DATE_SUB(CURRENT_DATE(),INTERVAL " + dias + " DAY)"
                    + " AND DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY)";
            ResultSet rsVenta = sqlVenta.executeQuery(queryVenta);

            while (rsVenta.next()) {
                int idVenta = rsVenta.getInt("id_venta");
                int idEmpleado = getIdEmpleado(empleados, rsVenta.getInt("id_empleado"));
                int idCliente = getIdCliente(clientes, rsVenta.getInt("id_cliente"));
                String metodoPago = rsVenta.getString("metodo_pago");
                String fecha = rsVenta.getString("fecha");

                Statement sqlDetail = Conexion.getConexion().createStatement();
                String queryDetail = "SELECT id_producto, cantidad "
                        + "FROM detalles WHERE id_venta = " + idVenta;
                ResultSet rsDetail = sqlDetail.executeQuery(queryDetail);

                ArrayList<DetalleVenta> detalles = new ArrayList<>();
                while (rsDetail.next()) {
                    int idProducto = getIdProducto(productos, rsDetail.getInt("id_producto"));
                    int cantidad = rsDetail.getInt("cantidad");

                    detalles.add(new DetalleVenta(productos.get(idProducto), cantidad));
                }

                ventas.add(new Venta(idVenta, empleados.get(idEmpleado),
                        clientes.get(idCliente), detalles, metodoPago, fecha));
                rsDetail.close();
                sqlDetail.close();
            }
            sqlVenta.close();
            rsVenta.close();
            Conexion.getConexion().close();
            System.out.println("ventas-obtenidass");
        } catch (SQLException ex) {
            System.out.println("mostrar-ventas" + ex.toString());
        }
        return ventas;
    }

    public ArrayList<Venta> selectVentaUltimoMes(ArrayList<Empleado> empleados, ArrayList<Cliente> clientes, ArrayList<Producto> productos) {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            Statement sqlVenta = Conexion.getConexion().createStatement();
            String queryVenta
                    = "SELECT * FROM ventas WHERE fecha BETWEEN DATE_SUB(CURRENT_DATE(),INTERVAL 1 MONTH)"
                    + " AND DATE_ADD(CURRENT_DATE(),INTERVAL 1 DAY)";
            ResultSet rsVenta = sqlVenta.executeQuery(queryVenta);

            while (rsVenta.next()) {
                int idVenta = rsVenta.getInt("id_venta");
                int idEmpleado = getIdEmpleado(empleados, rsVenta.getInt("id_empleado"));
                int idCliente = getIdCliente(clientes, rsVenta.getInt("id_cliente"));
                String metodoPago = rsVenta.getString("metodo_pago");
                String fecha = rsVenta.getString("fecha");

                Statement sqlDetail = Conexion.getConexion().createStatement();
                String queryDetail = "SELECT id_producto, cantidad "
                        + "FROM detalles WHERE id_venta = " + idVenta;
                ResultSet rsDetail = sqlDetail.executeQuery(queryDetail);

                ArrayList<DetalleVenta> detalles = new ArrayList<>();
                while (rsDetail.next()) {
                    int idProducto = getIdProducto(productos, rsDetail.getInt("id_producto"));
                    int cantidad = rsDetail.getInt("cantidad");

                    detalles.add(new DetalleVenta(productos.get(idProducto), cantidad));
                }

                ventas.add(new Venta(idVenta, empleados.get(idEmpleado),
                        clientes.get(idCliente), detalles, metodoPago, fecha));
                rsDetail.close();
                sqlDetail.close();
            }
            sqlVenta.close();
            rsVenta.close();
            Conexion.getConexion().close();
            System.out.println("ventas-obtenidass");
        } catch (SQLException ex) {
            System.out.println("mostrar-ventas" + ex.toString());
        }
        return ventas;
    }

    @Override
    public void insert(Venta venta) {
        try {
            String query = "CALL registrarVenta(?, ?, ?, ?)";
            PreparedStatement sql = Conexion.getConexion().prepareStatement(query);
            sql.setInt(1, venta.getEmpleado().getidEmpleado());
            sql.setInt(2, venta.getCliente().getIdCliente());
            sql.setFloat(3, venta.getImporteTotal());
            sql.setString(4, venta.getMetodoPago());
            sql.executeUpdate();
            sql.close();
            Conexion.getConexion().close();

            System.out.println("venta creada");
        } catch (SQLException ex) {
            System.out.println("Error-registrarVenta" + ex.toString());
        }

        try {
            for (DetalleVenta detalle : venta.getDetalles()) {
                String query = "CALL registrarDetalle(?, ?, ?)";
                PreparedStatement sql = Conexion.getConexion().prepareStatement(query);
                sql.setInt(1, detalle.getProducto().getIdProducto());
                sql.setInt(2, detalle.getCantidad());
                sql.setFloat(3, detalle.getImporte());
                sql.executeUpdate();
                sql.close();
                Conexion.getConexion().close();
            }
            System.out.println("detalle de venta agregado");
        } catch (SQLException ex) {
            System.out.println("Error-registrarDetalles" + ex.toString());
        }
    }

    @Override
    public ArrayList<Venta> search(ArrayList<Empleado> empleados, ArrayList<Cliente> clientes, ArrayList<Producto> productos, String buscar) {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            Statement sqlVenta = Conexion.getConexion().createStatement();
            String queryVenta = "SELECT * FROM ventas WHERE id_cliente LIKE '" + buscar + "%'";
            ResultSet rsVenta = sqlVenta.executeQuery(queryVenta);

            while (rsVenta.next()) {
                int idVenta = rsVenta.getInt("id_venta");
                int idEmpleado = getIdEmpleado(empleados, rsVenta.getInt("id_empleado"));
                int idCliente = getIdCliente(clientes, rsVenta.getInt("id_cliente"));
                String metodoPago = rsVenta.getString("metodo_pago");
                String fecha = rsVenta.getString("fecha");

                Statement sqlDetail = Conexion.getConexion().createStatement();
                String queryDetail = "SELECT id_producto, cantidad "
                        + "FROM detalles WHERE id_venta = " + idVenta;
                ResultSet rsDetail = sqlDetail.executeQuery(queryDetail);

                ArrayList<DetalleVenta> detalles = new ArrayList<>();
                while (rsDetail.next()) {
                    int idProducto = getIdProducto(productos, rsDetail.getInt("id_producto"));
                    int cantidad = rsDetail.getInt("cantidad");

                    detalles.add(new DetalleVenta(productos.get(idProducto), cantidad));
                }

                ventas.add(new Venta(idVenta, empleados.get(idEmpleado),
                        clientes.get(idCliente), detalles, metodoPago, fecha));
                rsDetail.close();
                sqlDetail.close();
            }
            sqlVenta.close();
            rsVenta.close();
            Conexion.getConexion().close();
            System.out.println("ventas-obtenidass");
        } catch (SQLException ex) {
            System.out.println("mostrar-ventas" + ex.toString());
        }
        return ventas;
    }

    private int getIdProducto(ArrayList<Producto> productos, int idProducto) {
        int id = 0;
        for (int i = 0; i < productos.size(); i++) {
            if (idProducto == productos.get(i).getIdProducto()) {
                id = i;
                break;
            }
        }
        return id;
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

    private int getIdCliente(ArrayList<Cliente> clientes, int idCliente) {
        int id = 0;
        for (int i = 0; i < clientes.size(); i++) {
            if (idCliente == clientes.get(i).getIdCliente()) {
                id = i;
                break;
            }
        }
        return id;
    }

}
