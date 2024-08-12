package quizjava;

public class Herrero {
    public Arma fabricarArma(String tipo) {
        switch (tipo) {
            case "Élfico":
                return new Arma("Espada Élfica");
            case "Enano":
                return new Arma("Martillo Enano");
            default:
                System.out.println("Tipo de trabajo no válido.");
                return null;
        }
    }
}
