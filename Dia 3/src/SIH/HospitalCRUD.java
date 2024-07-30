package SIH;

import java.sql.*;
import java.util.Scanner;

public class HospitalCRUD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("========Menú========");
            System.out.println("1. Opciones Hospital");
            System.out.println("2. Opciones Pacientes");
            System.out.println("3. Opciones Personal");
            System.out.println("4. Opciones Pabellon");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    menuHospital(scanner);
                    break;
                case 2:
                    menuPaciente(scanner);
                    break;
                case 3:
                    menuPersonal(scanner);
                    break;
                case 4:
                    menuPabellon(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú de Hospital
    private static void menuHospital(Scanner scanner) {
        while (true) {
            System.out.println("========Opciones Hospital========");
            System.out.println("1. Crear Hospital");
            System.out.println("2. Actualizar Hospital");
            System.out.println("3. Eliminar Hospital");
            System.out.println("4. Ver Hospitales");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearHospital(scanner);
                    break;
                case 2:
                    actualizarHospital(scanner);
                    break;
                case 3:
                    eliminarHospital(scanner);
                    break;
                case 4:
                    verHospitales();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú de Paciente
    private static void menuPaciente(Scanner scanner) {
        while (true) {
            System.out.println("========Opciones Pacientes========");
            System.out.println("1. Crear Paciente");
            System.out.println("2. Actualizar Paciente");
            System.out.println("3. Eliminar Paciente");
            System.out.println("4. Ver Pacientes");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearPaciente(scanner);
                    break;
                case 2:
                    actualizarPaciente(scanner);
                    break;
                case 3:
                    eliminarPaciente(scanner);
                    break;
                case 4:
                    verPacientes();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú de Personal
    private static void menuPersonal(Scanner scanner) {
        while (true) {
            System.out.println("========Opciones Personal========");
            System.out.println("1. Crear Personal");
            System.out.println("2. Actualizar Personal");
            System.out.println("3. Eliminar Personal");
            System.out.println("4. Ver Personal");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearPersonal(scanner);
                    break;
                case 2:
                    actualizarPersonal(scanner);
                    break;
                case 3:
                    eliminarPersonal(scanner);
                    break;
                case 4:
                    verPersonal();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú de Pabellon
    private static void menuPabellon(Scanner scanner) {
        while (true) {
            System.out.println("========Opciones Pabellon========");
            System.out.println("1. Crear Pabellon");
            System.out.println("2. Actualizar Pabellon");
            System.out.println("3. Eliminar Pabellon");
            System.out.println("4. Ver Pabellones");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearPabellon(scanner);
                    break;
                case 2:
                    actualizarPabellon(scanner);
                    break;
                case 3:
                    eliminarPabellon(scanner);
                    break;
                case 4:
                    verPabellones();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Métodos CRUD para Hospital
    private static void crearHospital(Scanner scanner) {
        System.out.println("");
        System.out.println("**********************************");
        System.out.println("* Ingrese el nombre del hospital *");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la dirección del hospital:");
        String direccion = scanner.nextLine();

        String sql = "INSERT INTO Hospital (nombre, direccion) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);
            pstmt.executeUpdate();
            System.out.println("");
            System.out.println("*********************************");
            System.out.println("* Hospital creado exitosamente. *");
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizarHospital(Scanner scanner) {
        System.out.println("");
        System.out.println("*******************************************");
        System.out.println("* Ingrese el ID del hospital a actualizar *");

        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

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
                System.out.println("");
                System.out.println("**************************************");
                System.out.println("* Hospital actualizado exitosamente. *");
                System.out.println("**************************************");
                System.out.println("");
            } else {
                System.out.println("");
                System.out.println("*********************************************************");
                System.out.println("* No se encontró el hospital con el ID proporcionado :c *");
                System.out.println("*********************************************************");
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void eliminarHospital(Scanner scanner) {
        System.out.println("");
        System.out.println("*******************************************");
        System.out.println("* Ingrese el ID del hospital a eliminar *");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Hospital WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("");
                System.out.println("***********************************");
                System.out.println("* Hospital eliminado exitosamente *");
                System.out.println("***********************************");
            } else {
                System.out.println("");
                System.out.println("******************************************************");
                System.out.println("* No se encontró el hospital con el ID proporcionado *");
                System.out.println("******************************************************");
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void verHospitales() {
        String sql = "SELECT * FROM Hospital";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                System.out.println("Hospital ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos CRUD para Paciente
    private static void crearPaciente(Scanner scanner) {
        System.out.println("**********************************");
        System.out.println("* Ingrese el título del paciente *");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese los nombres del paciente:");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese los apellidos del paciente:");
        String apellidos = scanner.nextLine();
        System.out.println("Ingrese la dirección del paciente:");
        String direccion = scanner.nextLine();
        System.out.println("Ingrese la fecha de nacimiento del paciente (YYYY-MM-DD):");
        String fechaNacimiento = scanner.nextLine();
        System.out.println("Ingrese el nombre del pabellón al que pertenece el paciente:");
        String pabellonNombre = scanner.nextLine();

        if (!pabellonExiste(pabellonNombre)) {
            System.out.println("");
            System.out.println("*************************");
            System.out.println("* El pabellón no existe *");
            System.out.println("*************************");
            System.out.println("");
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
            System.out.println("");
            System.out.println("********************************");
            System.out.println("* Paciente creado exitosamente *");
            System.out.println("********************************");
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizarPaciente(Scanner scanner) {
        System.out.println("");
        System.out.println("*******************************************");
        System.out.println("* Ingrese el ID del paciente a actualizar *");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

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
            System.out.println("");
            System.out.println("*************************");
            System.out.println("* El pabellón no existe *");
            System.out.println("*************************");
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
                System.out.println("");
                System.out.println("*************************************");
                System.out.println("* Paciente actualizado exitosamente *");
                System.out.println("*************************************");
                System.out.println("");
            } else {
                System.out.println("");
                System.out.println("******************************************************");
                System.out.println("* No se encontró el paciente con el ID proporcionado *");
                System.out.println("******************************************************");
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void eliminarPaciente(Scanner scanner) {
        System.out.println("");
        System.out.println("*************************");
        System.out.println("Ingrese el ID del paciente a eliminar:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Paciente WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("");
                System.out.println("***********************************");
                System.out.println("* Paciente eliminado exitosamente *");
                System.out.println("***********************************");
                System.out.println("");
            } else {
                System.out.println("");
                System.out.println("******************************************************");
                System.out.println("* No se encontró el paciente con el ID proporcionado *");
                System.out.println("******************************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void verPacientes() {
        String sql = "SELECT * FROM Paciente";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
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
            e.printStackTrace();
        }
    }

    // Métodos CRUD para Personal
    private static void crearPersonal(Scanner scanner) {
        System.out.println("Ingrese los nombres del personal:");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese el título del personal:");
        String titulo = scanner.nextLine();

        String sql = "INSERT INTO Personal (nombres, titulo) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombres);
            pstmt.setString(2, titulo);
            pstmt.executeUpdate();
            System.out.println("Personal creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizarPersonal(Scanner scanner) {
        System.out.println("Ingrese el ID del personal a actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

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
                System.out.println("Personal actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el personal con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void eliminarPersonal(Scanner scanner) {
        System.out.println("Ingrese el ID del personal a eliminar:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Personal WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Personal eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el personal con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void verPersonal() {
        String sql = "SELECT * FROM Personal";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombres = rs.getString("nombres");
                String titulo = rs.getString("titulo");
                System.out.println("Personal ID: " + id + ", Nombres: " + nombres + ", Título: " + titulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos CRUD para Pabellon
    private static void crearPabellon(Scanner scanner) {
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
            System.out.println("Pabellón creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizarPabellon(Scanner scanner) {
        System.out.println("Ingrese el ID del pabellón a actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

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
                System.out.println("Pabellón actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el pabellón con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void eliminarPabellon(Scanner scanner) {
        System.out.println("Ingrese el ID del pabellón a eliminar:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Pabellon WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Pabellón eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el pabellón con el ID proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void verPabellones() {
        String sql = "SELECT * FROM Pabellon";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int capacidad = rs.getInt("capacidad");
                System.out.println("Pabellón ID: " + id + ", Nombre: " + nombre + ", Capacidad: " + capacidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }
}
