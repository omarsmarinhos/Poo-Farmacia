package clases;

public class Empleado extends Persona {

    private int idEmpleado;
    private float sueldo;
    private String tipoEmpleado;
    private String cargo;

    public Empleado(String nombre, String apellido, String dni, String tipoEmpleado, float sueldo, String cargo) {
        super(nombre, apellido, dni);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
        this.cargo = cargo;
    }

    public Empleado(int idEmpleado, String nombre, String apellido, String dni, String tipoEmpleado, float sueldo, String cargo) {
        super(nombre, apellido, dni);
        this.idEmpleado = idEmpleado;
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
        this.cargo = cargo;
    }

    public Empleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public int getidEmpleado() {
        return idEmpleado;
    }

    public void setidEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
    
    @Override
    public String toString() {
        return "idEmpleado= " + idEmpleado + ", sueldo=" + sueldo + ", tipo=" + 
                tipoEmpleado + ", nombre= " + super.getNombre();
    }

    @Override
    public String accionPersona() {
        return "Empleado vende al cliente";
    }

    
}
