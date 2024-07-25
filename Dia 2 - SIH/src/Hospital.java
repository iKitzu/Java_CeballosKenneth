import java.util.List;

public class Hospital {
    private String nombre;
    private String direccion;
    private List<Departamento> departamentos;

    public Hospital(String nombre, String direccion, List<Departamento> departamentos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.departamentos = departamentos;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
