import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EstudianteCRUD estudianteCRUD = new EstudianteCRUD();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("---- Menu CRUD Estudiantes ----");
            System.out.println("1. Crear Estudiante");
            System.out.println("2. Leer Estudiantes");
            System.out.println("3. Actualizar Estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("5. Salir");
            System.out.println("Seleccione una opción:");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> estudianteCRUD.crearEstudiante();
                case 2 -> estudianteCRUD.leerEstudiantes();
                case 3 -> estudianteCRUD.actualizarEstudiante();
                case 4 -> estudianteCRUD.eliminarEstudiante();
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }
}
