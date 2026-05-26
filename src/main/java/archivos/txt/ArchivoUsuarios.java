package archivos.txt;

import com.example.prueba.Users;

import java.io.*;

public class ArchivoUsuarios {

    private static final String ARCHIVO =
            "data/user.txt";

    // GUARDAR USUARIO
    public static void guardarUsuario(
            Users users
    ) {

        try (FileWriter writer =
                     new FileWriter(
                             ARCHIVO,
                             true
                     )) {

            writer.write(
                    users.toFileString()
                            + "\n"
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // VALIDAR USUARIO
    public static Users validarUsuario(
            String correo,
            String clave
    ) {

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(
                                     ARCHIVO
                             ))) {

            String linea;

            while ((linea =
                    br.readLine()) != null) {

                String[] datos =
                        linea.split(",");

                String nombre =
                        datos[0];

                String correoGuardado =
                        datos[1];

                String claveGuardada =
                        datos[2];

                if (correoGuardado.equals(correo)
                        &&
                        claveGuardada.equals(clave)) {

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
}