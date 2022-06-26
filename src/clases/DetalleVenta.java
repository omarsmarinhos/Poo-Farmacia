package clases;

public class DetalleVenta {
    
    //private int idDetalle;
    private Producto producto; //precio
    private int cantidad;      //cantidad
    private float importe;      //precio * cantidad = importe

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe = producto.getPrecioVenta() * cantidad;
    }

//    public int getIdDetalle() {
//        return idDetalle;
//    }
//
//    public void setIdDetalle(int idDetalle) {
//        this.idDetalle = idDetalle;
//    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getImporte() {
        return importe;
    }

    public void setMonto(float montoTotal) {
        this.importe = montoTotal;
    }
    
    
}
