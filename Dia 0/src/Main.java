import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EstudianteCRUD estudianteCRUD = new EstudianteCRUD();
        ProfesorCRUD profesorCRUD = new ProfesorCRUD();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("---- Menu CRUD Colegio ----");
            System.out.println("1. Crear Estudiante");
            System.out.println("2. Leer Estudiantes");
            System.out.println("3. Actualizar Estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("5. Crear Profesor");
            System.out.println("6. Leer Profesores");
            System.out.println("7. Actualizar Profesor");
            System.out.println("8. Eliminar Profesor");
            System.out.println("9. Salir");
            System.out.println("Seleccione una opción:");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    estudianteCRUD.crearEstudiante();
                    break;
                case 2:
                    estudianteCRUD.leerEstudiantes();
                    break;
                case 3:
                    estudianteCRUD.actualizarEstudiante();
                    break;
                case 4:
                    estudianteCRUD.eliminarEstudiante();
                    break;
                case 5:
                    profesorCRUD.crearProfesor();
                    break;
                case 6:
                    profesorCRUD.leerProfesores();
                    break;
                case 7:
                    profesorCRUD.actualizarProfesor();
                    break;
                case 8:
                    profesorCRUD.eliminarProfesor();
                    break;
                case 9:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 9);
    }
}
