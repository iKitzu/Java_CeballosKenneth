import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear hospital
        Hospital hospital1 = new Hospital("Hospital Central", "Calle Principal 123", new ArrayList<>());

        // Crear departamentos
        Departamento depto1 = new Departamento("Cardiología", new ArrayList<>());
        Departamento depto2 = new Departamento("Neurología", new ArrayList<>());

        // Agregar departamentos al hospital
        hospital1.getDepartamentos().add(depto1);
        hospital1.getDepartamentos().add(depto2);

        // Crear personal
        Personal admin1 = new Personal("Carlos Perez", "Calle Falsa 123", new Date(), new Date(), 50000);
        Personal tecnico1 = new Personal("Ana Gomez", "Avenida Siempre Viva 456", new Date(), new Date(), 40000);
        Doctor doctor1 = new Doctor("Luis Martinez", "Boulevard de los Sueños Rotos 789", new Date(), new Date(), 70000, true, new ArrayList<>());
        Doctor doctor2 = new Doctor("Laura Sanchez", "Calle de la Amargura 101", new Date(), new Date(), 65000, false, new ArrayList<>());

        // Agregar personal a departamentos
        depto1.getPersonal().add(admin1);
        depto1.getPersonal().add(tecnico1);
        depto1.getPersonal().add(doctor1);
        depto2.getPersonal().add(doctor2);

        // Crear pacientes
        Paciente paciente1 = new Paciente("Pedro Ramirez", "Calle del Sol 202", new Date(), new Date(), "Pabellón A");
        Paciente paciente2 = new Paciente("Maria Lopez", "Avenida Luna 303", new Date(), new Date(), "Pabellón B");

        // Mostrar información del hospital
        System.out.println("Hospital: " + hospital1.getNombre());
        System.out.println("Dirección: " + hospital1.getDireccion());
        System.out.println("Departamentos:");
        for (Departamento depto : hospital1.getDepartamentos()) {
            System.out.println(" - " + depto.getNombre());
            for (Personal personal : depto.getPersonal()) {
                System.out.println("   * " + personal.getNombre() + " (" + personal.getClass().getSimpleName() + ")");
            }
        }

        // Mostrar información de pacientes
        System.out.println("Pacientes:");
        System.out.println(" - " + paciente1.getNombre() + " (Pabellón: " + paciente1.getPabellon() + ")");
        System.out.println(" - " + paciente2.getNombre() + " (Pabellón: " + paciente2.getPabellon() + ")");
    }
}
