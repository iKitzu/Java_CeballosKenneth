
package dia.pkg9;

class Solid {
    public static void sleep(Bed bed){
        bed.sleep();
    }
    
    public static void main(String[] args){
        Bed bed = new SingleBed();
        Bed KingBed = new KingBed();
        
        sleep(bed);
        sleep(KingBed);
    }
}

/*
Este principio nos indica que no importa cual cama estemos utilizando cada uno puede 
sustituirse sin ningún problema en el método que estemos requiriendo para este ejemplo, 
se crea una cama individual y una KingBed, por esto se crea un metodo que nos va ayudar para dormir 
que va a recibir una cama y esta cama va a estar llamando al método de dormir, 
Entonces nosotros vamos a estar invocando primero con la cama
individual y luego con la cama kingbed, asi que no importa la sustitución de esta interfaz
*/
