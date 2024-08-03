package LigaBaloncesto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class Partido {
    protected String equipoLocal;
    protected String equipoVisitante;
    protected int cestasLocal;
    protected int cestasVisitante;
    protected boolean finalizado;
    protected LocalDate fecha;

    public Partido(String equipoLocal, String equipoVisitante, LocalDate fecha) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.fecha = fecha;
        this.cestasLocal = 0;
        this.cestasVisitante = 0;
        this.finalizado = false;
    }

    public abstract String obtenerInformacion();

    public void registrarPuntosLocal(int puntos) {
        if (!finalizado) {
            cestasLocal += puntos;
        }
    }

    public void registrarPuntosVisitante(int puntos) {
        if (!finalizado) {
            cestasVisitante += puntos;
        }
    }

    public String obtenerResultado() {
        return "Resultado: " + equipoLocal + " " + cestasLocal + " - " + cestasVisitante + " " + equipoVisitante;
    }

    public String obtenerGanador() {
        if (cestasLocal > cestasVisitante) {
            return "Ganador: " + equipoLocal;
        } else if (cestasVisitante > cestasLocal) {
            return "Ganador: " + equipoVisitante;
        } else {
            return "Empate";
        }
    }

    public void finalizarPartido() {
        finalizado = true;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public static LocalDate leerFechaDesdeConsola() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        System.out.print("Ingrese la fecha (día/mes/año): ");
        String fechaInput = new Scanner(System.in).nextLine();
        try {
            return LocalDate.parse(fechaInput, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto. Usando fecha actual.");
            return LocalDate.now();
        }
    }
}
