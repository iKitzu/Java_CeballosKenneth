import java.util.List;

public class Departamento {
    private String nombre;
    private List<Personal> personal;

    public Departamento(String nombre, List<Personal> personal) {
        this.nombre = nombre;
        this.personal = personal;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Personal> getPersonal() {
        return personal;
    }

    public void setPersonal(List<Personal> personal) {
        this.personal = personal;
    }
}
