/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dia.pkg12;

/**
 *
 * @author Juan Felipe Rubio
 */

// Implementación
interface Dispositivo {
    void encender();
    void apagar();
    void ajustarVolumen(int porcentaje);
}

// Implementación concreta
class TV implements Dispositivo {
    private boolean encendido;
    private int volumen;

    @Override
    public void encender() {
        encendido = true;
        System.out.println("TV encendida");
    }

    @Override
    public void apagar() {
        encendido = false;
        System.out.println("TV apagada");
    }

    @Override
    public void ajustarVolumen(int porcentaje) {
        volumen = porcentaje;
        System.out.println("Volumen de TV ajustado a " + volumen + "%");
    }
}

// Abstracción
abstract class ControlRemoto {
    protected Dispositivo dispositivo;

    public ControlRemoto(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    abstract void encender();
    abstract void apagar();
}

// Abstracción refinada
class ControlRemotoBasico extends ControlRemoto {
    public ControlRemotoBasico(Dispositivo dispositivo) {
        super(dispositivo);
    }

    @Override
    void encender() {
        dispositivo.encender();
    }

    @Override
    void apagar() {
        dispositivo.apagar();
    }
}

