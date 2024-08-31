/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dia.pkg12;

/**
 *
 * @author Juan Felipe Rubio
 */

import java.util.ArrayList;
import java.util.List;

// Componente
interface Menu {
    void mostrar();
}

// Hoja (Elemento simple)
class ElementoMenu implements Menu {
    private String nombre;

    public ElementoMenu(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void mostrar() {
        System.out.println(nombre);
    }
}

// Compuesto (Elemento que contiene otros elementos)
class SubMenu implements Menu {
    private String nombre;
    private List<Menu> elementos = new ArrayList<>();

    public SubMenu(String nombre) {
        this.nombre = nombre;
    }

    public void agregarElemento(Menu menu) {
        elementos.add(menu);
    }

    @Override
    public void mostrar() {
        System.out.println("Submenú: " + nombre);
        for (Menu menu : elementos) {
            menu.mostrar();
        }
    }
}