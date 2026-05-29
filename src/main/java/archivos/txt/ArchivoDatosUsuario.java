package archivos.txt;

import com.example.prueba.DataStore;
import com.example.prueba.Moto;
import com.example.prueba.PreferenciasUsuario;
import com.example.prueba.Users;
import estructuras.ListaDobleEnlazada;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ArchivoDatosUsuario {

    private static final Path CARPETA_USUARIOS =
            Path.of("data", "usuarios");

    private static final String CARRITO = "carrito.txt";
    private static final String FAVORITOS = "favoritos.txt";
    private static final String HISTORIAL = "historial.txt";
    private static final String PREFERENCIAS = "preferencias.txt";
    private static final String COMPRAS = "compras.txt";

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ArchivoDatosUsuario() {
    }

    public static void inicializarDatos(Users usuario) {
        try {
            Path carpeta = carpetaUsuario(usuario);

            Files.createDirectories(carpeta);
            crearArchivoSiNoExiste(carpeta.resolve(CARRITO));
            crearArchivoSiNoExiste(carpeta.resolve(FAVORITOS));
            crearArchivoSiNoExiste(carpeta.resolve(HISTORIAL));
            crearArchivoSiNoExiste(carpeta.resolve(PREFERENCIAS));
            crearArchivoSiNoExiste(carpeta.resolve(COMPRAS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos(Users usuario) {
        inicializarDatos(usuario);

        cargarMotosEnLista(
                archivoUsuario(usuario, CARRITO),
                DataStore.carrito,
                true
        );

        cargarMotosEnLista(
                archivoUsuario(usuario, FAVORITOS),
                DataStore.favoritos,
                false
        );

        cargarHistorial(usuario);
        DataStore.preferencias = cargarPreferencias(usuario);
    }

    public static void guardarDatos(Users usuario) {
        guardarCarrito(usuario);
        guardarFavoritos(usuario);
        guardarHistorial(usuario);
        guardarPreferencias(usuario);
    }

    public static void eliminarMotoDeTodosLosUsuarios(Moto moto) {
        if (moto == null || moto.getNombre() == null) {
            return;
        }

        try {
            Files.createDirectories(CARPETA_USUARIOS);

            try (DirectoryStream<Path> carpetas =
                         Files.newDirectoryStream(CARPETA_USUARIOS)) {

                for (Path carpeta : carpetas) {
                    if (!Files.isDirectory(carpeta)) {
                        continue;
                    }

                    eliminarNombreDeArchivo(
                            carpeta.resolve(CARRITO),
                            moto.getNombre()
                    );
                    eliminarNombreDeArchivo(
                            carpeta.resolve(FAVORITOS),
                            moto.getNombre()
                    );
                    eliminarNombreDeArchivo(
                            carpeta.resolve(HISTORIAL),
                            moto.getNombre()
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarCarrito(Users usuario) {
        guardarNombres(
                archivoUsuario(usuario, CARRITO),
                DataStore.carrito
        );
    }

    public static void guardarFavoritos(Users usuario) {
        guardarNombres(
                archivoUsuario(usuario, FAVORITOS),
                DataStore.favoritos
        );
    }

    public static void guardarHistorial(Users usuario) {
        guardarNombres(
                archivoUsuario(usuario, HISTORIAL),
                DataStore.historial.toList()
        );
    }

    public static void guardarPreferencias(Users usuario) {
        inicializarDatos(usuario);

        Path archivo = archivoUsuario(usuario, PREFERENCIAS);
        PreferenciasUsuario preferencias = DataStore.preferencias;

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             archivo,
                             StandardCharsets.UTF_8
                     )) {

            writer.write("tema=" + limpiarValor(preferencias.getTema()));
            writer.newLine();
            writer.write("busqueda=" + limpiarValor(preferencias.getBusqueda()));
            writer.newLine();
            writer.write("filtroPrecio=" + limpiarValor(preferencias.getFiltroPrecio()));
            writer.newLine();
            writer.write("filtroCc=" + limpiarValor(preferencias.getFiltroCc()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registrarCompra(
            Users usuario,
            Iterable<Moto> motosCompradas,
            double total
    ) {
        inicializarDatos(usuario);

        List<String> lineas = new ArrayList<>();
        int cantidad = 0;

        lineas.add(
                "COMPRA|fecha=" + LocalDateTime.now().format(FORMATO_FECHA)
                        + "|total=" + total
        );

        for (Moto moto : motosCompradas) {
            cantidad++;
            lineas.add(
                    "ITEM|nombre=" + limpiarRegistro(moto.getNombre())
                            + "|marca=" + limpiarRegistro(moto.getMarca())
                            + "|cc=" + moto.getCilindraje()
                            + "|precio=" + moto.getPrecio()
            );
        }

        if (cantidad == 0) {
            return;
        }

        lineas.add("FIN|cantidad=" + cantidad);

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             archivoUsuario(usuario, COMPRAS),
                             StandardCharsets.UTF_8,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.APPEND
                     )) {

            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarMotosEnLista(
            Path archivo,
            ListaDobleEnlazada<Moto> destino,
            boolean permitirRepetidos
    ) {
        destino.clear();

        for (String nombre : leerLineas(archivo)) {
            Moto moto = DataStore.buscarMotoPorNombre(nombre);

            if (moto == null) {
                continue;
            }

            if (!permitirRepetidos && destino.anyMatch(
                    item -> item.getNombre().equalsIgnoreCase(moto.getNombre())
            )) {
                continue;
            }

            destino.add(moto);
        }
    }

    private static void cargarHistorial(Users usuario) {
        DataStore.historial.clear();

        for (String nombre : leerLineas(archivoUsuario(usuario, HISTORIAL))) {
            Moto moto = DataStore.buscarMotoPorNombre(nombre);

            if (moto != null) {
                DataStore.historial.push(moto);
            }
        }
    }

    private static PreferenciasUsuario cargarPreferencias(Users usuario) {
        PreferenciasUsuario preferencias = new PreferenciasUsuario();
        Path archivo = archivoUsuario(usuario, PREFERENCIAS);

        try (BufferedReader reader =
                     Files.newBufferedReader(
                             archivo,
                             StandardCharsets.UTF_8
                     )) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                int separador = linea.indexOf('=');

                if (separador < 0) {
                    continue;
                }

                String clave = linea.substring(0, separador).trim();
                String valor = linea.substring(separador + 1).trim();

                aplicarPreferencia(preferencias, clave, valor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return preferencias;
    }

    private static void aplicarPreferencia(
            PreferenciasUsuario preferencias,
            String clave,
            String valor
    ) {
        switch (clave) {
            case "tema" -> preferencias.setTema(valor);
            case "busqueda" -> preferencias.setBusqueda(valor);
            case "filtroPrecio" -> preferencias.setFiltroPrecio(valor);
            case "filtroCc" -> preferencias.setFiltroCc(valor);
            default -> {
            }
        }
    }

    private static void guardarNombres(
            Path archivo,
            Iterable<Moto> motos
    ) {
        inicializarDatosPorArchivo(archivo);

        List<String> lineas = new ArrayList<>();

        for (Moto moto : motos) {
            lineas.add(moto.getNombre());
        }

        try {
            Files.write(
                    archivo,
                    lineas,
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> leerLineas(Path archivo) {
        inicializarDatosPorArchivo(archivo);

        List<String> lineasLimpias = new ArrayList<>();

        try {
            for (String linea : Files.readAllLines(
                    archivo,
                    StandardCharsets.UTF_8
            )) {
                String limpia = linea.trim();

                if (!limpia.isEmpty()) {
                    lineasLimpias.add(limpia);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineasLimpias;
    }

    private static void eliminarNombreDeArchivo(
            Path archivo,
            String nombre
    ) throws IOException {

        inicializarDatosPorArchivo(archivo);

        List<String> lineas =
                Files.readAllLines(
                        archivo,
                        StandardCharsets.UTF_8
                );

        List<String> filtradas =
                new ArrayList<>();

        for (String linea : lineas) {
            if (!linea.trim().equalsIgnoreCase(nombre)) {
                filtradas.add(linea);
            }
        }

        if (!lineas.equals(filtradas)) {
            Files.write(
                    archivo,
                    filtradas,
                    StandardCharsets.UTF_8
            );
        }
    }

    private static Path archivoUsuario(
            Users usuario,
            String archivo
    ) {
        return carpetaUsuario(usuario).resolve(archivo);
    }

    private static Path carpetaUsuario(Users usuario) {
        String correo = usuario.getCorreo().toLowerCase();
        String carpetaSegura =
                Base64.getUrlEncoder()
                        .withoutPadding()
                        .encodeToString(correo.getBytes(StandardCharsets.UTF_8));

        return CARPETA_USUARIOS.resolve(carpetaSegura);
    }

    private static void inicializarDatosPorArchivo(Path archivo) {
        try {
            Files.createDirectories(archivo.getParent());
            crearArchivoSiNoExiste(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void crearArchivoSiNoExiste(Path archivo)
            throws IOException {

        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
        }
    }

    private static String limpiarValor(String valor) {
        if (valor == null) {
            return "";
        }

        return valor.replace("\n", " ").replace("\r", " ").trim();
    }

    private static String limpiarRegistro(String valor) {
        return limpiarValor(valor).replace("|", "/");
    }
}
