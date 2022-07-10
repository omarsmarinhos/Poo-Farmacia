package clases;

import java.util.Date;

public class Producto {

private int idProducto; 
private String presentacion;
private String nombreProducto;
private String concentracion;
private int stock;
private float precioVenta;
private String fechaVencimiento;

    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(String presentacion, String nombreProducto, String concentracion, int stock, float precioVenta, String fechaVencimiento) {
        this.presentacion = presentacion;
        this.nombreProducto = nombreProducto;
        this.concentracion = concentracion;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.fechaVencimiento = fechaVencimiento;
    }

    
    
    public Producto(int idProducto, String nombreProducto, float precioVenta) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
    }
  
    public Producto(int idProducto, String presentacion, String nombreProducto, String concentracion, int stock, float precioVenta, String fechaVencimiento) {
        this.idProducto = idProducto;
        this.presentacion = presentacion;
        this.nombreProducto = nombreProducto;
        this.concentracion = concentracion;
        this.stock = stock;
        this.precioVenta = precioVenta;
        this.fechaVencimiento = fechaVencimiento;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", presentacion=" + presentacion + ", nombreProducto=" + nombreProducto + ", concentracion=" + concentracion + ", stock=" + stock + ", precioVenta=" + precioVenta + ", fechaVencimiento=" + fechaVencimiento + '}';
    }

    
    

}
