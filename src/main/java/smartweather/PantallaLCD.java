package smartweather;

public class PantallaLCD {

    public void mostrarLectura(LecturaClimatica lectura) {
        System.out.println("\n[PANTALLA LCD]");
        System.out.println("------------------------------");
        System.out.println(lectura);
        System.out.println("Actualizacion: "
                + lectura.getFechaFormateada());
        System.out.println("------------------------------");
    }
}