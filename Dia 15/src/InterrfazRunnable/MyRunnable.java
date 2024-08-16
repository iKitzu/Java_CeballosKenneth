package Ejemplo_Implementacion.InterrfazRunnable;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Runnable - Iteración "+i);
            try{
                Thread.sleep(1000); //Pausa el hilo por un segundo.
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
