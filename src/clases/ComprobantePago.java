package clases;

import java.util.Date;

public abstract class ComprobantePago {

    private Date fecha;
    private String formaPago;
    private float importeTotal;
    
    public ComprobantePago() {
    }

    public ComprobantePago(Date fecha, String formaPago, float importeTotal) {
        this.fecha = fecha;
        this.formaPago = formaPago;
        this.importeTotal = importeTotal;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }
    
    @Override
    public abstract String toString();
    
    public abstract void modificar();
}
