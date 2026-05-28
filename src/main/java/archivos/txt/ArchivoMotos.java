package archivos.txt;

import com.example.prueba.Moto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoMotos {

    private static final String CARPETA = "data";
    private static final String ARCHIVO = "data/motos.txt";

    public static void guardarMoto(Moto moto, String imagen) {
        if (imagen != null && !imagen.trim().isEmpty()) {
            moto.setImagen(imagen.trim());
        }

        guardarMoto(moto);
    }

    public static void guardarMoto(Moto moto) {
        crearCarpetaData();

        try (FileWriter writer = new FileWriter(ARCHIVO, true)) {
            writer.write(convertirMotoALinea(moto) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Moto> cargarMotos() {
        ArrayList<Moto> motos = new ArrayList<>();
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return motos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", -1);

                if (datos.length != 6) {
                    continue;
                }

                String nombre = datos[0].trim();
                String marca = datos[1].trim();
                int cilindraje = Integer.parseInt(datos[2].trim());
                double precio = Double.parseDouble(datos[3].trim());
                int stock = Integer.parseInt(datos[4].trim());
                String imagen = datos[5].trim();

                motos.add(new Moto(
                        nombre,
                        marca,
                        cilindraje,
                        precio,
                        stock,
                        imagen
                ));
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return motos;
    }

    public static void guardarTodasLasMotos(List<Moto> motos) {
        crearCarpetaData();

        try (FileWriter writer = new FileWriter(ARCHIVO, false)) {
            for (Moto moto : motos) {
                writer.write(convertirMotoALinea(moto) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarMotosInicialesSiHaceFalta(List<Moto> motosIniciales) {
        File archivo = new File(ARCHIVO);

        if (archivo.exists() && archivo.length() > 0) {
            return;
        }

        guardarTodasLasMotos(motosIniciales);
    }

    private static String convertirMotoALinea(Moto moto) {
        return moto.getNombre() + "," +
                moto.getMarca() + "," +
                moto.getCilindraje() + "," +
                moto.getPrecio() + "," +
                moto.getStock() + "," +
                moto.getImagen();
    }

    private static void crearCarpetaData() {
        File carpeta = new File(CARPETA);

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }
}
