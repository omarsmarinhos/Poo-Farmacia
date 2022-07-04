package clases;

public class Cliente extends Persona {

    private int idCliente;
    private String ruc;
    private String razonSocial;
    private String tipoPersona;

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(int idCliente, String tipoPersona, String nombre, String apellido, 
        String dni, String ruc, String razonSocial) {
        super(nombre, apellido, dni);
        this.idCliente = idCliente;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.tipoPersona = tipoPersona;
    }
    
    public Cliente(String tipoPersona, String nombre, String apellido, 
        String dni, String ruc, String razonSocial) {
        super(nombre, apellido, dni);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.tipoPersona = tipoPersona;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public String toString() {
        return "idCliente= " + idCliente + ", tipo= " + tipoPersona
        + ", Nombre= " + super.getNombre() + ", Apellidos= " + super.getApellido() + ", DNI= " + super.getDni()
        + ", RUC= " + ruc + ", razon_social= " + razonSocial;
                    
    }

    @Override
    public String accionPersona() {
        return "Cliente compra al empleado";
    }

}
