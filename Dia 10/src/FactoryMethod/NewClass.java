package FactoryMethod;

abstract class Producto {
    abstract void operar();
}

class ProductoConcretoA extends Producto {
    void operar() {
        System.out.println("Producto Concreto A");
    }
}

class ProductoConcretoB extends Producto {
    void operar() {
        System.out.println("Producto Concreto B");
    }
}

abstract class Creador {
    abstract Producto crearProducto();
}

class CreadorConcretoA extends Creador {
    Producto crearProducto() {
        return new ProductoConcretoA();
    }
}

class CreadorConcretoB extends Creador {
    Producto crearProducto() {
        return new ProductoConcretoB();
    }
}
