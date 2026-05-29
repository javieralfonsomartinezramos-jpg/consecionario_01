package archivos.txt;

import com.example.prueba.Moto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ArchivoMotos {

    private static final Path CARPETA =
            Path.of("data");

    private static final Path ARCHIVO =
            CARPETA.resolve("Motos.txt");

    private static final Path ARCHIVO_COMPATIBLE =
            CARPETA.resolve("motos.txt");

    private ArchivoMotos() {
    }

    public static void guardarMoto(Moto moto, String imagen) {
        if (imagen != null && !imagen.trim().isEmpty()) {
            moto.setImagen(imagen.trim());
        }

        guardarMoto(moto);
    }

    public static void guardarMoto(Moto moto) {
        inicializarArchivo();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             archivoActual(),
                             StandardCharsets.UTF_8,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.APPEND
                     )) {

            writer.write(convertirMotoALinea(moto));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Moto> cargarMotos() {
        inicializarArchivo();

        ArrayList<Moto> motos = new ArrayList<>();

        try (BufferedReader br =
                     Files.newBufferedReader(
                             archivoActual(),
                             StandardCharsets.UTF_8
                     )) {

            String linea;

            while ((linea = br.readLine()) != null) {
                Moto moto = convertirLineaAMoto(linea);

                if (moto != null) {
                    motos.add(moto);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return motos;
    }

    public static void guardarTodasLasMotos(Iterable<Moto> motos) {
        inicializarArchivo();

        List<String> lineas = new ArrayList<>();

        for (Moto moto : motos) {
            lineas.add(convertirMotoALinea(moto));
        }

        escribirSeguro(lineas);
    }

    public static void guardarMotosInicialesSiHaceFalta(
            List<Moto> motosIniciales
    ) {
        inicializarArchivo();

        try {
            if (Files.size(archivoActual()) > 0) {
                return;
            }

            guardarTodasLasMotos(motosIniciales);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Moto convertirLineaAMoto(String linea) {
        if (linea == null || linea.trim().isEmpty()) {
            return null;
        }

        String[] datos = linea.split(",", -1);

        if (datos.length != 6) {
            return null;
        }

        try {
            String nombre = datos[0].trim();
            String marca = datos[1].trim();
            int cilindraje = Integer.parseInt(datos[2].trim());
            double precio = Double.parseDouble(datos[3].trim());
            int stock = Integer.parseInt(datos[4].trim());
            String imagen = datos[5].trim();

            if (nombre.isEmpty()
                    || marca.isEmpty()
                    || imagen.isEmpty()
                    || cilindraje <= 0
                    || precio <= 0
                    || stock < 0) {
                return null;
            }

            return new Moto(
                    nombre,
                    marca,
                    cilindraje,
                    precio,
                    stock,
                    imagen
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static String convertirMotoALinea(Moto moto) {
        return moto.getNombre() + ","
                + moto.getMarca() + ","
                + moto.getCilindraje() + ","
                + moto.getPrecio() + ","
                + moto.getStock() + ","
                + moto.getImagen();
    }

    private static void inicializarArchivo() {
        try {
            Files.createDirectories(CARPETA);

            if (!Files.exists(archivoActual())) {
                Files.createFile(ARCHIVO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path archivoActual() {
        if (Files.exists(ARCHIVO)) {
            return ARCHIVO;
        }

        if (Files.exists(ARCHIVO_COMPATIBLE)) {
            return ARCHIVO_COMPATIBLE;
        }

        return ARCHIVO;
    }

    private static void escribirSeguro(List<String> lineas) {
        try {
            Path destino = archivoActual();
            Path temporal =
                    Files.createTempFile(
                            CARPETA,
                            "motos-",
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
                        destino,
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.ATOMIC_MOVE
                );
            } catch (IOException e) {
                Files.move(
                        temporal,
                        destino,
                        StandardCopyOption.REPLACE_EXISTING
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
