/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dia.pkg9;

/**
 *
 * @author Juan Felipe Rubio
 */

// Interfaz de habitación
public interface Room {
    // Metodo para prender la luz
    public void turnLight();
    
    /*
    Metodo para cocinar comida
    public void cookFood();
    
    Con este metodo rompemos el principio de segregación de interfaces
    porque solo tenemos que hacer las interfaces que hagan lo justo y necesario y 
    que sean de la manera más abstracta posible
    */
}
