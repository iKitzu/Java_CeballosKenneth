package quizjava;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AlbionOnline {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Albion albion = new Albion(); // Instancia de Albion para registrar héroes y villanos
    private static final Herrero herrero = new Herrero();

    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    registrarHeroe();
                    break;
                case 2:
                    registrarVillano();
                    break;
                case 3:
                    crearArma();
                    break;
                case 4:
                    System.out.println("\nGracias Por Jugar!");
                    System.out.println("Cerrando Sesión...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 4.");
            }
        } while (opcion != 4);
    }

    private static void mostrarMenu() {
        System.out.println("\n========  Bienvenido a Albion  ========");
        System.out.println("|                                      |");
        System.out.println("| 1. Registrarse Como Héroe            |");
        System.out.println("|                                      |");
        System.out.println("| 2. Registrarse Como Villano          |");
        System.out.println("|                                      |");
        System.out.println("| 3. Crear Arma                        |");
        System.out.println("|                                      |");
        System.out.println("| 4. Salir de Albion                   |");
        System.out.println("|                                      |");
        System.out.println("=======================================");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        int opcion = 0;
        boolean opcionValida = false;
        while (!opcionValida) {
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                if (opcion >= 1 && opcion <= 4) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción fuera de rango. Por favor, seleccione una opción del 1 al 4.");
                    mostrarMenu();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer
                mostrarMenu();
            }
        }
        return opcion;
    }

    private static void registrarHeroe() {
        if (albion.getHeroe() != null) {
            System.out.println("\nYa existe un héroe en el mundo. Solo se puede tener un héroe por mundo.");
            return;
        }
        
        System.out.println("\n=== Registrarse en el Gremio ===");
        System.out.print("Nickname: ");
        String nickname = scanner.nextLine();
        String arma = seleccionarArma();

        Heroe heroe = Heroe.getInstancia(nickname, arma);
        albion.registrarHeroe(heroe);
        mostrarMenuHeroe(heroe);
    }

    private static void registrarVillano() {
        System.out.println("\n=== Registrarse Como Villano ===");
        System.out.print("Nickname: ");
        String nickname = scanner.nextLine();
        String arma = seleccionarArma();
        System.out.print("¿Qué raza eres?: ");
        String raza = scanner.nextLine();

        Villano villano = Villano.crearVillano(raza, nickname, arma);
        albion.registrarVillano(villano);
        mostrarMenuVillano(villano);
    }

    private static void crearArma() {
        System.out.println("\n=== Crear Arma ===");
        System.out.print("Seleccione el tipo de trabajo: ");
        System.out.println("1. Élfico");
        System.out.println("2. Enano");
        int tipoTrabajo = leerOpcionTipoTrabajo();

        String tipo = (tipoTrabajo == 1) ? "Élfico" : (tipoTrabajo == 2) ? "Enano" : "Desconocido";
        Arma arma = herrero.fabricarArma(tipo);

        if (arma != null) {
            System.out.println("Arma creada: " + arma.getNombre());
            System.out.println("Seleccione un encantamiento: ");
            System.out.println("1. Fuego");
            System.out.println("2. Vorpal");
            Encantamiento encantamiento = seleccionarEncantamiento();

            if (encantamiento != null) {
                arma.setEncantamiento(encantamiento);
                System.out.println("Encantamiento aplicado: " + encantamiento.getNombre());
            }
        } else {
            System.out.println("No se pudo crear el arma.");
        }
    }

    private static String seleccionarArma() {
        String arma = "";
        boolean armaValida = false;
        while (!armaValida) {
            System.out.println("1. Espada");
            System.out.println("2. Hacha");
            System.out.println("3. Lanza");
            System.out.print("Seleccione su arma: ");
            int opcionArma = leerOpcion();
            switch (opcionArma) {
                case 1:
                    arma = "Espada";
                    armaValida = true;
                    break;
                case 2:
                    arma = "Hacha";
                    armaValida = true;
                    break;
                case 3:
                    arma = "Lanza";
                    armaValida = true;
                    break;
                default:
                    System.out.println("Arma no válida. Por favor, seleccione una opción del 1 al 3.");
                    break;
            }
        }
        return arma;
    }

    private static int leerOpcionTipoTrabajo() {
        int tipoTrabajo = 0;
        boolean tipoValido = false;
        while (!tipoValido) {
            try {
                tipoTrabajo = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                if (tipoTrabajo == 1 || tipoTrabajo == 2) {
                    tipoValido = true;
                } else {
                    System.out.println("Tipo de trabajo no válido. Por favor, seleccione 1 para Élfico o 2 para Enano.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar el buffer
            }
        }
        return tipoTrabajo;
    }

    private static Encantamiento seleccionarEncantamiento() {
        Encantamiento encantamiento = null;
        boolean encantamientoValido = false;
        while (!encantamientoValido) {
            int opcionEncantamiento = leerOpcion();
            switch (opcionEncantamiento) {
                case 1:
                    encantamiento = new EncantamientoFuego();
                    encantamientoValido = true;
                    break;
                case 2:
                    encantamiento = new EncantamientoVorpal();
                    encantamientoValido = true;
                    break;
                default:
                    System.out.println("Encantamiento no válido. Por favor, seleccione 1 para Fuego o 2 para Vorpal.");
                    break;
            }
        }
        return encantamiento;
    }

    private static void mostrarMenuHeroe(Heroe heroe) {
        System.out.println("\n=== Bienvenido al Menú del Héroe ===");
        System.out.println("Hola, " + heroe.getNickname() + "! ¿Qué te gustaría hacer?");
        // Puedes añadir opciones adicionales para el héroe aquí
        System.out.println("1. Ver detalles del héroe");
        System.out.println("2. Regresar al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = leerOpcion();
        switch (opcion) {
            case 1:
                System.out.println("Detalles del héroe: ");
                System.out.println("Nickname: " + heroe.getNickname());
                System.out.println("Arma: " + heroe.getArma());
                break;
            case 2:
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void mostrarMenuVillano(Villano villano) {
        System.out.println("\n=== Bienvenido al Menú del Villano ===");
        System.out.println("Hola, " + villano.getNickname() + "! ¿Qué te gustaría hacer?");
        // Puedes añadir opciones adicionales para el villano aquí
        System.out.println("1. Ver detalles del villano");
        System.out.println("2. Regresar al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = leerOpcion();
        switch (opcion) {
            case 1:
                System.out.println("Detalles del villano: ");
                System.out.println("Nickname: " + villano.getNickname());
                System.out.println("Arma: " + villano.getArma());
                System.out.println("Raza: " + villano.getRaza());
                break;
            case 2:
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}
