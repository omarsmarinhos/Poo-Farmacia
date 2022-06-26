package clases;

import java.util.Date;

public class Boleta extends ComprobantePago {

    private String codigoBoleta;

    public Boleta(String codigoBoleta) {
        this.codigoBoleta = codigoBoleta;
    }

    public Boleta(String codigoBoleta, Date fecha, String formaPago, float importeTotal) {
        super(fecha, formaPago, importeTotal);
        this.codigoBoleta = codigoBoleta;
    }

    public String getCodigoBoleta() {
        return codigoBoleta;
    }

    public void setCodigoBoleta(String codigoBoleta) {
        this.codigoBoleta = codigoBoleta;
    }

    @Override
    public String toString() {
        return "Código: " + codigoBoleta + " - Forma de pago: " + super.getFormaPago() + 
                " - Fecha: " + super.getFecha();
    }

    @Override
    public void modificar() {
        
    }

    

}
