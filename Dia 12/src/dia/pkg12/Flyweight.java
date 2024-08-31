/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dia.pkg12;

/**
 *
 * @author Juan Felipe Rubio
 */

import java.util.HashMap;
import java.util.Map;

// Flyweight
class Caractere {
    private char letra;

    public Caractere(char letra) {
        this.letra = letra;
    }

    public void mostrar(String fuente, int tamano) {
        System.out.println("Carácter: " + letra + " Fuente: " + fuente + " Tamaño: " + tamano);
    }
}

// Flyweight Factory
class FabricaDeCaracteres {
    private Map<Character, Caractere> caracteres = new HashMap<>();

    public Caractere obtenerCaractere(char letra) {
        Caractere caractere = caracteres.get(letra);
        if (caractere == null) {
            caractere = new Caractere(letra);
            caracteres.put(letra, caractere);
        }
        return caractere;
    }
}
