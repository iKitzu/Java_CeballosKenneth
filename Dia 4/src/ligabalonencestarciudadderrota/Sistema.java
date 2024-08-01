package ligabalonencestarciudadderrota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private List<Partido> partidos;

    public Sistema() {
        this.partidos = new ArrayList<>();
    }

    public void registrarPartido(Partido partido) {
        partidos.add(partido);
        try (Connection conn = Database.getConnection()) {
            partido.guardarPartido(conn);
        } catch (SQLException e) {
            System.out.println("Error al guardar el partido: " + e.getMessage());
        }
    }

    public void registrarPuntosLocal(int indice, int puntos) {
        Partido partido = partidos.get(indice);
        partido.registrarPuntosLocal(puntos);
        actualizarPuntosEnBD(partido);
    }

    public void registrarPuntosVisitante(int indice, int puntos) {
        Partido partido = partidos.get(indice);
        partido.registrarPuntosVisitante(puntos);
        actualizarPuntosEnBD(partido);
    }

    private void actualizarPuntosEnBD(Partido partido) {
        String sql = "UPDATE partidos SET cestas_local = ?, cestas_visitante = ? WHERE equipo_local = ? AND equipo_visitante = ? AND fecha = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, partido.cestasLocal);
            pstmt.setInt(2, partido.cestasVisitante);
            pstmt.setString(3, partido.equipoLocal);
            pstmt.setString(4, partido.equipoVisitante);
            pstmt.setDate(5, new java.sql.Date(partido.fecha.getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar los puntos: " + e.getMessage());
        }
    }

    public void finalizarPartido(Partido partido) {
        partido.finalizarPartido();
        try (Connection conn = Database.getConnection()) {
            String sql = "UPDATE partidos SET finalizado = ? WHERE equipo_local = ? AND equipo_visitante = ? AND fecha = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setBoolean(1, partido.finalizado);
                pstmt.setString(2, partido.equipoLocal);
                pstmt.setString(3, partido.equipoVisitante);
                pstmt.setDate(4, new java.sql.Date(partido.fecha.getTime()));
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al finalizar el partido: " + e.getMessage());
        }
    }

    public void mostrarGanador(Partido partido) {
        System.out.println("Ganador: " + partido.obtenerGanador());
    }

    public void mostrarInformacion(Partido partido) {
        System.out.println(partido.obtenerInformacion());
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n============================");
            System.out.println("        Menú Principal    " );
            System.out.println("============================");
            System.out.println("1. Registrar partido");
            System.out.println("2. Registrar puntos para equipo local");
            System.out.println("3. Registrar puntos para equipo visitante");
            System.out.println("4. Finalizar partido");
            System.out.println("5. Mostrar ganador");
            System.out.println("6. Mostrar información del partido");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // consumir el salto de línea

            if (opcion == 7) {
                break;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Tipo de partido (1. Liga, 2. PlayOff): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine(); // consumir el salto de línea

                    System.out.println("Equipo local: ");
                    String equipoLocal = scanner.nextLine();
                    System.out.println("Equipo visitante: ");
                    String equipoVisitante = scanner.nextLine();
                    System.out.println("Fecha (dd/MM/yyyy): ");
                    String fechaStr = scanner.nextLine();
                    Date fecha = new Date(fechaStr);

                    if (tipo == 1) {
                        System.out.println("Número de jornada: ");
                        int jornada = scanner.nextInt();
                        Partido partidoLiga = new PartidoLiga(equipoLocal, equipoVisitante, fecha, jornada);
                        registrarPartido(partidoLiga);
                    } else {
                        System.out.println("Ronda (octavos, cuartos, final): ");
                        String ronda = scanner.nextLine();
                        Partido partidoPlayOff = new PartidoPlayOff(equipoLocal, equipoVisitante, fecha, ronda);
                        registrarPartido(partidoPlayOff);
                    }
                    break;
                case 2:
                    System.out.println("Índice del partido para registrar puntos en el equipo local: ");
                    int indiceLocal = scanner.nextInt();
                    System.out.println("Puntos a registrar: ");
                    int puntosLocal = scanner.nextInt();
                    registrarPuntosLocal(indiceLocal, puntosLocal);
                    break;
                case 3:
                    System.out.println("Índice del partido para registrar puntos en el equipo visitante: ");
                    int indiceVisitante = scanner.nextInt();
                    System.out.println("Puntos a registrar: ");
                    int puntosVisitante = scanner.nextInt();
                    registrarPuntosVisitante(indiceVisitante, puntosVisitante);
                    break;
                case 4:
                    System.out.println("Índice del partido a finalizar: ");
                    int indiceFinalizar = scanner.nextInt();
                    scanner.nextLine(); // consumir el salto de línea
                    finalizarPartido(partidos.get(indiceFinalizar));
                    break;
                case 5:
                    System.out.println("Índice del partido para mostrar el ganador: ");
                    int indiceGanador = scanner.nextInt();
                    scanner.nextLine(); // consumir el salto de línea
                    mostrarGanador(partidos.get(indiceGanador));
                    break;
                case 6:
                    System.out.println("Índice del partido para mostrar información: ");
                    int indiceInformacion = scanner.nextInt();
                    scanner.nextLine(); // consumir el salto de línea
                    mostrarInformacion(partidos.get(indiceInformacion));
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
