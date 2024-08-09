package AbstractFactory;

public class Main {
    public static void main(String[] args) {
        MobiliarioFactory factory = new MobiliarioModernoFactory();
        Silla silla = factory.crearSilla();
        Mesa mesa = factory.crearMesa();

        silla.sentarse();
        mesa.usar();
    }
}
