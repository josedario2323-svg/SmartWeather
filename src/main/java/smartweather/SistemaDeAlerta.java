package smartweather;

public class SistemaDeAlerta {

    public void comprobarCondiciones(LecturaClimatica lectura) {
        double temperatura = lectura.getTemperatura();
        double humedad = lectura.getHumedad();

        System.out.println("\n[SISTEMA DE ALERTA]");

        boolean hayAlerta = false;

        if (temperatura > 35) {
            System.out.println("¡ALERTA! Temperatura extremadamente alta: "+ temperatura + " °C");
            hayAlerta = true;
        }

        if (temperatura < 0) {
            System.out.println("¡ALERTA! Temperatura bajo cero: "+ temperatura + " °C");
            hayAlerta = true;
        }

        if (humedad >= 90) {
            System.out.println("¡ALERTA! Humedad extremadamente alta: "+ humedad + " %");
            hayAlerta = true;
        }

        if (humedad <= 10) {
            System.out.println("¡ALERTA! Humedad extremadamente baja: "+ humedad + " %");
            hayAlerta = true;
        }

        if (!hayAlerta) {
            System.out.println("Condiciones meteorológicas normales.");
        }
    }
}