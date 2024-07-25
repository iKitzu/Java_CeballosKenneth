import java.util.Date;

// Clase base para todas las personas
public class Persona {
    private String nombreCompleto;
    private String direccion;

    public Persona(String nombreCompleto, String direccion) {
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
