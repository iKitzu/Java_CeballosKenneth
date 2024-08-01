package ligabalonencestarciudadderrota;

import java.util.Date;

public class PartidoLiga extends Partido {
    private int numeroJornada;

    public PartidoLiga(String equipoLocal, String equipoVisitante, Date fecha, int numeroJornada) {
        super(equipoLocal, equipoVisitante, fecha);
        this.numeroJornada = numeroJornada;
    }

    @Override
    public void finalizarPartido() {
        this.finalizado = true;
    }
}

