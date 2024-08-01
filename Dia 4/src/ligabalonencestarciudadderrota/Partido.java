package ligabalonencestarciudadderrota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public abstract class Partido {
    protected String equipoLocal;
    protected String equipoVisitante;
    protected int cestasLocal;
    protected int cestasVisitante;
    protected boolean finalizado;
    protected Date fecha;

    public Partido(String equipoLocal, String equipoVisitante, Date fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.cestasLocal = 0;
        this.cestasVisitante = 0;
        this.finalizado = false;
        this.fecha = fecha;
    }

    public String obtenerInformacion() {
        return "Partido entre " + equipoLocal + " y " + equipoVisitante + " el " + fecha.toString();
    }

    public String obtenerResultado() {
        return equipoLocal + " " + cestasLocal + " - " + cestasVisitante + " " + equipoVisitante;
    }

    public void registrarPuntosLocal(int puntos) {
        cestasLocal += puntos;
    }

    public void registrarPuntosVisitante(int puntos) {
        cestasVisitante += puntos;
    }

    public String obtenerGanador() {
        if (cestasLocal > cestasVisitante) {
            return equipoLocal;
        } else if (cestasVisitante > cestasLocal) {
            return equipoVisitante;
        } else {
            return "Empate";
        }
    }

    public abstract void finalizarPartido();

    public void guardarPartido(Connection conn) throws SQLException {
        String sql = "INSERT INTO partidos (equipo_local, equipo_visitante, cestas_local, cestas_visitante, finalizado, fecha) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipoLocal);
            pstmt.setString(2, equipoVisitante);
            pstmt.setInt(3, cestasLocal);
            pstmt.setInt(4, cestasVisitante);
            pstmt.setBoolean(5, finalizado);
            pstmt.setDate(6, new java.sql.Date(fecha.getTime()));
            pstmt.executeUpdate();
        }
    }
}
