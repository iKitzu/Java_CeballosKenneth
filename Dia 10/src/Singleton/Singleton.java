package Singleton;

public class Singleton {
    private static Singleton instancia;

    private Singleton() {
        // Constructor privado para evitar instanciación.
    }

    public static Singleton getInstancia() {
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }

    public void mostrarMensaje() {
        System.out.println("Instancia única de Singleton");
    }
}
