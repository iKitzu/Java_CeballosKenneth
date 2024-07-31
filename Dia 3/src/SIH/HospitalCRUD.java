package SIH;

import java.sql.*;
import java.util.Scanner;

public class HospitalCRUD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final String RESET = "\033[0m";
        final String RED = "\033[31m";
        
        while (true) {
            System.out.println("\n============================");
            System.out.println("        Menú Principal    " );
            System.out.println("============================");
            System.out.println("1. Opciones Hospital");
            System.out.println("2. Opciones Pacientes");
            System.out.println("3. Opciones Personal");
            System.out.println("4. Opciones Pabellon");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> menuHospital(scanner);
                case 2 -> menuPaciente(scanner);
                case 3 -> menuPersonal(scanner);
                case 4 -> menuPabellon(scanner);
                case 5 -> {
                    System.out.println("Hasta la proxima... (Musica Dupsted)");
                    scanner.close();
                    return;
                }
                default -> System.out.println("\nOpción no válida.\n");
            }
        }
    }

    // Menú de Hospital
    private static void menuHospital(Scanner scanner) {
        while (true) {
            System.out.println("\n============================");
            System.out.println("      Opciones Hospital");
            System.out.println("============================");
            System.out.println("1. Crear Hospital");
            System.out.println("2. Actualizar Hospital");
            System.out.println("3. Eliminar Hospital");
            System.out.println("4. Ver Hospitales");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearHospital(scanner);
                case 2 -> actualizarHospital(scanner);
                case 3 -> eliminarHospital(scanner);
                case 4 -> verHospitales();
                case 5 -> {
                    return;
                }
                default -> System.out.println("\nOpción no válida.\n");
            }
        }
    }

    // Menú de Paciente
    private static void menuPaciente(Scanner scanner) {
        while (true) {
            System.out.println("\n============================");
            System.out.println("      Opciones Pacientes");
            System.out.println("============================");
            System.out.println("1. Crear Paciente");
            System.out.println("2. Actualizar Paciente");
            System.out.println("3. Eliminar Paciente");
            System.out.println("4. Ver Pacientes");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearPaciente(scanner);
                case 2 -> actualizarPaciente(scanner);
                case 3 -> eliminarPaciente(scanner);
                case 4 -> verPacientes();
                case 5 -> {
                    return;
                }
                default -> System.out.println("\nOpción no válida.\n");
            }
        }
    }

    // Menú de Personal
    private static void menuPersonal(Scanner scanner) {
        while (true) {
            System.out.println("\n============================");
            System.out.println("      Opciones Personal");
            System.out.println("============================");
            System.out.println("1. Crear Personal");
            System.out.println("2. Actualizar Personal");
            System.out.println("3. Eliminar Personal");
            System.out.println("4. Ver Personal");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearPersonal(scanner);
                case 2 -> actualizarPersonal(scanner);
                case 3 -> eliminarPersonal(scanner);
                case 4 -> verPersonal();
                case 5 -> {
                    return;
                }
                default -> System.out.println("\nOpción no válida.\n");
            }
        }
    }

    // Menú de Pabellon
    private static void menuPabellon(Scanner scanner) {
        while (true) {
            System.out.println("\n============================");
            System.out.println("      Opciones Pabellón");
            System.out.println("============================");
            System.out.println("1. Crear Pabellon");
            System.out.println("2. Actualizar Pabellon");
            System.out.println("3. Eliminar Pabellon");
            System.out.println("4. Ver Pabellones");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearPabellon(scanner);
                case 2 -> actualizarPabellon(scanner);
                case 3 -> eliminarPabellon(scanner);
                case 4 -> verPabellones();
                case 5 -> {
                    return;
                }
                default -> System.out.println("\nOpción no válida.\n");
            }
        }
    }

    // Métodos CRUD para Hospital
    private static void crearHospital(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Crear Hospital");
        System.out.println("==============================");
        System.out.print("Ingrese el nombre del hospital: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la dirección del hospital: ");
        String direccion = scanner.nextLine();

        String sql = "INSERT INTO Hospital (nombre, direccion) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);
            pstmt.executeUpdate();
            System.out.println("\n¡Hospital creado exitosamente!");
        } catch (SQLException e) {
        }
    }

    private static void actualizarHospital(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   ACTUALIZAR HOSPITAL");
        System.out.println("==============================");
        System.out.print("Ingrese el ID del hospital a actualizar: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nuevo nombre del hospital:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la nueva dirección del hospital:");
        String direccion = scanner.nextLine();

        String sql = "UPDATE Hospital SET nombre = ?, direccion = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);
            pstmt.setInt(3, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n¡Hospital actualizado exitosamente!");
            } else {
                System.out.println("\n¯\\_(ツ)_/¯");
                System.out.println("No se encontró el hospital con el ID proporcionado.");
            }
        } catch (SQLException e) {
        }
    }

    private static void eliminarHospital(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Eliminar Hospital");
        System.out.println("==============================");
        System.out.print("Ingrese el ID del hospital a eliminar: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Hospital WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n¡Hospital eliminado exitosamente! (Fue bombardeado por دون دجاج)");
            } else {
                System.out.println("\n¯\\_(ツ)_/¯");
                System.out.println("No se encontró el hospital con el ID proporcionado.");
            }
        } catch (SQLException e) {
        }
    }

    private static void verHospitales() {
        String sql = "SELECT * FROM Hospital";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n==============================");
            System.out.println("       Lista de Hospitales");
            System.out.println("==============================");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion);
            }
        } catch (SQLException e) {
        }
    }

    // Métodos CRUD para Paciente
    private static void crearPaciente(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Crear Paciente");
        System.out.println("==============================");
        System.out.print("Ingrese el título del paciente: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el nombre del paciente: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese el apellido del paciente: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese la dirección del paciente: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese la fecha de nacimiento del paciente (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Ingrese el nombre del pabellón al que pertenece el paciente:");
        String pabellonNombre = scanner.nextLine();

        if (!pabellonExiste(pabellonNombre)) {
            System.out.println("\n¡El Pabellón no existe! D:");
            return;
        }

        String sql = "INSERT INTO Paciente (titulo, nombres, apellidos, direccion, fecha_nacimiento, pabellon) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, nombres);
            pstmt.setString(3, apellidos);
            pstmt.setString(4, direccion);
            pstmt.setString(5, fechaNacimiento);
            pstmt.setString(6, pabellonNombre);
            pstmt.executeUpdate();
            System.out.println("\n¡Paciente creado exitosamente!");
        } catch (SQLException e) {
        }
    }

    private static void actualizarPaciente(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Actualziar Paciente");
        System.out.println("==============================");
        System.out.print("Ingrese el ID del paciente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nuevo título del paciente:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese los nuevos nombres del paciente:");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese los nuevos apellidos del paciente:");
        String apellidos = scanner.nextLine();
        System.out.println("Ingrese la nueva dirección del paciente:");
        String direccion = scanner.nextLine();
        System.out.println("Ingrese la nueva fecha de nacimiento del paciente (YYYY-MM-DD):");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Ingrese el nuevo nombre del pabellón al que pertenece el paciente:");
        String pabellonNombre = scanner.nextLine();

        if (!pabellonExiste(pabellonNombre)) {
            System.out.println("\n¡El Pabellón no existe! D:");
            return;
        }

        String sql = "UPDATE Paciente SET titulo = ?, nombres = ?, apellidos = ?, direccion = ?, fecha_nacimiento = ?, pabellon = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, nombres);
            pstmt.setString(3, apellidos);
            pstmt.setString(4, direccion);
            pstmt.setString(5, fechaNacimiento);
            pstmt.setString(6, pabellonNombre);
            pstmt.setInt(7, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n¡Paciente actualizado exitosamente!");
            } else {
                System.out.println("\n¯\\_(ツ)_/¯");
                System.out.println("No se encontró el paciente con el ID proporcionado.");
            }
        } catch (SQLException e) {
        }
    }

    private static void eliminarPaciente(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Eliminar Paciente");
        System.out.println("==============================");
        System.out.print("Ingrese el ID del paciente a eliminar: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Paciente WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n¡Paciente eliminado exitosamente! (Que en paz descanse.....)");
            } else {
                System.out.println("\n¯\\_(ツ)_/¯");
                System.out.println("No se encontró el paciente con el ID proporcionado.");
            }
        } catch (SQLException e) {
        }
    }

    private static void verPacientes() {
        String sql = "SELECT * FROM Paciente";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n==============================");
            System.out.println("       Lista de Pacientes");
            System.out.println("==============================");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String fechaNacimiento = rs.getString("fecha_nacimiento");
                String pabellon = rs.getString("pabellon");
                System.out.println("Paciente ID: " + id + ", Título: " + titulo + ", Nombres: " + nombres + ", Apellidos: " + apellidos + ", Dirección: " + direccion + ", Fecha de Nacimiento: " + fechaNacimiento + ", Pabellón: " + pabellon);
            }
        } catch (SQLException e) {
        }
    }

    // Métodos CRUD para Personal
    private static void crearPersonal(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   CREAR PERSONAL");
        System.out.println("==============================");
        System.out.print("Ingrese el nombre del personal: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese el título del personal: ");
        String titulo = scanner.nextLine();

        String sql = "INSERT INTO Personal (nombres, titulo) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombres);
            pstmt.setString(2, titulo);
            pstmt.executeUpdate();
            System.out.println("\n¡Personal creado exitosamente!, ¡A explotarlo laboralmente >:D!");
        } catch (SQLException e) {
        }
    }

    private static void actualizarPersonal(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Actualizar Personal");
        System.out.println("==============================");
        System.out.print("Ingrese el ID del personal a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese los nuevos nombres del personal:");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese el nuevo título del personal:");
        String titulo = scanner.nextLine();

        String sql = "UPDATE Personal SET nombres = ?, titulo = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombres);
            pstmt.setString(2, titulo);
            pstmt.setInt(3, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n¡Personal actualizado exitosamente!");
            } else {
                System.out.println("\n¯\\_(ツ)_/¯");
                System.out.println("No se encontró el personal con el ID proporcionado.");
            }
        } catch (SQLException e) {
        }
    }

    private static void eliminarPersonal(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Eliminar Personal");
        System.out.println("==============================");
        System.out.print("Ingrese el ID del personal a eliminar: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Personal WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n¡Personal despedido exitosamente (no volvera a llevar pan a la casa :c)!");
            } else {
                System.out.println("\n¯\\_(ツ)_/¯");
                System.out.println("No se encontró el personal con el ID proporcionado.");
            }
        } catch (SQLException e) {
        }
    }

    private static void verPersonal() {
        String sql = "SELECT * FROM Personal";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n==============================");
            System.out.println("       Lista de Personal");
            System.out.println("==============================");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombres = rs.getString("nombres");
                String titulo = rs.getString("titulo");
                System.out.println("Personal ID: " + id + ", Nombres: " + nombres + ", Título: " + titulo);
            }
        } catch (SQLException e) {
        }
    }

    // Métodos CRUD para Pabellon
    private static void crearPabellon(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Crear Personal");
        System.out.println("==============================");
        System.out.println("Ingrese el nombre del pabellón:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la capacidad del pabellón:");
        int capacidad = scanner.nextInt();

        String sql = "INSERT INTO Pabellon (nombre, capacidad) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, capacidad);
            pstmt.executeUpdate();
            System.out.println("\n¡Pabellón creado exitosamente!");
        } catch (SQLException e) {
        }
    }

    private static void actualizarPabellon(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Actualizar Pabellon");
        System.out.println("==============================");
        System.out.print("Ingrese el ID del pabellón a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nuevo nombre del pabellón:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la nueva capacidad del pabellón:");
        int capacidad = scanner.nextInt();

        String sql = "UPDATE Pabellon SET nombre = ?, capacidad = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, capacidad);
            pstmt.setInt(3, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n¡Pabellón actualizado exitosamente!");
            } else {
                System.out.println("\n¯\\_(ツ)_/¯");
                System.out.println("No se encontró el pabellón con el ID proporcionado.");
            }
        } catch (SQLException e) {
        }
    }

    private static void eliminarPabellon(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("   Eliminar Pabellon");
        System.out.println("==============================");
        System.out.print("Ingrese el ID del pabellón a eliminar: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Pabellon WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("\n¡Pabellón eliminado exitosamente!");
            } else {
                System.out.println("\n¯\\_(ツ)_/¯");
                System.out.println("No se encontró el pabellón con el ID proporcionado.");
            }
        } catch (SQLException e) {
        }
    }

    private static void verPabellones() {
        String sql = "SELECT * FROM Pabellon";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n==============================");
            System.out.println("       Lista de Pabellones");
            System.out.println("==============================");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int capacidad = rs.getInt("capacidad");
                System.out.println("Pabellón ID: " + id + ", Nombre: " + nombre + ", Capacidad: " + capacidad);
            }
        } catch (SQLException e) {
        }
    }

    // Método para verificar si un pabellón existe
    private static boolean pabellonExiste(String nombre) {
        String sql = "SELECT COUNT(*) FROM Pabellon WHERE nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }
}
