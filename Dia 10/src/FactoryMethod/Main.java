package FactoryMethod;

public class Main {
    public static void main(String[] args) {
        Creador creador = new CreadorConcretoA();
        Producto producto = creador.crearProducto();
        producto.operar();
    }
}
