import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EstudianteCRUD {
    private List<Estudiante> estudiantes = new ArrayList<>();
    private int contadorId = 1;
    private Scanner scanner = new Scanner(System.in);

    public void crearEstudiante() {
        System.out.println("Ingrese nombres:");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese apellidos:");
        String apellidos = scanner.nextLine();
        System.out.println("Ingrese direccion:");
        String direccion = scanner.nextLine();
        System.out.println("Ingrese telefono:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese estado:");
        String estado = scanner.nextLine();

        Estudiante estudiante = new Estudiante(contadorId++, nombres, apellidos, direccion, telefono, estado);
        estudiantes.add(estudiante);
        System.out.println("Estudiante creado: " + estudiante);
    }

    public void leerEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for (Estudiante estudiante : estudiantes) {
                System.out.println(estudiante);
            }
        }
    }

    public void actualizarEstudiante() {
        System.out.println("Ingrese el ID del estudiante a actualizar:");
        int id = Integer.parseInt(scanner.nextLine());
        Estudiante estudiante = buscarEstudiantePorId(id);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.println("Ingrese nuevos nombres (actual: " + estudiante.getNombres() + "):");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese nuevos apellidos (actual: " + estudiante.getApellidos() + "):");
        String apellidos = scanner.nextLine();
        System.out.println("Ingrese nueva direccion (actual: " + estudiante.getDireccion() + "):");
        String direccion = scanner.nextLine();
        System.out.println("Ingrese nuevo telefono (actual: " + estudiante.getTelefono() + "):");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese nuevo estado (actual: " + estudiante.getEstado() + "):");
        String estado = scanner.nextLine();

        estudiante.setNombres(nombres);
        estudiante.setApellidos(apellidos);
        estudiante.setDireccion(direccion);
        estudiante.setTelefono(telefono);
        estudiante.setEstado(estado);

        System.out.println("Estudiante actualizado: " + estudiante);
    }

    public void eliminarEstudiante() {
        System.out.println("Ingrese el ID del estudiante a eliminar:");
        int id = Integer.parseInt(scanner.nextLine());
        Estudiante estudiante = buscarEstudiantePorId(id);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        estudiantes.remove(estudiante);
        System.out.println("Estudiante eliminado: " + estudiante);
    }

    private Estudiante buscarEstudiantePorId(int id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                return estudiante;
            }
        }
        return null;
    }
}
