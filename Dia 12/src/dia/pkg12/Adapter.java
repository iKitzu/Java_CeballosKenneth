
package dia.pkg12;


// Ejemplo: Adaptador para conectar un sistema antiguo con una nueva API.


// Interfaz antigua
interface OldSystem {
    void oldMethod();
}

// Implementación del sistema antiguo
class OldSystemImpl implements OldSystem {
    public void oldMethod() {
        System.out.println("Método del sistema antiguo.");
    }
}

// Nueva interfaz que el cliente espera
interface NewSystem {
    void newMethod();
}

// Adaptador que permite usar la antigua implementación con la nueva interfaz
class Adapter implements NewSystem {
    private OldSystem oldSystem;

    public Adapter(OldSystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    public void newMethod() {
        oldSystem.oldMethod(); // Adapta el método antiguo al nuevo
    }
}

// Uso del adaptador

