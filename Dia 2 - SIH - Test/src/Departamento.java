import java.util.ArrayList;
import java.util.List;

// Clase para departamentos
public class Departamento {
    private String nombre;
    private List<Personal> personal;

    public Departamento(String nombre) {
        this.nombre = nombre;
        this.personal = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Personal> getPersonal() {
        return personal;
    }

    public void addPersonal(Personal p) {
        this.personal.add(p);
    }
}
