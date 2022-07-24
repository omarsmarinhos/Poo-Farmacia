package clases;

public class Usuario {
    
    private int idUsuario;
    private Empleado empleado;
    private String user;
    private String password;

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Empleado empleado, String user, String password) {
        this.empleado = empleado;
        this.user = user;
        this.password = password;
    }
    
    public Usuario(int idUsuario, Empleado empleado, String user, String password) {
        this.idUsuario = idUsuario;
        this.empleado = empleado;
        this.user = user;
        this.password = password;
    }

    public Usuario(int idUsuario, String user, String password) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
}
