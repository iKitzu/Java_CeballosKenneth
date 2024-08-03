package LigaBaloncesto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LigaBaloncesto {
    private static ArrayList<Partido> partidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar la entrada no válida
                continue;
            }
            scanner.nextLine(); // Consumir nueva línea
            switch (opcion) {
                case 1:
                    registrarPartidoLiga();
                    break;
                case 2:
                    registrarPartidoPlayOff();
                    break;
                case 3:
                    finalizarPartido();
                    break;
                case 4:
                    mostrarGanador();
                    break;
                case 5:
                    mostrarInformacionPartido();
                    break;
                case 6:
                    modificarPartido();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("======== Menú ========");
        System.out.println("1. Registrar Partido de Liga");
        System.out.println("2. Registrar Partido de PlayOff");
        System.out.println("3. Finalizar Partido");
        System.out.println("4. Mostrar Ganador");
        System.out.println("5. Mostrar Información del Partido");
        System.out.println("6. Modificar Partido");
        System.out.println("7. Salir");
        System.out.println("======================");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarPartidoLiga() {
        System.out.print("Equipo Local: ");
        String equipoLocal = scanner.nextLine();
        System.out.print("Equipo Visitante: ");
        String equipoVisitante = scanner.nextLine();
        LocalDate fecha = Partido.leerFechaDesdeConsola();
        int jornada;
        while (true) {
            try {
                System.out.print("Jornada (número): ");
                jornada = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar la entrada no válida
            }
        }

        Partido partido = new PartidoLiga(equipoLocal, equipoVisitante, fecha, jornada);
        partidos.add(partido);
        System.out.println("Partido de Liga registrado exitosamente.");
    }

    private static void registrarPartidoPlayOff() {
        System.out.print("Equipo Local: ");
        String equipoLocal = scanner.nextLine();
        System.out.print("Equipo Visitante: ");
        String equipoVisitante = scanner.nextLine();
        LocalDate fecha = Partido.leerFechaDesdeConsola();
        System.out.print("Ronda: ");
        String ronda = scanner.nextLine();

        Partido partido = new PartidoPlayOff(equipoLocal, equipoVisitante, fecha, ronda);
        partidos.add(partido);
        System.out.println("Partido de PlayOff registrado exitosamente.");
    }

    private static void finalizarPartido() {
        System.out.print("Ingrese el índice del partido a finalizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (indice >= 0 && indice < partidos.size()) {
            partidos.get(indice).finalizarPartido();
            System.out.println("Partido finalizado.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private static void mostrarGanador() {
        System.out.print("Ingrese el índice del partido: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (indice >= 0 && indice < partidos.size()) {
            System.out.println(partidos.get(indice).obtenerGanador());
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private static void mostrarInformacionPartido() {
        System.out.print("Ingrese el índice del partido: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (indice >= 0 && indice < partidos.size()) {
            System.out.println(partidos.get(indice).obtenerInformacion());
            System.out.println(partidos.get(indice).obtenerResultado());
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private static void modificarPartido() {
        System.out.print("Ingrese el índice del partido a modificar: ");
        int indice = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (indice >= 0 && indice < partidos.size()) {
            Partido partido = partidos.get(indice);
            System.out.println("Información actual del partido:");
            System.out.println(partido.obtenerInformacion());

            System.out.println("Modificar opciones:");
            System.out.println("1. Fecha");
            System.out.println("2. Equipos");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    LocalDate nuevaFecha = Partido.leerFechaDesdeConsola();
                    partido.setFecha(nuevaFecha);
                    System.out.println("Fecha actualizada.");
                    break;
                case 2:
                    System.out.print("Nuevo Equipo Local: ");
                    partido.equipoLocal = scanner.nextLine();
                    System.out.print("Nuevo Equipo Visitante: ");
                    partido.equipoVisitante = scanner.nextLine();
                    System.out.println("Equipos actualizados.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("Índice no válido.");
        }
    }
}
