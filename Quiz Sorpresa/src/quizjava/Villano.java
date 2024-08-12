package quizjava;

public class Villano extends Personaje {
    private String raza;
    private static final int MAX_VILLANOS = 5;
    private static int conteoVillanos = 0;

    private Villano(String nickname, String arma, String raza) {
        super(nickname, arma);
        this.raza = raza;
        conteoVillanos++;
    }

    public static Villano crearVillano(String raza, String nickname, String arma) {
        if (conteoVillanos < MAX_VILLANOS) {
            return new Villano(nickname, arma, raza);
        } else {
            System.out.println("No se pueden crear más villanos del tipo " + raza + " debido a limitaciones de memoria.");
            return null;
        }
    }

    public String getRaza() {
        return raza;
    }
}
