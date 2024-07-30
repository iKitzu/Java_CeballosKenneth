package SIH;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Paciente {

    private static final Scanner scanner = new Scanner(System.in);

    public static void crearPaciente() {
        System.out.print("Ingrese el título: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese los nombres: ");
        String nombres = scanner.nextLine();

        System.out.print("Ingrese los apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
        String fechaNacimientoStr = scanner.nextLine();

        // Validar la fecha
        if (!DateValidator.isValidDate(fechaNacimientoStr)) {
            System.out.println("Fecha de nacimiento inválida. Asegúrese de usar el formato yyyy-MM-dd.");
            return;
        }

        System.out.print("Ingrese el pabellón: ");
        String pabellon = scanner.nextLine();

        // Verificar si el pabellón existe
        if (!pabellonExiste(pabellon)) {
            System.out.println("El pabellón no existe.");
            return;
        }

        String sql = "INSERT INTO Paciente (titulo, nombres, apellidos, direccion, fecha_nacimiento, pabellon) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, nombres);
            pstmt.setString(3, apellidos);
            pstmt.setString(4, direccion);
            pstmt.setString(5, fechaNacimientoStr);
            pstmt.setString(6, pabellon);
            pstmt.executeUpdate();
            System.out.println("Paciente creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarPaciente() {
        System.out.print("Ingrese el ID del paciente a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nuevo título: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese los nuevos nombres: ");
        String nombres = scanner.nextLine();

        System.out.print("Ingrese los nuevos apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("Ingrese la nueva dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese la nueva fecha de nacimiento (yyyy-MM-dd): ");
        String fechaNacimientoStr = scanner.nextLine();

        // Validar la fecha
        if (!DateValidator.isValidDate(fechaNacimientoStr)) {
            System.out.println("Fecha de nacimiento inválida. Asegúrese de usar el formato yyyy-MM-dd.");
            return;
        }

        System.out.print("Ingrese el nuevo pabellón: ");
        String pabellon = scanner.nextLine();

        // Verificar si el pabellón existe
        if (!pabellonExiste(pabellon)) {
            System.out.println("El pabellón no existe.");
            return;
        }

        String sql = "UPDATE Paciente SET titulo = ?, nombres = ?, apellidos = ?, direccion = ?, fecha_nacimiento = ?, pabellon = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, nombres);
            pstmt.setString(3, apellidos);
            pstmt.setString(4, direccion);
            pstmt.setString(5, fechaNacimientoStr);
            pstmt.setString(6, pabellon);
            pstmt.setInt(7, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Paciente actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el paciente con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarPaciente() {
        System.out.print("Ingrese el ID del paciente a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        String sql = "DELETE FROM Paciente WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Paciente eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el paciente con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void verPacientes() {
        String sql = "SELECT * FROM Paciente";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String fechaNacimiento = rs.getString("fecha_nacimiento");
                String pabellon = rs.getString("pabellon");
                System.out.println("Paciente ID: " + id + ", Título: " + titulo + ", Nombres: " + nombres +
                                   ", Apellidos: " + apellidos + ", Dirección: " + direccion + 
                                   ", Fecha de Nacimiento: " + fechaNacimiento + ", Pabellón: " + pabellon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Verificación de existencia de pabellón
    private static boolean pabellonExiste(String pabellonNombre) {
        String sql = "SELECT COUNT(*) FROM Pabellon WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pabellonNombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
