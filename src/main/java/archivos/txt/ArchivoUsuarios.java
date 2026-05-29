package archivos.txt;

import com.example.prueba.Users;
import com.example.prueba.Session;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArchivoUsuarios {

    private static final Path ARCHIVO =
            Path.of("data", "user.txt");

    private ArchivoUsuarios() {
    }

    public static boolean guardarUsuario(
            Users users
    ) {
        prepararArchivoUsuarios();

        if (users == null
                || esDatoReservado(users.getNombre(), users.getCorreo())
                || existeCorreo(users.getCorreo())) {
            return false;
        }

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             ARCHIVO,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.APPEND
                     )) {

            writer.write(
                    users.toFileString()
            );
            writer.newLine();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Users validarUsuario(
            String correo,
            String clave
    ) {
        prepararArchivoUsuarios();

        if (Session.esCorreoAdmin(correo)) {
            return null;
        }

        try (BufferedReader br =
                     Files.newBufferedReader(
                             ARCHIVO,
                             StandardCharsets.UTF_8
                     )) {

            String linea;

            while ((linea =
                    br.readLine()) != null) {

                String[] datos =
                        linea.split(",", -1);

                if (datos.length < 3) {
                    continue;
                }

                String nombre =
                        datos[0];

                String correoGuardado =
                        datos[1];

                String claveGuardada =
                        datos[2];

                if (esDatoReservado(nombre, correoGuardado)) {
                    continue;
                }

                if (correoGuardado.equalsIgnoreCase(correo)
                        && claveGuardada.equals(clave)) {

                    return new Users(
                            nombre,
                            correoGuardado,
                            claveGuardada
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean existeCorreo(String correo) {
        prepararArchivoUsuarios();

        if (Session.esCorreoAdmin(correo)) {
            return true;
        }

        try (BufferedReader br =
                     Files.newBufferedReader(
                             ARCHIVO,
                             StandardCharsets.UTF_8
                     )) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", -1);

                if (datos.length >= 2
                        && datos[1].equalsIgnoreCase(correo)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean esDatoReservado(
            String nombre,
            String correo
    ) {

        return Session.esIdentidadAdminReservada(nombre, correo);
    }

    private static void prepararArchivoUsuarios() {
        try {
            Files.createDirectories(ARCHIVO.getParent());

            if (!Files.exists(ARCHIVO)) {
                Files.createFile(ARCHIVO);
            }

            normalizarArchivoUsuarios();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void normalizarArchivoUsuarios()
            throws IOException {

        List<String> lineasOriginales =
                Files.readAllLines(
                        ARCHIVO,
                        StandardCharsets.UTF_8
                );

        List<String> lineasValidas =
                new ArrayList<>();

        Set<String> correosRegistrados =
                new HashSet<>();

        for (String linea : lineasOriginales) {
            String[] datos = linea.split(",", -1);

            if (datos.length < 3) {
                continue;
            }

            String nombre = datos[0].trim();
            String correo = datos[1].trim();
            String clave = datos[2].trim();
            String correoNormalizado = correo.toLowerCase();

            if (nombre.isEmpty()
                    || correo.isEmpty()
                    || clave.isEmpty()
                    || esDatoReservado(nombre, correo)
                    || correosRegistrados.contains(correoNormalizado)) {
                continue;
            }

            correosRegistrados.add(correoNormalizado);
            lineasValidas.add(
                    new Users(nombre, correo, clave).toFileString()
            );
        }

        if (!lineasOriginales.equals(lineasValidas)) {
            escribirSeguro(lineasValidas);
        }
    }

    private static void escribirSeguro(List<String> lineas)
            throws IOException {

        Path temporal =
                Files.createTempFile(
                        ARCHIVO.getParent(),
                        "usuarios-",
                        ".tmp"
                );

        Files.write(
                temporal,
                lineas,
                StandardCharsets.UTF_8,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

        try {
            Files.move(
                    temporal,
                    ARCHIVO,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE
            );
        } catch (IOException e) {
            Files.move(
                    temporal,
                    ARCHIVO,
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
    }
}
