import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfesorCRUD {
    private List<Profesor> profesores = new ArrayList<>();
    private int contadorId = 1;
    private Scanner scanner = new Scanner(System.in);

    public void crearProfesor() {
        System.out.println("Ingrese nombres:");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese apellidos:");
        String apellidos = scanner.nextLine();
        System.out.println("Ingrese departamento:");
        String departamento = scanner.nextLine();
        System.out.println("Ingrese telefono:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese correo:");
        String correo = scanner.nextLine();

        Profesor profesor = new Profesor(contadorId++, nombres, apellidos, departamento, telefono, correo);
        profesores.add(profesor);
        System.out.println("Profesor creado: " + profesor + " correctamente ");
    }

    public void leerProfesores() {
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        } else {
            for (Profesor profesor : profesores) {
                System.out.println(profesor);
            }
        }
    }

    public void actualizarProfesor() {
        System.out.println("Ingrese el ID del profesor a actualizar:");
        int id = Integer.parseInt(scanner.nextLine());
        Profesor profesor = buscarProfesorPorId(id);
        if (profesor == null) {
            System.out.println("Profesor no encontrado.");
            return;
        }

        System.out.println("Ingrese nuevos nombres (actual: " + profesor.getNombres() + "):");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese nuevos apellidos (actual: " + profesor.getApellidos() + "):");
        String apellidos = scanner.nextLine();
        System.out.println("Ingrese nuevo departamento (actual: " + profesor.getDepartamento() + "):");
        String departamento = scanner.nextLine();
        System.out.println("Ingrese nuevo telefono (actual: " + profesor.getTelefono() + "):");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese nuevo correo (actual: " + profesor.getCorreo() + "):");
        String correo = scanner.nextLine();

        profesor.setNombres(nombres);
        profesor.setApellidos(apellidos);
        profesor.setDepartamento(departamento);
        profesor.setTelefono(telefono);
        profesor.setCorreo(correo);

        System.out.println("Profesor actualizado: " + profesor);
    }

    public void eliminarProfesor() {
        System.out.println("Ingrese el ID del profesor a eliminar:");
        int id = Integer.parseInt(scanner.nextLine());
        Profesor profesor = buscarProfesorPorId(id);
        if (profesor == null) {
            System.out.println("Profesor no encontrado.");
            return;
        }

        profesores.remove(profesor);
        System.out.println("Profesor eliminado: " + profesor);
    }

    private Profesor buscarProfesorPorId(int id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId() == id) {
                return profesor;
            }
        }
        return null;
    }
}
