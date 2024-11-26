import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in); // Scanner global
    private static final Random random = new Random(); // Generador de números aleatorios

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> seleccionarPlaneta();
                case 2 -> seleccionarNave();
                case 3 -> {
                    try {
                        simularViaje();
                    } catch (InterruptedException e) {
                        System.err.println("Error en la simulación: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("Saliendo del simulador...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close(); // Cierra el Scanner al finalizar el programa
    }

    public static void mostrarMenu() {
        System.out.println("\nMenú de opciones:");
        System.out.println("1. Seleccionar planeta de destino");
        System.out.println("2. Seleccionar nave espacial");
        System.out.println("3. Iniciar simulación del viaje");
        System.out.println("4. Salir");
    }

    public static void seleccionarPlaneta() {
        String[] planetas = {"Marte", "Júpiter", "Saturno"};
        int[] distancias = {225000000, 778000000, 1427000000};

        System.out.println("\nPlanetas disponibles:");
        for (int i = 0; i < planetas.length; i++) {
            System.out.println((i + 1) + ". " + planetas[i] + " - " + distancias[i] + " km");
        }

        System.out.print("Seleccione un planeta (1-3): ");
        int opcion = scanner.nextInt();

        if (opcion >= 1 && opcion <= planetas.length) {
            System.out.println("Planeta seleccionado: " + planetas[opcion - 1]);
            System.out.println("Distancia al planeta: " + distancias[opcion - 1] + " km");
        } else {
            System.out.println("Selección no válida. Intente nuevamente.");
        }
    }

    public static void seleccionarNave() {
        String[] naves = {"Nave A (100,000 km/h)", "Nave B (150,000 km/h)", "Nave C (200,000 km/h)"};
        int[] velocidades = {100000, 150000, 200000};

        System.out.println("\nNaves disponibles:");
        for (int i = 0; i < naves.length; i++) {
            System.out.println((i + 1) + ". " + naves[i]);
        }

        System.out.print("Seleccione una nave (1-3): ");
        int opcion = scanner.nextInt();

        if (opcion >= 1 && opcion <= naves.length) {
            System.out.println("Nave seleccionada: " + naves[opcion - 1]);
            System.out.println("Velocidad de la nave: " + velocidades[opcion - 1] + " km/h");
        } else {
            System.out.println("Selección no válida. Intente nuevamente.");
        }
    }

    public static void simularViaje() throws InterruptedException {
        System.out.println("\nIniciando simulación del viaje...");
        int progreso = 0;

        while (progreso < 100) {
            progreso += 20; // Incremento del progreso en 20%
            System.out.println("Progreso: " + progreso + "% completado");

            if (Math.random() < 0.5) { // Evento aleatorio con 50% de probabilidad
                System.out.println("¡Evento inesperado! Iniciando...");
                randomEvents();
            }

            Thread.sleep(1000); // Simula tiempo real (1 segundo)
        }

        System.out.println("¡Viaje completado con éxito!");
    }

    private static void randomEvents() throws InterruptedException {
        int randomMethod = random.nextInt(4); // Selección aleatoria del evento
        switch (randomMethod) {
            case 0 -> motorOff(scanner, random);
            case 1 -> overload(scanner, random);
            case 2 -> motorFailure(scanner, random);
            case 3 -> asteroidRain(scanner, random);
            default -> System.err.println("Error: evento aleatorio no válido.");
        }
    }

    private static void motorOff(Scanner input, Random random) throws InterruptedException {
        // Lógica del evento motorOff...
    }

    private static void overload(Scanner scanner, Random random) throws InterruptedException {
        // Lógica del evento overload...
    }

    private static void motorFailure(Scanner scanner, Random random) {
        // Lógica del evento motorFailure...
    }

    private static void asteroidRain(Scanner input, Random random) throws InterruptedException {
        // Lógica del evento asteroidRain...
    }
}
