package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    File f;

    public FileManager(String path) throws IOException {
        f = new File(path);
    }

    public String readFile() {
        Scanner s = null;
        String output = "";
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                output += linea + "-";
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (s != null) s.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return output;
    }

    public ArrayList<String> readFileArrayList() {
        Scanner s = null;
        ArrayList<String> lineas = new ArrayList<>();
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                lineas.add(s.nextLine());

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally { // intentar cerrar el scanner
            try {
                if (s != null) s.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lineas;
    }

    public void addLine(String linea) {
        FileWriter fw = null;
        try { // intentar escribir en el archivo
            fw = new FileWriter(f.getAbsoluteFile(), true);
            fw.write(linea);
        } catch (IOException e) {
            System.out.println("Excepcion.");
            e.printStackTrace();
        } finally { // intentar cerrar el fileWriter
            try {
                if (fw != null) fw.close();
                System.out.println("Se ha Guardado \"" + linea + "\" Correctamente!" );
            } catch (IOException e2) {
                System.out.println("E2");
                e2.printStackTrace();
            }
        }
    }


    public static ArrayList<String> linesToArrayList(String noFormated) {
        // Separar lineas y crear ArrayList de retorno
        String[] lineas = noFormated.split("-");
        ArrayList<String> output = new ArrayList<>();

        // Adicionar lineas al ArrayList de retorno
        for (String l : lineas) output.add(l);
        return output;
    }


}
