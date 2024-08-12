package quizjava;

public class Arma {
    private String nombre;
    private Encantamiento encantamiento;

    public Arma(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEncantamiento(Encantamiento encantamiento) {
        this.encantamiento = encantamiento;
    }

    public void blandir() {
        if (encantamiento != null) {
            encantamiento.activar();
        }
    }

    public void atacar() {
        if (encantamiento != null) {
            encantamiento.aplicar();
        }
    }

    public void soltar() {
        if (encantamiento != null) {
            encantamiento.desactivar();
        }
    }
}
