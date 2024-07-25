import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Clase principal para el sistema de gestión del hospital
public class HospitalManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Hospital hospital = new Hospital("Hospital Central");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Sistema Integrado Hospitalario (SIH)");
            System.out.println("1. Agregar Persona");
            System.out.println("2. Agregar Departamento");
            System.out.println("3. Agregar Personal");
            System.out.println("4. Mostrar Personas");
            System.out.println("5. Mostrar Departamentos");
            System.out.println("6. Mostrar Personal");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1 -> addPersona();
                case 2 -> addDepartamento();
                case 3 -> addPersonal();
                case 4 -> showPersonas();
                case 5 -> showDepartamentos();
                case 6 -> showPersonal();
                case 7 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void addPersona() {
        System.out.print("Nombre Completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
        String fechaNacimientoStr = scanner.nextLine();
        Date fechaNacimiento = parseDate(fechaNacimientoStr);
        System.out.print("Pabellón: ");
        String pabellon = scanner.nextLine();

        Paciente paciente = new Paciente(nombreCompleto, direccion, fechaNacimiento, pabellon);
        hospital.addPersona(paciente);
        System.out.println("Paciente agregado.");
    }

    private static void addDepartamento() {
        System.out.print("Nombre del Departamento: ");
        String nombre = scanner.nextLine();
        Departamento departamento = new Departamento(nombre);
        hospital.addDepartamento(departamento);
        System.out.println("Departamento agregado.");
    }

    private static void addPersonal() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Fecha de Vinculación (YYYY-MM-DD): ");
        String fechaVinculacionStr = scanner.nextLine();
        Date fechaVinculacion = parseDate(fechaVinculacionStr);
        System.out.print("Salario: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.println("Tipo de Personal (Administrativo/Técnico/Operaciones): ");
        String tipo = scanner.nextLine();

        Personal personal = null;
        if (tipo.equalsIgnoreCase("Administrativo")) {
            personal = new PersonalAdministrativo(nombre, fechaVinculacion, salario);
        } else if (tipo.equalsIgnoreCase("Técnico")) {
            personal = new PersonalTecnico(nombre, fechaVinculacion, salario);
        } else if (tipo.equalsIgnoreCase("Operaciones")) {
            personal = new PersonalOperaciones(nombre, fechaVinculacion, salario);
        }

        if (personal != null) {
            System.out.print("Nombre del Departamento: ");
            String departamentoNombre = scanner.nextLine();
            Departamento departamento = hospital.getDepartamentos().stream()
                .filter(d -> d.getNombre().equalsIgnoreCase(departamentoNombre))
                .findFirst().orElse(null);
            if (departamento != null) {
                departamento.addPersonal(personal);
                System.out.println("Personal agregado al departamento.");
            } else {
                System.out.println("Departamento no encontrado.");
            }
        } else {
            System.out.println("Tipo de personal no válido.");
        }
    }

    private static void showPersonas() {
        System.out.println("Listado de Personas:");
        for (Persona persona : hospital.getPersonas()) {
            System.out.println("Nombre: " + persona.getNombreCompleto() + ", Dirección: " + persona.getDireccion());
        }
    }

    private static void showDepartamentos() {
        System.out.println("Listado de Departamentos:");
        for (Departamento departamento : hospital.getDepartamentos()) {
            System.out.println("Departamento: " + departamento.getNombre());
        }
    }

    private static void showPersonal() {
        System.out.println("Listado de Personal:");
        for (Departamento departamento : hospital.getDepartamentos()) {
            System.out.println("Departamento: " + departamento.getNombre());
            for (Personal personal : departamento.getPersonal()) {
                System.out.println("Nombre: " + personal.getNombre() + ", Salario: " + personal.getSalario() + ", Fecha de Vinculación: " + dateFormat.format(personal.getFechaVinculacion()));
            }
        }
    }

    private static Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Fecha inválida. Usando fecha actual.");
            return new Date();
        }
    }
}
