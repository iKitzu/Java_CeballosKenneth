import java.util.Date;

// Clase para los pacientes (hereda de Persona)
public class Paciente extends Persona {
    private Date fechaNacimiento;
    private String pabellon;

    public Paciente(String nombreCompleto, String direccion, Date fechaNacimiento, String pabellon) {
        super(nombreCompleto, direccion);
        this.fechaNacimiento = fechaNacimiento;
        this.pabellon = pabellon;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPabellon() {
        return pabellon;
    }

    public void setPabellon(String pabellon) {
        this.pabellon = pabellon;
    }

    // Calcula la edad del paciente
    public int getEdad() {
        long diff = new Date().getTime() - fechaNacimiento.getTime();
        return (int) (diff / (1000L * 60 * 60 * 24 * 365));
    }
}
