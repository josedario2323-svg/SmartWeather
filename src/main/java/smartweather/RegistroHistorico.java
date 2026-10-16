
package smartweather;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RegistroHistorico {

    private final Path archivo;

    public RegistroHistorico() {
        archivo = Path.of("registros","historial_climatico.txt").toAbsolutePath();
        System.out.println("Archivo histórico: " + archivo);

        try {
            Files.createDirectories(archivo.getParent());

            if (Files.notExists(archivo)) {
                Files.createFile(archivo);
            }

            System.out.println("Archivo histórico: " + archivo);

        } catch (IOException e) {
            throw new IllegalStateException("No se pudo crear el archivo histórico.", e);
        }
    }

    public synchronized void registrarLectura(LecturaClimatica lectura) {

        String linea = String.format(
                "[%s] %s%n",
                lectura.getFechaFormateada(),
                lectura
        );

        try {
            Files.writeString(
                    archivo,
                    linea,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            System.out.println("[REGISTRO HISTÓRICO] Lectura guardada.");

        } catch (IOException e) {
            System.err.println("Error al guardar la lectura: "+ e.getMessage());
        }
    }
}
