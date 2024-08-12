package quizjava;

public class Personaje {
    private String nickname;
    private String arma;

    public Personaje(String nickname, String arma) {
        this.nickname = nickname;
        this.arma = arma;
    }

    public String getNickname() {
        return nickname;
    }

    public String getArma() {
        return arma;
    }
}
