package ligabalonencestarciudadderrota;

import java.util.Date;

public class PartidoPlayOff extends Partido {
    private String ronda;

    public PartidoPlayOff(String equipoLocal, String equipoVisitante, Date fecha, String ronda) {
        super(equipoLocal, equipoVisitante, fecha);
        this.ronda = ronda;
    }

    @Override
    public void finalizarPartido() {
        if (cestasLocal != cestasVisitante) {
            this.finalizado = true;
        } else {
            System.out.println("El partido no puede finalizar en empate en PlayOffs.");
        }
    }
}
