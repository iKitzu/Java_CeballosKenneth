/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dia.pkg12;

/**
 *
 * @author Juan Felipe Rubio
 */

// Componente base
interface Cafe {
    String descripcion();
    double costo();
}

// Componente concreto
class CafeSimple implements Cafe {
    @Override
    public String descripcion() {
        return "Café";
    }

    @Override
    public double costo() {
        return 10.0;
    }
}

// Decorador abstracto
abstract class CafeDecorador implements Cafe {
    protected Cafe cafeDecorado;

    public CafeDecorador(Cafe cafeDecorado) {
        this.cafeDecorado = cafeDecorado;
    }

    @Override
    public String descripcion() {
        return cafeDecorado.descripcion();
    }

    @Override
    public double costo() {
        return cafeDecorado.costo();
    }
}

// Decorador concreto
class ConLeche extends CafeDecorador {
    public ConLeche(Cafe cafeDecorado) {
        super(cafeDecorado);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + ", con leche";
    }

    @Override
    public double costo() {
        return super.costo() + 3.0;
    }
}
