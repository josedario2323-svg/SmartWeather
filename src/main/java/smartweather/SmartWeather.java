
package smartweather;

import java.util.Random;
import java.util.UUID;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartWeather {

    public static void main(String[] args) {

        EstacionMeteorologica estacion = new EstacionMeteorologica();

        GestorNotificaciones gestor = estacion.getGestor();

        PantallaLCD pantalla = new PantallaLCD();
        SistemaDeAlerta alertas = new SistemaDeAlerta();
        RegistroHistorico registro = new RegistroHistorico();

        // Conectar los dispositivos
        UUID idPantalla = gestor.suscribir(pantalla::mostrarLectura);

        UUID idAlertas = gestor.suscribir(alertas::comprobarCondiciones);

        UUID idRegistro = gestor.suscribir(registro::registrarLectura);

        System.out.println(
                "\nSmartWeather está funcionando."
        );

        System.out.println("Se generará una medición cada 5 segundos.");

        System.out.println("Presiona ENTER para detener el programa.\n");

        Random random = new Random();

        ScheduledExecutorService programador = Executors.newSingleThreadScheduledExecutor();

        // Simular mediciones periódicamente
        programador.scheduleAtFixedRate(() -> {

            try {
                double temperatura =
                        -5 + random.nextDouble() * 45;

                double humedad =
                        5 + random.nextDouble() * 95;

                double presion =
                        980 + random.nextDouble() * 55;

                estacion.actualizarMediciones(
                        temperatura,
                        humedad,
                        presion
                );

            } catch (Exception e) {
                System.err.println("Error en la medición: "+ e.getMessage());
            }

        }, 0, 5, TimeUnit.SECONDS);

        // Mantener el programa activo hasta presionar ENTER
        try (Scanner teclado = new Scanner(System.in)) {
            teclado.nextLine();
        }

        // Detener las mediciones y desconectar dispositivos
        programador.shutdownNow();

        gestor.desuscribir(idPantalla);
        gestor.desuscribir(idAlertas);
        gestor.desuscribir(idRegistro);

        try {
            if (!programador.awaitTermination(3, TimeUnit.SECONDS)) {
                System.err.println("El programador no se detuvo a tiempo.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nSmartWeather se ha detenido.");
    }
}
