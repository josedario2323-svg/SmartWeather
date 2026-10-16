package smartweather;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class GestorNotificaciones {

    private final Map<UUID, Consumer<LecturaClimatica>> suscriptores
            = new ConcurrentHashMap<>();

    public UUID suscribir(Consumer<LecturaClimatica> dispositivo) {
        UUID id = UUID.randomUUID();

        suscriptores.put(id, dispositivo);

        System.out.println("Dispositivo conectado: " + id);

        return id;
    }

    public void desuscribir(UUID id) {
        Consumer<LecturaClimatica> eliminado = suscriptores.remove(id);

        if (eliminado != null) {
            System.out.println("Dispositivo desconectado: " + id);
        } else {
            System.out.println("No existe esa suscripción.");
        }
    }

    public void publicar(LecturaClimatica lectura) {
        for (Consumer<LecturaClimatica> dispositivo: suscriptores.values()) {
            dispositivo.accept(lectura);
        }
    }

    public int cantidadSuscriptores() {
        return suscriptores.size();
    }
}