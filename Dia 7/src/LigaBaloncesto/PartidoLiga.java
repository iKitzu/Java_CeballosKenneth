package LigaBaloncesto;

import java.time.LocalDate;

public class PartidoLiga extends Partido {
    private int jornada;

    public PartidoLiga(String equipoLocal, String equipoVisitante, LocalDate fecha, int jornada) {
        super(equipoLocal, equipoVisitante, fecha);
        this.jornada = jornada;
    }

    @Override
    public String obtenerInformacion() {
        return "Partido de Liga: " + equipoLocal + " vs " + equipoVisitante + " en la jornada " + jornada + " el " + fecha;
    }
}
