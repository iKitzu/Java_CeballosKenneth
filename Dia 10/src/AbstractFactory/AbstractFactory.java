package AbstractFactory;

interface Silla {
    void sentarse();
}

interface Mesa {
    void usar();
}

class SillaModerna implements Silla {
    public void sentarse() {
        System.out.println("Sentado en una silla moderna.");
    }
}

class MesaModerna implements Mesa {
    public void usar() {
        System.out.println("Usando una mesa moderna.");
    }
}

class SillaVictoriana implements Silla {
    public void sentarse() {
        System.out.println("Sentado en una silla victoriana.");
    }
}

class MesaVictoriana implements Mesa {
    public void usar() {
        System.out.println("Usando una mesa victoriana.");
    }
}

interface MobiliarioFactory {
    Silla crearSilla();
    Mesa crearMesa();
}

class MobiliarioModernoFactory implements MobiliarioFactory {
    public Silla crearSilla() {
        return new SillaModerna();
    }

    public Mesa crearMesa() {
        return new MesaModerna();
    }
}

class MobiliarioVictorianoFactory implements MobiliarioFactory {
    public Silla crearSilla() {
        return new SillaVictoriana();
    }

    public Mesa crearMesa() {
        return new MesaVictoriana();
    }
}
