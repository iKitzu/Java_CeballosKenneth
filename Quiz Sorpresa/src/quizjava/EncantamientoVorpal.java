package quizjava;

public class EncantamientoVorpal extends Encantamiento {
    @Override
    public String getNombre() {
        return "Vorpal";
    }

    @Override
    public void activar() {
        System.out.println("El arma se ve roja.");
    }

    @Override
    public void aplicar() {
        System.out.println("Incrementa la posibilidad de un ataque crítico.");
    }

    @Override
    public void desactivar() {
        System.out.println("El arma deja de verse roja.");
    }
}
