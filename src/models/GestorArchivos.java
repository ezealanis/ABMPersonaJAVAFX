package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorArchivos {

    private static final String ARCHIVO_TEXTO = "personas.txt";
    private static final String ARCHIVO_BINARIO = "personas.dat";

    public static void guardarCSV(List<Persona> lista) {

        BufferedWriter bw = null;

        try {
            FileWriter fw = new FileWriter(ARCHIVO_TEXTO);
            bw = new BufferedWriter(fw);

            for (Persona p : lista) {
                bw.write(p.toCSV());
                bw.newLine();
                bw.flush();
            }
            //liberar buffer y pasar al disco duro, o sea borra del buffer y lo pasa a la memoria
            bw.close();

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static List<Persona> leerCSV() {

        List<Persona> lista = new ArrayList<>();
        BufferedReader br = null;

        try {

            FileReader fr = new FileReader(ARCHIVO_TEXTO);
            br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");
                Persona aux = new Persona(datos[0], datos[1], Integer.parseInt(datos[2]), Genero.valueOf(datos[3]));

                lista.add(aux);
            }

            br.close();

        } catch (IOException e) {
            System.out.println(e);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error al convertir un dato");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        return lista;
    }

    public static void serializarLista(List<Persona> lista) throws IOException {

        ObjectOutputStream oos = null;

        try {
            FileOutputStream fos = new FileOutputStream(GestorArchivos.ARCHIVO_BINARIO);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(lista);

        } catch (IOException e) {

            System.out.println("Error al serializar: " + e.getMessage());
        } finally {
            try {
                if (oos != null) {
                    System.out.println("Lista serializada!");
                    oos.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    public static List<Persona> deserializar() {

        List<Persona> lista = new ArrayList<>();
        ObjectInputStream ois = null;

        try {
            FileInputStream fis = new FileInputStream(GestorArchivos.ARCHIVO_BINARIO);
            ois = new ObjectInputStream(fis);

            lista = (List<Persona>) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar: " + e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return lista;
    }

}
