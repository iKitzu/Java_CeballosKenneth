
package dia.pkg12;

// Interfaz
interface Imagen {
    void mostrar();
}

// Real Subject (objeto real)
class ImagenReal implements Imagen {
    private String archivo;

    public ImagenReal(String archivo) {
        this.archivo = archivo;
        cargarImagen();
    }

    private void cargarImagen() {
        System.out.println("Cargando imagen desde " + archivo);
    }

    @Override
    public void mostrar() {
        System.out.println("Mostrando " + archivo);
    }
}

// Proxy
class ProxyImagen implements Imagen {
    private String archivo;
    private ImagenReal imagenReal;

    public ProxyImagen(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void mostrar() {
        if (imagenReal == null) {
            imagenReal = new ImagenReal(archivo);
        }
        imagenReal.mostrar();
    }
}
