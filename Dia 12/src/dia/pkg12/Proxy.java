    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dia.pkg12;

/**
 *
 * @author Juan Felipe Rubio
 */

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
