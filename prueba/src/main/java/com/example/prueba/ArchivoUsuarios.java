package com.example.prueba;

import java.io.*;

public class ArchivoUsuarios {

    private static final String ARCHIVO = "data/user.txt";

    public static void guardarUsuario(Users users) {
        try (FileWriter writer =
                     new FileWriter(ARCHIVO, true)) {

            writer.write(users.toFileString() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validarUsuario(String correo,
                                         String clave) {

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(ARCHIVO))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos[1].equals(correo)
                        && datos[2].equals(clave)) {
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}