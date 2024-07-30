package SIH;

public class Hospital {
    int id;
    String nombre;
    String direccion;

    public Hospital(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Hospital ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion;
    }
}
