import java.util.Date;

// Clase base para el personal del hospital
public abstract class Personal {
    private String nombre;
    private Date fechaVinculacion;
    private double salario;

    public Personal(String nombre, Date fechaVinculacion, double salario) {
        this.nombre = nombre;
        this.fechaVinculacion = fechaVinculacion;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(Date fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
