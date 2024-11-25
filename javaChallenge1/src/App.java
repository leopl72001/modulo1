import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
      

    }
    private static void motorOff(Scanner input, Random random) throws InterruptedException {
        System.out.println("|-----------------------------------|");
        System.out.println("|             ALERTA!!!             |");
        System.out.println("|-----------------------------------|");
        System.out.println("|    Tu nave presenta fallas y      |");
        System.out.println("|    se apagara si no corriges      |");
        System.out.println("|             la falla              |");
        System.out.println("|-----------------------------------|");
        pressEnter(input);

        int seconds = 15;
        String[] operation = { "7 x 8", "8 x 8", "7 x 7", "77 x 88" };
        int[] result = { 7 * 8, 8 * 8, 7 * 7, 77 * 88 };
        int randomIndex = random.nextInt(operation.length); // Seleccionar operación aleatoria

        boolean solved = false; // Bandera para verificar si se resolvió correctamente
        String userResponse = ""; // Para guardar la respuesta del usuario

        // Iniciar el temporizador
        long startTime = System.currentTimeMillis(); // Capturar el tiempo de inicio

        while (seconds >= 0) {
            // Limpiar la consola solo al final del ciclo
            if (seconds != 15) {
                clearScreen();
            }

            // Mostrar el mensaje de operación y el tiempo restante
            System.out.println("|-----------------------------------|");
            System.out.println("|       para corregirlo resuelve    |");
            System.out.println("|       esta operacion tienes:      |");
            System.out.println("|            " + seconds + " segundos            |");
            System.out.println("|-----------------------------------|");
            System.out.println("|       ¿Cuánto es " + operation[randomIndex] + "?           |");
            System.out.println("|-----------------------------------|");
            System.out.print("   \nRespuesta: " + (userResponse.isEmpty() ? "" : userResponse)); // Mostrar la respuesta o espacio vacío

            // Leer la respuesta del usuario
            if (input.hasNext()) {
                String inputResponse = input.next(); // Captura lo que el usuario ingresa

                // Verificar si la entrada es un número entero
                try {
                    int answer = Integer.parseInt(inputResponse); // Intenta convertir la respuesta a un número entero
                    userResponse = inputResponse; // Actualiza la respuesta del usuario

                    // Comprobar si la respuesta es correcta
                    if (answer == result[randomIndex]) {
                        solved = true;
                        break;
                    } else {
                        System.err.println("Respuesta incorrecta. Intenta nuevamente...");
                        userResponse = ""; // Borrar la respuesta incorrecta
                    }
                } catch (NumberFormatException e) {
                    // Si ocurre un error de formato (el usuario no ingresa un número), muestra un mensaje y sigue pidiendo una respuesta válida
                    System.err.println("Entrada no válida. Por favor ingresa un número.");
                }
            }

            // Calcular el tiempo restante
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Tiempo transcurrido en segundos
            seconds = 15 - (int)elapsedTime; // Actualizar los segundos restantes

            // Si el tiempo aún no ha pasado, esperar 1 segundo antes de la siguiente actualización
            if (seconds >= 0) {
                Thread.sleep(1000); // Pausa de 1 segundo
            }
        }

        // Mensaje final dependiendo de si la respuesta fue correcta
        if (solved) {
            System.out.println("|-----------------------------------|");
            System.out.println("| ¡Felicidades! Has corregido la    |");
            System.out.println("|        falla de la nave.          |");
            System.out.println("|-----------------------------------|");
        } else {
            System.out.println("|-----------------------------------|");
            System.out.println("|        ¡Se acabó el tiempo!       |");
            System.out.println("|      La nave ha sido apagada.     |");
            System.out.println("|-----------------------------------|");
        }
    }

    
    private static void overload(Scanner scanner, Random random) throws InterruptedException {
        int[] todosLosNumeros = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        // Crear un array para la secuencia aleatoria de 3 números
        int[] secuencia = new int[3];

        // Seleccionar 3 números aleatorios de los 9 posibles
        for (int i = 0; i < secuencia.length; i++) {
            int indiceAleatorio = random.nextInt(todosLosNumeros.length - i); // índice aleatorio para los números restantes
            secuencia[i] = todosLosNumeros[indiceAleatorio];
            // Desplazar el número seleccionado al final para evitar repetirlo
            todosLosNumeros[indiceAleatorio] = todosLosNumeros[todosLosNumeros.length - i - 1];
        }

        System.out.println("|-------------------------------|");
        System.out.println("|           Alerta!!!           |");
        System.out.println("|     ¡Sobrecarga detectada!    |");
        System.out.println("|-------------------------------|");
        pressEnter(scanner);

        System.out.println("\nApaga los sistemas en este orden:");
        // Mostrar la secuencia de números aleatoria
        for (int num : secuencia) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Inicializar el temporizador
        int tiempoRestante = 9;
        long startTime = System.currentTimeMillis(); // Tiempo inicial

        // Pedir al usuario que ingrese los números en el orden correcto
        int[] respuestaUsuario = new int[3];
        for (int i = 0; i < secuencia.length; i++) {
            // Asegurar que el tiempo se actualice en cada ciclo
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Calcular el tiempo transcurrido en segundos
            tiempoRestante = 9 - (int) elapsedTime; // Actualizar el tiempo restante

            if (tiempoRestante <= 0) {
                System.out.println("|---------------------------------|");
                System.out.println("|       se acabo el tiempo.       |");
                System.out.println("|       Sobrecarga mantenida      |");
                System.out.println("|       ¡La nave explotará!       |");
                System.out.println("|---------------------------------|");
                scanner.close();
                return; // Termina el programa si el tiempo se agotó
            }

            System.out.println("\n|-----------------------|");
            System.out.println("    Tienes " + tiempoRestante + " segundos   ");
            System.out.println("|-----------------------|");
            System.out.print("\nIntroduce el número " + (i + 1) + ": ");
            respuestaUsuario[i] = scanner.nextInt();

            // Si la secuencia es incorrecta, termina el ciclo
            if (respuestaUsuario[i] != secuencia[i]) {
                System.out.println("|---------------------------------|");
                System.out.println("|      Error en la secuencia.     |");
                System.out.println("|       Sobrecarga mantenida      |");
                System.out.println("|       ¡La nave explotará!       |");
                System.out.println("|---------------------------------|");
                scanner.close();
                return;
            }

            // Esperar 1 segundo antes de pedir el siguiente número
            Thread.sleep(1000);
        }

        // Si el usuario ingresa correctamente todos los números antes de que se agote
        // el tiempo
        System.out.println("\n|----------------------------|");
        System.out.println("|    ¡Sistemas apagados      |");
        System.out.println("|       correctamente!       |");
        System.out.println("|----------------------------|");
    }


    private static void motorFailure(Scanner scanner, Random random) {
        int code = random.nextInt(30) + 1;

        System.out.println("|-----------------------------------|");
        System.out.println("|             Alerta!!!             |");
        System.out.println("|-----------------------------------|");
        System.out.println("|         ¡Fallo de motor!          |");
        System.out.println("|-----------------------------------|");
        pressEnter(scanner);

        System.out.println("\n|-------------------------------------------|");
        System.out.println("|              para repararlo               |");
        System.out.println("|       Adivina el número entre 1 y 30.     9999999999999999999999999999999999| ");
        System.out.println("|            Tienes 20 segundos.            |");
        System.out.println("|-------------------------------------------|");

        long startTime = System.currentTimeMillis(); // Tiempo inicial
        int timeLimit = 20; // Tiempo límite en segundos

        while (true) {
            // Calcular el tiempo restante
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Tiempo transcurrido en segundos
            int timeRemaining = timeLimit - (int) elapsedTime;

            // Verificar si el tiempo se ha agotado
            if (timeRemaining <= 0) {
                System.out.println("\n|-----------------------------------|");
                System.out.println("|   ¡Se acabó el tiempo! el Motor   |");
                System.out.println("|      murió, tal como te pasará.   |");
                System.out.println("|-----------------------------------|");
                break;
            }

            System.out.println("\n|---------------------------------------------|");
            System.out.println("         Tiempo restante: " + timeRemaining + " segundos  ");
            System.out.println("|---------------------------------------------|");
            System.out.print("\nIntroduce tu intento: ");

            try {
                int attempt = Integer.parseInt(scanner.nextLine().trim()); // Validar entrada

                if (attempt == code) {
                    System.out.println("\n|-----------------------------------|");
                    System.out.println("|     ¡Código correcto! Motor       |");
                    System.out.println("|         restaurado.               |");
                    System.out.println("|-----------------------------------|");
                    break;
                } else {
                    int difference = Math.abs(code - attempt);

                    if (difference <= 5) {
                        System.out.println("¡Estás muy cerca!");
                    } else if (difference <= 15) {
                        System.out.println("Estás cerca.");
                    } else {
                        System.out.println("Estás lejos.");
                    }

                    if (attempt < code) {
                        System.out.println("Te falta por llegar. Intenta un número mayor.");
                    } else {
                        System.out.println("Te pasaste. Intenta un número menor.");
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("|-----------------------------------------|");
                System.err.println("|   ¡Error! Entrada no válida. Ingresa    |");
                System.err.println("|      un número entre 1 y 30.            |");
                System.err.println("|-----------------------------------------|");
                
            }
        }
    }


    private static void asteroidRain(Scanner input, Random random) throws InterruptedException {
        System.out.println("|-----------------------------------|");
        System.out.println("|             ALERTA!!!             |");
        System.out.println("|-----------------------------------|");
        System.out.println("|    Presentamos una lluvia de      |");
        System.out.println("|            meteoritos             |");
        System.out.println("|-----------------------------------|");
        pressEnter(input);

        int seconds = 10;
        String[] country = { "E.E.U.U", "COLOMBIA", "CHIPRE", "ARGENTINA", "RUMANIA", "INDONESIA" };
        String[] result = { "washington", "bogota", "nicosia", "buenos aires", "bucarest", "yakarta" };
        int randomIndex = random.nextInt(country.length);

        boolean solved = false;
        String userResponse = "";

        long startTime = System.currentTimeMillis();

        while (seconds >= 0) {
            clearScreen(); // Limpia la consola en cada iteración

            System.out.println("|-----------------------------------|");
            System.out.println("|      Para activar el escudo       |");
            System.out.println("| Di la capital de: " + country[randomIndex] + "         |");
            System.out.println("| Tienes " + seconds + " segundos            |");
            System.out.println("|-----------------------------------|");
            System.out.print("\nRespuesta: " + (userResponse.isEmpty() ? "" : userResponse));

            if (input.hasNextLine()) {
                userResponse = input.nextLine().trim(); // Lee y limpia la entrada del usuario

                if (userResponse.equalsIgnoreCase(result[randomIndex])) {
                    solved = true; // Marca como resuelto si es correcto
                    break;
                } else {
                    System.out.println("Respuesta incorrecta. Intenta de nuevo");
                    userResponse = ""; // Limpia la respuesta para el próximo intento
                }
            }

            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            seconds = 10 - (int) elapsedTime;

            if (seconds > 0) {
                Thread.sleep(1000);
            }
        }

        clearScreen(); // Limpia la consola antes de mostrar el resultado final

        if (solved) {
            System.out.println("|-----------------------------------|");
            System.out.println("|     ¡Felicidades! Has podido      |");
            System.out.println("|        activar el escudo          |");
            System.out.println("|-----------------------------------|");
        } else {
            System.out.println("|-----------------------------------|");
            System.out.println("|        ¡Se acabó el tiempo!       |");
            System.out.println("|      Prepárate para morir!!!      |");
            System.out.println("|-----------------------------------|");
        }
    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
 

    private static void pressEnter(Scanner input) {
        System.out.print("Presiona ENTER para continuar");
        input.nextLine();
    }
}
