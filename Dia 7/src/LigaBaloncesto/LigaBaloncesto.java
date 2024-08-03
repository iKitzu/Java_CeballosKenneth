package LigaBaloncesto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    registrarPartido();
                    break;
                case 2:
                    finalizarPartido();
                    break;
                case 3:
                    mostrarInformacionPartido();
                    break;
                case 4:
                    modificarPartido();
                    break;
                case 5:
                    mostrarTodosPartidos();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 6.");
            }
        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("\n======== Menú Principal ========");
        System.out.println("| 1. Registrar Partido          |");
        System.out.println("| 2. Finalizar Partido          |");
        System.out.println("| 3. Mostrar Información        |");
        System.out.println("| 4. Modificar Partido          |");
        System.out.println("| 5. Mostrar Todos los Partidos |");
        System.out.println("| 6. Salir                      |");
        System.out.println("=================================");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarPartido() {
        System.out.println("\n=== Registrar Partido ===");
        System.out.print("Tipo de partido (1 para Liga, 2 para PlayOff): ");
        int tipoPartido = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        System.out.print("Equipo Local: ");
        String equipoLocal = scanner.nextLine();
        System.out.print("Equipo Visitante: ");
        String equipoVisitante = scanner.nextLine();
        LocalDate fecha = leerFechaDesdeConsola();
        String queryInsertar = null;

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (tipoPartido == 1) {
                System.out.print("Jornada: ");
                int jornada = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea

                queryInsertar = "INSERT INTO partidos (equipo_local, equipo_visitante, fecha, jornada, finalizado) VALUES (?, ?, ?, ?, 'No')";
                try (PreparedStatement stmt = conn.prepareStatement(queryInsertar, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, equipoLocal);
                    stmt.setString(2, equipoVisitante);
                    stmt.setDate(3, Date.valueOf(fecha));
                    stmt.setInt(4, jornada);
                    stmt.executeUpdate();

                    ResultSet rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        System.out.println("Partido de Liga registrado exitosamente con ID: " + id);
                    }
                }
            } else if (tipoPartido == 2) {
                System.out.print("Ronda: ");
                String ronda = scanner.nextLine();

                queryInsertar = "INSERT INTO partidos (equipo_local, equipo_visitante, fecha, ronda, finalizado) VALUES (?, ?, ?, ?, 'No')";
                try (PreparedStatement stmt = conn.prepareStatement(queryInsertar, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, equipoLocal);
                    stmt.setString(2, equipoVisitante);
                    stmt.setDate(3, Date.valueOf(fecha));
                    stmt.setString(4, ronda);
                    stmt.executeUpdate();

                    ResultSet rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        System.out.println("Partido de PlayOff registrado exitosamente con ID: " + id);
                    }
                }
            } else {
                System.out.println("Tipo de partido no válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void finalizarPartido() {
        System.out.println("\n=== Finalizar Partido ===");
        System.out.print("Ingrese el ID del partido a finalizar: ");
        int partidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        try (Connection conn = DatabaseConnection.getConnection()) {
            String queryVerificar = "SELECT id, finalizado FROM partidos WHERE id = ?";
            try (PreparedStatement stmtVerificar = conn.prepareStatement(queryVerificar)) {
                stmtVerificar.setInt(1, partidoId);
                ResultSet rs = stmtVerificar.executeQuery();
                if (rs.next()) {
                    String finalizado = rs.getString("finalizado");
                    if ("Sí".equals(finalizado)) {
                        System.out.println("El partido ya está finalizado y no se puede modificar.");
                        return;
                    }

                    System.out.print("Puntos equipo local: ");
                    int puntosLocal = scanner.nextInt();
                    System.out.print("Puntos equipo visitante: ");
                    int puntosVisitante = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea

                    String queryActualizar = "UPDATE partidos SET cestas_local = ?, cestas_visitante = ?, finalizado = 'Sí' WHERE id = ?";
                    try (PreparedStatement stmtActualizar = conn.prepareStatement(queryActualizar)) {
                        stmtActualizar.setInt(1, puntosLocal);
                        stmtActualizar.setInt(2, puntosVisitante);
                        stmtActualizar.setInt(3, partidoId);
                        stmtActualizar.executeUpdate();
                        System.out.println("Partido finalizado y puntos registrados.");
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
        System.out.println("\n=== Mostrar Información del Partido ===");
        System.out.print("Ingrese el ID del partido: ");
        int partidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT equipo_local, equipo_visitante, fecha, cestas_local, cestas_visitante, finalizado FROM partidos WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, partidoId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String equipoLocal = rs.getString("equipo_local");
                    String equipoVisitante = rs.getString("equipo_visitante");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    int cestasLocal = rs.getInt("cestas_local");
                    int cestasVisitante = rs.getInt("cestas_visitante");
                    String finalizado = rs.getString("finalizado");

                    System.out.println("Equipo Local: " + equipoLocal);
                    System.out.println("Equipo Visitante: " + equipoVisitante);
                    System.out.println("Fecha: " + fecha);
                    System.out.println("Puntos Local: " + cestasLocal);
                    System.out.println("Puntos Visitante: " + cestasVisitante);
                    System.out.println("Estado: " + (finalizado.equals("Sí") ? "Finalizado" : "No Finalizado"));

                    if (finalizado.equals("Sí")) {
                        if (cestasLocal > cestasVisitante) {
                            System.out.println("Ganador: " + equipoLocal);
                        } else if (cestasVisitante > cestasLocal) {
                            System.out.println("Ganador: " + equipoVisitante);
                        } else {
                            System.out.println("El partido terminó en empate.");
                        }
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
        System.out.println("\n=== Modificar Partido ===");
        System.out.print("Ingrese el ID del partido a modificar: ");
        int partidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        try (Connection conn = DatabaseConnection.getConnection()) {
            String queryVerificar = "SELECT finalizado FROM partidos WHERE id = ?";
            try (PreparedStatement stmtVerificar = conn.prepareStatement(queryVerificar)) {
                stmtVerificar.setInt(1, partidoId);
                ResultSet rs = stmtVerificar.executeQuery();
                if (rs.next()) {
                    String finalizado = rs.getString("finalizado");
                    if ("Sí".equals(finalizado)) {
                        System.out.println("El partido ya está finalizado y no se puede modificar.");
                        return;
                    }

                    System.out.print("Nuevo equipo local (dejar vacío para no cambiar): ");
                    String nuevoEquipoLocal = scanner.nextLine();
                    System.out.print("Nuevo equipo visitante (dejar vacío para no cambiar): ");
                    String nuevoEquipoVisitante = scanner.nextLine();
                    System.out.print("Nueva fecha (YYYY-MM-DD, dejar vacío para no cambiar): ");
                    String nuevaFechaStr = scanner.nextLine();
                    LocalDate nuevaFecha = null;
                    if (!nuevaFechaStr.isEmpty()) {
                        try {
                            nuevaFecha = LocalDate.parse(nuevaFechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido.");
                            return;
                        }
                    }

                    String queryActualizar = "UPDATE partidos SET equipo_local = COALESCE(NULLIF(?, ''), equipo_local), equipo_visitante = COALESCE(NULLIF(?, ''), equipo_visitante), fecha = COALESCE(NULLIF(?, fecha), fecha) WHERE id = ?";
                    try (PreparedStatement stmtActualizar = conn.prepareStatement(queryActualizar)) {
                        stmtActualizar.setString(1, nuevoEquipoLocal);
                        stmtActualizar.setString(2, nuevoEquipoVisitante);
                        stmtActualizar.setObject(3, nuevaFecha != null ? Date.valueOf(nuevaFecha) : null);
                        stmtActualizar.setInt(4, partidoId);
                        stmtActualizar.executeUpdate();
                        System.out.println("Partido modificado exitosamente.");
                    }
                } else {
                    System.out.println("Partido no encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarTodosPartidos() {
        System.out.println("\n=== Mostrar Todos los Partidos ===");

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT id, equipo_local, equipo_visitante, fecha, finalizado FROM partidos";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String equipoLocal = rs.getString("equipo_local");
                    String equipoVisitante = rs.getString("equipo_visitante");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    String finalizado = rs.getString("finalizado");

                    System.out.println("ID: " + id);
                    System.out.println("Equipo Local: " + equipoLocal);
                    System.out.println("Equipo Visitante: " + equipoVisitante);
                    System.out.println("Fecha: " + fecha);
                    System.out.println("Finalizado: " + finalizado);
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static LocalDate leerFechaDesdeConsola() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print("Fecha (YYYY-MM-DD): ");
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Inténtelo de nuevo.");
            }
        }
    }
}
