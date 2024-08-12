package quizjava;

import java.util.ArrayList;
import java.util.List;

public class Albion {
    private Heroe heroe;
    private List<Villano> villanos = new ArrayList<>();

    // Método para registrar un héroe
    public void registrarHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    // Método para obtener el héroe registrado
    public Heroe getHeroe() {
        return this.heroe;
    }

    // Método para registrar un villano
    public void registrarVillano(Villano villano) {
        if (villanos.size() < 5) { // Asumiendo que hay un límite de 5 villanos del mismo tipo
            villanos.add(villano);
        } else {
            System.out.println("No se pueden registrar más villanos del mismo tipo.");
        }
    }
}
