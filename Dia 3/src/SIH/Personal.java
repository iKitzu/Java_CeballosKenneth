package SIH;

public class Personal {
    int id;
    String nombres;
    String titulo;

    public Personal(int id, String nombres, String titulo) {
        this.id = id;
        this.nombres = nombres;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Personal ID: " + id + ", Nombres: " + nombres + ", Título: " + titulo;
    }
}
