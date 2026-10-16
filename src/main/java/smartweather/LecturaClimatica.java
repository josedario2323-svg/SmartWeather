package smartweather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LecturaClimatica {

    private final double temperatura;
    private final double humedad;
    private final double presion;
    private final LocalDateTime fecha;

    public LecturaClimatica(double temperatura, double humedad, double presion) {

        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;
        this.fecha = LocalDateTime.now();
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public double getPresion() {
        return presion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getFechaFormateada() {
        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return fecha.format(formato);
    }

    @Override
    public String toString() {
        return String.format(
                "Temperatura: %.1f °C | Humedad: %.1f %% | Presion: %.1f hPa",
                temperatura, humedad, presion
        );
    }
}