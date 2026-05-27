package archivos.txt;

import com.example.prueba.Moto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoMotos {

    private static final String ARCHIVO =
            "data/motos.txt";

    public static void guardarMoto(
            Moto moto,
            String imagen
    ) {

        try {

            File carpeta =
                    new File("data");

            if (!carpeta.exists()) {
                carpeta.mkdir();
            }

            FileWriter writer =
                    new FileWriter(
                            ARCHIVO,
                            true
                    );

            writer.write(
                    moto.getNombre() + "," +
                            moto.getMarca() + "," +
                            moto.getCilindraje() + "," +
                            moto.getPrecio() + "," +
                            moto.getStock() + "," +
                            imagen + "\n"
            );

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
