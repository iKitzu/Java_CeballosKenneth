package Prototype;

class Documento implements Cloneable {
    private String contenido;

    public Documento(String contenido) {
        this.contenido = contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Documento [contenido=" + contenido + "]";
    }

    @Override
    protected Documento clone() throws CloneNotSupportedException {
        return (Documento) super.clone();
    }
}
