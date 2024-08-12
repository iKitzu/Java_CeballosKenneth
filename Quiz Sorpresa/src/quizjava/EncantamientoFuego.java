package quizjava;

public class EncantamientoFuego extends Encantamiento {
    @Override
    public String getNombre() {
        return "Fuego";
    }

    @Override
    public void activar() {
        System.out.println("Genera un aura de luz.");
    }

    @Override
    public void aplicar() {
        System.out.println("Añade daño de fuego al atacar.");
    }

    @Override
    public void desactivar() {
        System.out.println("El aura de fuego se apaga.");
    }
}
