import java.util.Date;

public class Paciente extends Persona {
    private Date fechaIngreso;
    private String pabellon;

    public Paciente(String nombre, String direccion, Date fechaNacimiento, Date fechaIngreso, String pabellon) {
        super(nombre, direccion, fechaNacimiento);
        this.fechaIngreso = fechaIngreso;
        this.pabellon = pabellon;
    }

    // Getters y Setters
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPabellon() {
        return pabellon;
    }

    public void setPabellon(String pabellon) {
        this.pabellon = pabellon;
    }
}
