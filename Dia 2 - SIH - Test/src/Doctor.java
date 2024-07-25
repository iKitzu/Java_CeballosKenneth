import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Doctor, subclase de PersonalOperaciones
public class Doctor extends PersonalOperaciones {
    private List<Doctor> equipo;

    public Doctor(String nombre, Date fechaVinculacion, double salario) {
        super(nombre, fechaVinculacion, salario);
        this.equipo = new ArrayList<>();
    }

    public List<Doctor> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<Doctor> equipo) {
        this.equipo = equipo;
    }
}
