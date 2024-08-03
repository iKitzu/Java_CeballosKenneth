package LigaBaloncesto;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LigaBaloncesto {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar la entrada no válida
                continue;
            }
            scanner.nextLine(); // Consumir nueva línea
            switch (opcion) {
                case 1:
                    registrarPartidoLiga();
                    break;
                case 2:
                    registrarPartidoPlayOff();
                    break;
                case 3:
                    finalizarPartido();
                    break;
                case 4:
                    mostrarGanador();
                    break;
                case 5:
                    mostrarInformacionPartido();
                    break;
                case 6:
                    modificarPartido();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("======== Menú ========");
        System.out.println("1. Registrar Partido de Liga");
        System.out.println("2. Registrar Partido de PlayOff");
        System.out.println("3. Finalizar Partido");
        System.out.println("4. Mostrar Ganador");
        System.out.println("5. Mostrar Información del Partido");
        System.out.println("6. Modificar Partido");
        System.out.println("7. Salir");
        System.out.println("======================");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarPartidoLiga() {
        System.out.print("Equipo Local: ");
        String equipoLocal = scanner.nextLine();
        System.out.print("Equipo Visitante: ");
        String equipoVisitante = scanner.nextLine();
        LocalDate fecha = Partido.leerFechaDesdeConsola();
        int jornada;
        while (true) {
            try {
                System.out.print("Jornada (número): ");
                jornada = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar la entrada no válida
            }
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO partidos (equipo_local, equipo_visitante, fecha, cestas_local, cestas_visitante, finalizado) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, equipoLocal);
                stmt.setString(2, equipoVisitante);
                stmt.setDate(3, java.sql.Date.valueOf(fecha));
                stmt.setInt(4, 0); // Inicialmente sin puntos
                stmt.setInt(5, 0); // Inicialmente sin puntos
                stmt.setBoolean(6, false); // Inicialmente no finalizado
                stmt.executeUpdate();

                // Obtener el ID del partido insertado
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int partidoId = generatedKeys.getInt(1);

                    String queryLiga = "INSERT INTO partidos_liga (partido_id, jornada) VALUES (?, ?)";
                    try (PreparedStatement stmtLiga = conn.prepareStatement(queryLiga)) {
                        stmtLiga.setInt(1, partidoId);
                        stmtLiga.setInt(2, jornada);
                        stmtLiga.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Partido de Liga registrado exitosamente.");
    }

    private static void registrarPartidoPlayOff() {
        System.out.print("Equipo Local: ");
        String equipoLocal = scanner.nextLine();
        System.out.print("Equipo Visitante: ");
        String equipoVisitante = scanner.nextLine();
        LocalDate fecha = Partido.leerFechaDesdeConsola();
        System.out.print("Ronda: ");
        String ronda = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO partidos (equipo_local, equipo_visitante, fecha, cestas_local, cestas_visitante, finalizado) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, equipoLocal);
                stmt.setString(2, equipoVisitante);
                stmt.setDate(3, java.sql.Date.valueOf(fecha));
                stmt.setInt(4, 0); // Inicialmente sin puntos
                stmt.setInt(5, 0); // Inicialmente sin puntos
                stmt.setBoolean(6, false); // Inicialmente no finalizado
                stmt.executeUpdate();

                // Obtener el ID del partido insertado
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int partidoId = generatedKeys.getInt(1);

                    String queryPlayOff = "INSERT INTO partidos_playoff (partido_id, ronda) VALUES (?, ?)";
                    try (PreparedStatement stmtPlayOff = conn.prepareStatement(queryPlayOff)) {
                        stmtPlayOff.setInt(1, partidoId);
                        stmtPlayOff.setString(2, ronda);
                        stmtPlayOff.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Partido de PlayOff registrado exitosamente.");
    }

    private static void finalizarPartido() {
        System.out.print("Ingrese el ID del partido a finalizar: ");
        int partidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE partidos SET finalizado = TRUE WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, partidoId);
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Partido finalizado.");
                } else {
                    System.out.println("Partido no encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarGanador() {
        System.out.print("Ingrese el ID del partido: ");
        int partidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT equipo_local, equipo_visitante, cestas_local, cestas_visitante FROM partidos WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, partidoId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String equipoLocal = rs.getString("equipo_local");
                    String equipoVisitante = rs.getString("equipo_visitante");
                    int cestasLocal = rs.getInt("cestas_local");
                    int cestasVisitante = rs.getInt("cestas_visitante");

                    if (cestasLocal > cestasVisitante) {
                        System.out.println("Ganador: " + equipoLocal);
                    } else if (cestasVisitante > cestasLocal) {
                        System.out.println("Ganador: " + equipoVisitante);
                    } else {
                        System.out.println("Empate");
                    }
                } else {
                    System.out.println("Partido no encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarInformacionPartido() {
        System.out.print("Ingrese el ID del partido: ");
        int partidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT p.equipo_local, p.equipo_visitante, p.fecha, l.jornada, po.ronda " +
                           "FROM partidos p " +
                           "LEFT JOIN partidos_liga l ON p.id = l.partido_id " +
                           "LEFT JOIN partidos_playoff po ON p.id = po.partido_id " +
                           "WHERE p.id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, partidoId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String equipoLocal = rs.getString("equipo_local");
                    String equipoVisitante = rs.getString("equipo_visitante");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    Integer jornada = rs.getInt("jornada");
                    String ronda = rs.getString("ronda");

                    System.out.println("Información del Partido:");
                    System.out.println("Equipo Local: " + equipoLocal);
                    System.out.println("Equipo Visitante: " + equipoVisitante);
                    System.out.println("Fecha: " + fecha);
                    if (jornada != null) {
                        System.out.println("Jornada: " + jornada);
                    }
                    if (ronda != null) {
                        System.out.println("Ronda: " + ronda);
                    }
                } else {
                    System.out.println("Partido no encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void modificarPartido() {
        System.out.print("Ingrese el ID del partido a modificar: ");
        int partidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        System.out.print("Nuevo equipo local (deje en blanco para no cambiar): ");
        String nuevoEquipoLocal = scanner.nextLine();
        System.out.print("Nuevo equipo visitante (deje en blanco para no cambiar): ");
        String nuevoEquipoVisitante = scanner.nextLine();
        System.out.print("Nueva fecha (día/mes/año, deje en blanco para no cambiar): ");
        String nuevaFechaStr = scanner.nextLine();
        LocalDate nuevaFecha = null;
        if (!nuevaFechaStr.isBlank()) {
            try {
                nuevaFecha = LocalDate.parse(nuevaFechaStr, DateTimeFormatter.ofPattern("d/M/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. No se aplicará el cambio.");
            }
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE partidos SET ";
            boolean first = true;
            if (!nuevoEquipoLocal.isBlank()) {
                query += "equipo_local = ?";
                first = false;
            }
            if (!nuevoEquipoVisitante.isBlank()) {
                if (!first) query += ", ";
                query += "equipo_visitante = ?";
                first = false;
            }
            if (nuevaFecha != null) {
                if (!first) query += ", ";
                query += "fecha = ?";
            }
            query += " WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                int index = 1;
                if (!nuevoEquipoLocal.isBlank()) {
                    stmt.setString(index++, nuevoEquipoLocal);
                }
                if (!nuevoEquipoVisitante.isBlank()) {
                    stmt.setString(index++, nuevoEquipoVisitante);
                }
                if (nuevaFecha != null) {
                    stmt.setDate(index++, java.sql.Date.valueOf(nuevaFecha));
                }
                stmt.setInt(index, partidoId);
                stmt.executeUpdate();
                System.out.println("Partido modificado exitosamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
