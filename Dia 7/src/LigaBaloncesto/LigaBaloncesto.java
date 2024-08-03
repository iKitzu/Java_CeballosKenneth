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
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\n======== Menú Principal ========");
        System.out.println("| 1. Registrar Partido          |");
        System.out.println("| 2. Finalizar Partido          |");
        System.out.println("| 3. Mostrar Información        |");
        System.out.println("| 4. Modificar Partido          |");
        System.out.println("| 5. Salir                      |");
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

        if (tipoPartido == 1) {
            System.out.print("Jornada: ");
            int jornada = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            queryInsertar = "INSERT INTO partidos (equipo_local, equipo_visitante, fecha, jornada) VALUES (?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(queryInsertar)) {
                stmt.setString(1, equipoLocal);
                stmt.setString(2, equipoVisitante);
                stmt.setDate(3, Date.valueOf(fecha));
                stmt.setInt(4, jornada);
                stmt.executeUpdate();
                System.out.println("Partido de Liga registrado exitosamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (tipoPartido == 2) {
            System.out.print("Ronda: ");
            String ronda = scanner.nextLine();

            queryInsertar = "INSERT INTO partidos (equipo_local, equipo_visitante, fecha, ronda) VALUES (?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(queryInsertar)) {
                stmt.setString(1, equipoLocal);
                stmt.setString(2, equipoVisitante);
                stmt.setDate(3, Date.valueOf(fecha));
                stmt.setString(4, ronda);
                stmt.executeUpdate();
                System.out.println("Partido de PlayOff registrado exitosamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Tipo de partido no válido.");
        }
    }

    private static void finalizarPartido() {
    System.out.println("\n=== Finalizar Partido ===");
    System.out.print("Ingrese el ID del partido a finalizar: ");
    int partidoId = scanner.nextInt();
    scanner.nextLine(); // Consumir nueva línea

    try (Connection conn = DatabaseConnection.getConnection()) {
        // Verificar si el partido existe y si ya está finalizado
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

                // Registrar puntos y marcar como finalizado
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

                if ("Sí".equals(finalizado)) {
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
                boolean finalizado = rs.getString("finalizado").equalsIgnoreCase("Sí");
                if (finalizado) {
                    System.out.println("El partido ya está finalizado y no se puede modificar.");
                    return;
                }

                System.out.print("Nuevo equipo local (dejar vacío para no cambiar): ");
                String nuevoEquipoLocal = scanner.nextLine();
                System.out.print("Nuevo equipo visitante (dejar vacío para no cambiar): ");
                String nuevoEquipoVisitante = scanner.nextLine();
                System.out.print("Nueva fecha (dejar vacío para no cambiar, formato: día/mes/año): ");
                String nuevaFechaInput = scanner.nextLine();
                LocalDate nuevaFecha = nuevaFechaInput.isEmpty() ? null : LocalDate.parse(nuevaFechaInput, DateTimeFormatter.ofPattern("d/M/yyyy"));

                String queryActualizar = "UPDATE partidos SET equipo_local = COALESCE(?, equipo_local), equipo_visitante = COALESCE(?, equipo_visitante), fecha = COALESCE(?, fecha) WHERE id = ?";
                try (PreparedStatement stmtActualizar = conn.prepareStatement(queryActualizar)) {
                    stmtActualizar.setString(1, nuevoEquipoLocal.isEmpty() ? null : nuevoEquipoLocal);
                    stmtActualizar.setString(2, nuevoEquipoVisitante.isEmpty() ? null : nuevoEquipoVisitante);
                    stmtActualizar.setDate(3, nuevaFecha != null ? Date.valueOf(nuevaFecha) : null);
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


    private static LocalDate leerFechaDesdeConsola() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        System.out.print("Ingrese la fecha (día/mes/año): ");
        String fechaInput = scanner.nextLine();
        try {
            return LocalDate.parse(fechaInput, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto. Usando fecha actual.");
            return LocalDate.now();
        }
    }
}
