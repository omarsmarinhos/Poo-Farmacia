package clases;

public class Proveedor {

    private int RUC;
    private String nombrelaboratorio;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private String codigoProveedor;

    public Proveedor() {
    }

    public Proveedor(int RUC, String nombrelaboratorio, String razonSocial, String direccion, String telefono, String codigoProveedor) {
        this.RUC = RUC;
        this.nombrelaboratorio = nombrelaboratorio;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoProveedor = codigoProveedor;
    }

    public void registrarProveedor(){
        
    }
    
    public void buscarProveedor(){
        
    }
    
    public void eliminarProveedor(){
        
    }

    public int getRUC() {
        return RUC;
    }

    public void setRUC(int RUC) {
        this.RUC = RUC;
    }

    public String getNombrelaboratorio() {
        return nombrelaboratorio;
    }

    public void setNombrelaboratorio(String nombrelaboratorio) {
        this.nombrelaboratorio = nombrelaboratorio;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }
    
    
}
