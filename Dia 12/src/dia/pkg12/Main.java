/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dia.pkg12;

/**
 *
 * @author Juan Felipe Rubio
 */

/*
public class Main {
    public static void main(String[] args) {
        OldSystem oldSystem = new OldSystemImpl();
        NewSystem newSystem = new Adapter(oldSystem);
        newSystem.newMethod(); // Llama al método antiguo a través del adaptador
    }
}*/

/*
// Uso del patrón Bridge
public class Main {
    public static void main(String[] args) {
        Dispositivo tv = new TV();
        ControlRemoto control = new ControlRemotoBasico(tv);
        control.encender();  // Salida: TV encendida
        control.apagar();    // Salida: TV apagada
    }
}*/

/*
// Uso del patrón Composite
public class Main {
    public static void main(String[] args) {
        ElementoMenu item1 = new ElementoMenu("Elemento 1");
        ElementoMenu item2 = new ElementoMenu("Elemento 2");
        SubMenu submenu = new SubMenu("Submenú Principal");
        submenu.agregarElemento(item1);
        submenu.agregarElemento(item2);

        submenu.mostrar();
        // Salida:
        // Submenú: Submenú Principal
        // Elemento 1
        // Elemento 2
    }
}
*/

/*
// Uso del patrón Decorator
public class Main {
    public static void main(String[] args) {
        Cafe cafe = new CafeSimple();
        System.out.println(cafe.descripcion() + " $" + cafe.costo());

        Cafe cafeConLeche = new ConLeche(cafe);
        System.out.println(cafeConLeche.descripcion() + " $" + cafeConLeche.costo());
    }
}
*/

/*
// Uso del patrón Facade
public class Main {
    public static void main(String[] args) {
        GestionPedidos gestionPedidos = new GestionPedidos();
        gestionPedidos.procesarPedido("Libro Java");
        // Salida:
        // Verificando stock de Libro Java
        // Generando factura para Libro Java
        // Procesando envío de Libro Java
    }
}
*/

/*
// Uso del patrón Flyweight
public class Main {
    public static void main(String[] args) {
        FabricaDeCaracteres fabrica = new FabricaDeCaracteres();

        String texto = "Hola Flyweight";
        String fuente = "Arial";
        int tamano = 12;

        for (char c : texto.toCharArray()) {
            Caractere caractere = fabrica.obtenerCaractere(c);
            caractere.mostrar(fuente, tamano);
        }
    }
}
*/


// Uso del patrón Proxy
public class Main {
    public static void main(String[] args) {
        Imagen imagen = new ProxyImagen("foto.jpg");

        // La imagen se carga en este momento, cuando se necesita por primera vez
        imagen.mostrar(); // Salida: Cargando imagen desde foto.jpg
                          //         Mostrando foto.jpg

        // La imagen ya está cargada, no se vuelve a cargar
        imagen.mostrar(); // Salida: Mostrando foto.jpg
    }
}
