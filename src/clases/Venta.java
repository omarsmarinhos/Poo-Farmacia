package clases;

import java.util.ArrayList;

public class Venta {
    
    private int idVenta;
    private Empleado empleado;
    private Cliente cliente;
    private float importeTotal;
    private ArrayList<DetalleVenta> detalles;
    private String metodoPago;
    private String fecha;

    public Venta(int idVenta, Empleado empleado, Cliente cliente, ArrayList<DetalleVenta> detalles, String metodoPago, String fecha) {
        this.idVenta = idVenta;
        this.empleado = empleado;
        this.cliente = cliente;
        this.detalles = detalles;
        this.metodoPago = metodoPago;
        this.fecha = fecha.substring(0, fecha.length()-2);;
    }

    public Venta(Empleado empleado, Cliente cliente, ArrayList<DetalleVenta> detalles, String metodoPago) {
        this.empleado = empleado;
        this.cliente = cliente;
        this.detalles = detalles;
        this.metodoPago = metodoPago;
    }

    

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

    public float calcularImporteTotal(ArrayList<DetalleVenta> detalles){
        float monto = 0;
        for (int i = 0; i < detalles.size(); i++) {
            monto+= detalles.get(i).getImporte();
        }
        return monto;
    }
    
    public float getImporteTotal() {
        return calcularImporteTotal(detalles);
    }
    
    public void setImporteTotal(float importeTotal){
        this.importeTotal = importeTotal;
    }

    
    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        String karma = "----------------------------------------------------------------------\n"
                + "idVenta: " + idVenta + ", empleado: " + empleado.getNombre() + ", cliente:" + 
                cliente.getNombre() + ", ImporteTotal:" + getImporteTotal()+ 
                "\n----------------------------------------------------------------------";
        
        for (int i = 0; i < detalles.size(); i++) {
            karma += "\nProducto: " + detalles.get(i).getProducto().getNombreProducto() +
                    "\tPrecio: " + detalles.get(i).getProducto().getPrecioVenta() +
                    "\tCantidad: " + detalles.get(i).getCantidad() + 
                    "\tImporte: " + detalles.get(i).getImporte();
        }
        return karma;
    }
    
}
