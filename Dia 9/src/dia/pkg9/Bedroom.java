/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dia.pkg9;

/**
 *
 * @author Juan Felipe Rubio
 */

// En habitación para dormir implementamos ese mismo cuarto
public class Bedroom implements Room{
    // Agregando una T.V. a la habitación
    Television television;
    
    /*
    // Implementando el constructor de la habitación
    public Bedroom(){
    Inicializando la tv en HD
    television = new TelevisionHD();
    
    Haciendo esto rompemos el principio de la inversión de dependencias, porque cada
    vez que nosotros inicializamos una habitación, siempre va a tener la habitación en HD
    y no queremos que siempre tenga una habitación en HD 
    }*/
    
    // Implementando el constructor de la habitación
    public Bedroom(Television tv){
        television = tv;
    // Lo que indica este principio es que mejor nosotros tenemos que tenerlo de algún otro modo recibirlo
    // En este caso desde el constructor vamos a recibir  la televisión que queremos asignar 
    // y se la vamos a estar asignando a la variable privada de esta clase, pero nosotros ya 
    //cuando creemos un objeto de tipo habitación le tenemos que pasar el tipo de televisión y entonces 
    //ya lo podemos sustituir sin ningun problema.
    }
    // Función para dormir
    public void sleep(){
    
    }
    
    // Función para preparar comida
    public void prepareFood(){
    
    }
    
    // Metodo de prender la luz
    @Override
    public void turnLight(){
    
    }
    // El primer principio indica que una clase se debe de encargar de una sola cosa, 
    //más especifica y no incluirles cosas que sean orientadas a otra clase
}
