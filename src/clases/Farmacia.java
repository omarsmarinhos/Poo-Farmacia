package clases;

public class Farmacia {

    private int codigoSucursal;
    private String nombreComercial;
    private String telefono;
    private String direccion;
    private String ruc;
    private String correo;

    public Farmacia() {
    }

    public Farmacia(String nombreComercial, int codigoSucursal, String telefono, String ruc, String direccion, String correo) {
        this.nombreComercial = nombreComercial;
        this.codigoSucursal = codigoSucursal;
        this.telefono = telefono;
        this.ruc = ruc;
        this.direccion = direccion;
        this.correo = correo;
    }

    public void registrarFarmacia() {

    }

    public void buscarFarmacia() {

    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
