package quizjava;

public class Heroe extends Personaje {
    private static Heroe instancia;

    private Heroe(String nickname, String arma) {
        super(nickname, arma);
    }

    public static Heroe getInstancia(String nickname, String arma) {
        if (instancia == null) {
            instancia = new Heroe(nickname, arma);
        }
        return instancia;
    }
}
