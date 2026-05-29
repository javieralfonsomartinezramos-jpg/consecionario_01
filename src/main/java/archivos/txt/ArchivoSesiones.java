package archivos.txt;

import com.example.prueba.Users;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArchivoSesiones {

    private static final Path ARCHIVO =
            Path.of("data", "sesiones.txt");

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ArchivoSesiones() {
    }

    public static void registrarInicio(
            Users usuario,
            boolean admin
    ) {

        registrarEvento("LOGIN", usuario, admin);
    }

    public static void registrarCierre(
            Users usuario,
            boolean admin
    ) {

        registrarEvento("LOGOUT", usuario, admin);
    }

    private static void registrarEvento(
            String evento,
            Users usuario,
            boolean admin
    ) {
        if (usuario == null) {
            return;
        }

        crearArchivoSiHaceFalta();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             ARCHIVO,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.APPEND
                     )) {

            writer.write(
                    evento
                            + "|fecha=" + LocalDateTime.now().format(FORMATO_FECHA)
                            + "|tipo=" + (admin ? "admin" : "usuario")
                            + "|nombre=" + limpiar(usuario.getNombre())
                            + "|correo=" + limpiar(usuario.getCorreo())
            );
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void crearArchivoSiHaceFalta() {
        try {
            Files.createDirectories(ARCHIVO.getParent());

            if (!Files.exists(ARCHIVO)) {
                Files.createFile(ARCHIVO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String limpiar(String valor) {
        if (valor == null) {
            return "";
        }

        return valor
                .replace("|", "/")
                .replace("\n", " ")
                .replace("\r", " ")
                .trim();
    }
}
