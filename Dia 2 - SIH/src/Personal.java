import java.util.Date;

public class Personal extends Persona {
    private Date fechaVinculacion;
    private double salario;

    public Personal(String nombre, String direccion, Date fechaNacimiento, Date fechaVinculacion, double salario) {
        super(nombre, direccion, fechaNacimiento);
        this.fechaVinculacion = fechaVinculacion;
        this.salario = salario;
    }

    // Getters y Setters
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
