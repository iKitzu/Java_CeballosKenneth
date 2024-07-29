package SIH;

import java.sql.*;
import java.util.Scanner;

public class HospitalCRUD {

    // Crear instancia de Conexion
    private final Conexion conexion = new Conexion();

    public static void main(String[] args) {
        // Crear instancia de HospitalCRUD
        HospitalCRUD hospitalCRUD = new HospitalCRUD();

        // Ejecutar el menú principal
        hospitalCRUD.run();
    }

    // Método para ejecutar el menú principal
    public void run() {
        Connection conn = conexion.establecerConexion();
        if (conn == null) {
            System.err.println("No se pudo establecer conexión con la base de datos.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            
            while (running) {
                System.out.println("\nMenú Principal:");
                System.out.println("1. Opciones Hospital");
                System.out.println("2. Opciones Pacientes");
                System.out.println("3. Opciones Personal");
                System.out.println("4. Opciones Pabellón");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                
                switch (option) {
                    case 1:
                        hospitalMenu(conn, scanner);
                        break;
                    case 2:
                        pacienteMenu(conn, scanner);
                        break;
                    case 3:
                        personalMenu(conn, scanner);
                        break;
                    case 4:
                        pabellonMenu(conn, scanner);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            }
        }
        conexion.cerrarConexion(conn);
    }

    // Menú Hospital
    private void hospitalMenu(Connection conn, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nOpciones Hospital:");
            System.out.println("1. Crear Hospital");
            System.out.println("2. Leer Hospitales");
            System.out.println("3. Actualizar Hospital");
            System.out.println("4. Eliminar Hospital");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    createHospital(conn, scanner);
                    break;
                case 2:
                    readHospitals(conn);
                    break;
                case 3:
                    updateHospital(conn, scanner);
                    break;
                case 4:
                    deleteHospital(conn, scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // Menú Pacientes
    private void pacienteMenu(Connection conn, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nOpciones Pacientes:");
            System.out.println("1. Crear Paciente");
            System.out.println("2. Leer Pacientes");
            System.out.println("3. Actualizar Paciente");
            System.out.println("4. Eliminar Paciente");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    createPaciente(conn, scanner);
                    break;
                case 2:
                    readPacientes(conn);
                    break;
                case 3:
                    updatePaciente(conn, scanner);
                    break;
                case 4:
                    deletePaciente(conn, scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // Menú Personal
    private void personalMenu(Connection conn, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nOpciones Personal:");
            System.out.println("1. Crear Personal");
            System.out.println("2. Leer Personal");
            System.out.println("3. Actualizar Personal");
            System.out.println("4. Eliminar Personal");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    createPersonal(conn, scanner);
                    break;
                case 2:
                    readPersonal(conn);
                    break;
                case 3:
                    updatePersonal(conn, scanner);
                    break;
                case 4:
                    deletePersonal(conn, scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // Menú Pabellón
    private void pabellonMenu(Connection conn, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nOpciones Pabellón:");
            System.out.println("1. Crear Pabellón");
            System.out.println("2. Leer Pabellones");
            System.out.println("3. Actualizar Pabellón");
            System.out.println("4. Eliminar Pabellón");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    createPabellon(conn, scanner);
                    break;
                case 2:
                    readPabellones(conn);
                    break;
                case 3:
                    updatePabellon(conn, scanner);
                    break;
                case 4:
                    deletePabellon(conn, scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    // Métodos CRUD para Hospital
    private void createHospital(Connection conn, Scanner scanner) {
        System.out.print("Ingrese nombre del hospital: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese dirección del hospital: ");
        String direccion = scanner.nextLine();

        String sql = "INSERT INTO Hospital (nombre, direccion) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.executeUpdate();
            System.out.println("Hospital creado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear hospital: " + e.getMessage());
        }
    }

    private void readHospitals(Connection conn) {
        String sql = "SELECT * FROM Hospital";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Nombre: " + rs.getString("nombre") +
                                   ", Dirección: " + rs.getString("direccion"));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer hospitales: " + e.getMessage());
        }
    }

    private void updateHospital(Connection conn, Scanner scanner) {
        System.out.print("Ingrese ID del hospital a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese nuevo nombre del hospital: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nueva dirección del hospital: ");
        String direccion = scanner.nextLine();

        String sql = "UPDATE Hospital SET nombre = ?, direccion = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setInt(3, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Hospital actualizado exitosamente.");
            } else {
                System.out.println("Hospital no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar hospital: " + e.getMessage());
        }
    }

    private void deleteHospital(Connection conn, Scanner scanner) {
        System.out.print("Ingrese ID del hospital a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String sql = "DELETE FROM Hospital WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Hospital eliminado exitosamente.");
            } else {
                System.out.println("Hospital no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar hospital: " + e.getMessage());
        }
    }

    // Métodos CRUD para Pacientes
    private void createPaciente(Connection conn, Scanner scanner) {
        System.out.print("Ingrese título del paciente: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese nombres del paciente: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese apellidos del paciente: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese dirección del paciente: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese fecha de nacimiento del paciente (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Ingrese ID del pabellón al que pertenece: ");
        int pabellonId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String pabellonCheckSql = "SELECT COUNT(*) FROM Pabellon WHERE id = ?";
        try (PreparedStatement pabellonCheckStmt = conn.prepareStatement(pabellonCheckSql)) {
            pabellonCheckStmt.setInt(1, pabellonId);
            try (ResultSet rs = pabellonCheckStmt.executeQuery()) {
                rs.next();
                if (rs.getInt(1) == 0) {
                    System.out.println("El pabellón no existe.");
                    return;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar pabellón: " + e.getMessage());
        }

        String sql = "INSERT INTO Paciente (titulo, nombres, apellidos, direccion, fecha_nacimiento, pabellon_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setString(2, nombres);
            stmt.setString(3, apellidos);
            stmt.setString(4, direccion);
            stmt.setString(5, fechaNacimiento);
            stmt.setInt(6, pabellonId);
            stmt.executeUpdate();
            System.out.println("Paciente creado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear paciente: " + e.getMessage());
        }
    }

    private void readPacientes(Connection conn) {
        String sql = "SELECT * FROM Paciente";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Título: " + rs.getString("titulo") +
                                   ", Nombres: " + rs.getString("nombres") +
                                   ", Apellidos: " + rs.getString("apellidos") +
                                   ", Dirección: " + rs.getString("direccion") +
                                   ", Fecha de Nacimiento: " + rs.getDate("fecha_nacimiento") +
                                   ", ID del Pabellón: " + rs.getInt("pabellon_id"));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer pacientes: " + e.getMessage());
        }
    }

    private void updatePaciente(Connection conn, Scanner scanner) {
        System.out.print("Ingrese ID del paciente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese nuevo título del paciente: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese nuevos nombres del paciente: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese nuevos apellidos del paciente: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese nueva dirección del paciente: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese nueva fecha de nacimiento del paciente (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Ingrese nuevo ID del pabellón al que pertenece: ");
        int pabellonId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String pabellonCheckSql = "SELECT COUNT(*) FROM Pabellon WHERE id = ?";
        try (PreparedStatement pabellonCheckStmt = conn.prepareStatement(pabellonCheckSql)) {
            pabellonCheckStmt.setInt(1, pabellonId);
            try (ResultSet rs = pabellonCheckStmt.executeQuery()) {
                rs.next();
                if (rs.getInt(1) == 0) {
                    System.out.println("El pabellón no existe.");
                    return;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar pabellón: " + e.getMessage());
        }

        String sql = "UPDATE Paciente SET titulo = ?, nombres = ?, apellidos = ?, direccion = ?, fecha_nacimiento = ?, pabellon_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setString(2, nombres);
            stmt.setString(3, apellidos);
            stmt.setString(4, direccion);
            stmt.setString(5, fechaNacimiento);
            stmt.setInt(6, pabellonId);
            stmt.setInt(7, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Paciente actualizado exitosamente.");
            } else {
                System.out.println("Paciente no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
        }
    }

    private void deletePaciente(Connection conn, Scanner scanner) {
        System.out.print("Ingrese ID del paciente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String sql = "DELETE FROM Paciente WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Paciente eliminado exitosamente.");
            } else {
                System.out.println("Paciente no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar paciente: " + e.getMessage());
        }
    }

    // Métodos CRUD para Personal
    private void createPersonal(Connection conn, Scanner scanner) {
        System.out.print("Ingrese nombres del personal: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese título del personal: ");
        String titulo = scanner.nextLine();

        String sql = "INSERT INTO Personal (nombres, titulo) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombres);
            stmt.setString(2, titulo);
            stmt.executeUpdate();
            System.out.println("Personal creado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear personal: " + e.getMessage());
        }
    }

    private void readPersonal(Connection conn) {
        String sql = "SELECT * FROM Personal";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Nombres: " + rs.getString("nombres") +
                                   ", Título: " + rs.getString("titulo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer personal: " + e.getMessage());
        }
    }

    private void updatePersonal(Connection conn, Scanner scanner) {
        System.out.print("Ingrese ID del personal a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese nuevos nombres del personal: ");
        String nombres = scanner.nextLine();
        System.out.print("Ingrese nuevo título del personal: ");
        String titulo = scanner.nextLine();

        String sql = "UPDATE Personal SET nombres = ?, titulo = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombres);
            stmt.setString(2, titulo);
            stmt.setInt(3, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Personal actualizado exitosamente.");
            } else {
                System.out.println("Personal no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar personal: " + e.getMessage());
        }
    }

    private void deletePersonal(Connection conn, Scanner scanner) {
        System.out.print("Ingrese ID del personal a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String sql = "DELETE FROM Personal WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Personal eliminado exitosamente.");
            } else {
                System.out.println("Personal no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar personal: " + e.getMessage());
        }
    }

    // Métodos CRUD para Pabellón
    private void createPabellon(Connection conn, Scanner scanner) {
        System.out.print("Ingrese nombre del pabellón: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese capacidad del pabellón: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String sql = "INSERT INTO Pabellon (nombre, capacidad) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, capacidad);
            stmt.executeUpdate();
            System.out.println("Pabellón creado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear pabellón: " + e.getMessage());
        }
    }

    private void readPabellones(Connection conn) {
        String sql = "SELECT * FROM Pabellon";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Nombre: " + rs.getString("nombre") +
                                   ", Capacidad: " + rs.getInt("capacidad"));
            }
        } catch (SQLException e) {
            System.err.println("Error al leer pabellones: " + e.getMessage());
        }
    }

    private void updatePabellon(Connection conn, Scanner scanner) {
        System.out.print("Ingrese ID del pabellón a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese nuevo nombre del pabellón: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nueva capacidad del pabellón: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String sql = "UPDATE Pabellon SET nombre = ?, capacidad = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, capacidad);
            stmt.setInt(3, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Pabellón actualizado exitosamente.");
            } else {
                System.out.println("Pabellón no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar pabellón: " + e.getMessage());
        }
    }

    private void deletePabellon(Connection conn, Scanner scanner) {
        System.out.print("Ingrese ID del pabellón a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String sql = "DELETE FROM Pabellon WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Pabellón eliminado exitosamente.");
            } else {
                System.out.println("Pabellón no encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar pabellón: " + e.getMessage());
        }
    }
}
