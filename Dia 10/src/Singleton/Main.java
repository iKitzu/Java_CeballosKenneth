package Singleton;

public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstancia();
        singleton.mostrarMensaje();
    }
}
