package SIH;

public class Pabellon {
    int id;
    String nombre;
    int capacidad;

    public Pabellon(int id, String nombre, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Pabellón ID: " + id + ", Nombre: " + nombre + ", Capacidad: " + capacidad;
    }
}
