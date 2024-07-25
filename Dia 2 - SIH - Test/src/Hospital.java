import java.util.ArrayList;
import java.util.List;

// Clase para hospitales
public class Hospital {
    private String nombre;
    private List<Departamento> departamentos;
    private List<Persona> personas;

    public Hospital(String nombre) {
        this.nombre = nombre;
        this.departamentos = new ArrayList<>();
        this.personas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void addDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void addPersona(Persona persona) {
        this.personas.add(persona);
    }
}
