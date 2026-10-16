package smartweather;

public class EstacionMeteorologica {

    private double temperatura;
    private double humedad;
    private double presion;

    private final GestorNotificaciones gestor;

    public EstacionMeteorologica() {
        gestor = new GestorNotificaciones();
    }

    public void actualizarMediciones(double temperatura, double humedad, double presion) {

        if (humedad < 0 || humedad > 100) {
            throw new IllegalArgumentException(
                    "La humedad debe estar entre 0 y 100 %."
            );
        }

        if (presion <= 0) {
            throw new IllegalArgumentException(
                    "La presión debe ser mayor que cero."
            );
        }

        this.temperatura = temperatura;
        this.humedad = humedad;
        this.presion = presion;

        LecturaClimatica lectura = new LecturaClimatica(this.temperatura, this.humedad, this.presion);

        System.out.println("\n=== NUEVA MEDICIÓN METEOROLÓGICA ===");

        gestor.publicar(lectura);
    }

    public GestorNotificaciones getGestor() {
        return gestor;
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
}