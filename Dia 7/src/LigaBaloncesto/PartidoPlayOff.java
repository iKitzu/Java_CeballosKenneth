package LigaBaloncesto;

import java.time.LocalDate;

public class PartidoPlayOff extends Partido {
    private String ronda;

    public PartidoPlayOff(String equipoLocal, String equipoVisitante, LocalDate fecha, String ronda) {
        super(equipoLocal, equipoVisitante, fecha);
        this.ronda = ronda;
    }

    @Override
    public String obtenerInformacion() {
        return "Partido de PlayOff: " + equipoLocal + " vs " + equipoVisitante + " en la ronda " + ronda + " el " + fecha;
    }

    @Override
    public void finalizarPartido() {
        if (cestasLocal != cestasVisitante) {
            finalizado = true;
        } else {
            System.out.println("El partido está empatado y no puede finalizar hasta que haya un ganador.");
        }
    }
}
