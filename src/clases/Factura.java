package clases;

import java.util.Date;

public class Factura extends ComprobantePago {

    private String codigoFactura;

    public Factura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Factura(String codigoFactura, Date fecha, String formaPago, float importeTotal) {
        super(fecha, formaPago, importeTotal);
        this.codigoFactura = codigoFactura;
    }

    
    
    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    @Override
    public String toString() {
        return "Código: " + codigoFactura + " - Forma de pago: " + super.getFormaPago() + 
                " - Fecha: " + super.getFecha();
    }

    @Override
    public void modificar() {
    }

}
