import java.util.Date;
import java.util.List;

public class Doctor extends Personal {
    private boolean esLider;
    private List<Doctor> equipo;

    public Doctor(String nombre, String direccion, Date fechaNacimiento, Date fechaVinculacion, double salario, boolean esLider, List<Doctor> equipo) {
        super(nombre, direccion, fechaNacimiento, fechaVinculacion, salario);
        this.esLider = esLider;
        this.equipo = equipo;
    }

    // Getters y Setters
    public boolean isEsLider() {
        return esLider;
    }

    public void setEsLider(boolean esLider) {
        this.esLider = esLider;
    }

    public List<Doctor> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<Doctor> equipo) {
        this.equipo = equipo;
    }
}
