package bean;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import static utility.InputPane.GetNum;

public class ListNumeros {
    private ArrayList<ArrayList<Integer>> ListaNumeros;
    public String Name;

    public ListNumeros(String name) {
        ListaNumeros = new ArrayList<>();
        this.Name = name;
    }

    public void Del(){
        int n = GetNum("Eliminar de las listas");
        int count = 0;
        for (ArrayList<Integer> fila : ListaNumeros) {
            if (fila.contains(n)) {
                fila.removeIf(num -> num.equals(n));
                count++;
            }
        }

        if (count > 0) {
            JOptionPane.showMessageDialog(null, "Se eliminaron " + count + " ocurrencias del número " + n + " en la lista.",
                    "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna ocurrencia del número " + n + " en la lista.",
                    "Número no Encontrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void EmptyDel(){
        ListaNumeros.removeIf(ArrayList::isEmpty);
    }

    public void AllSize(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-15s %s\n", "Número de ArrayList", "Tamaño"));
        for (int i = 0; i < ListaNumeros.size(); i++) {
            sb.append(String.format("%-20d %d\n", i + 1, ListaNumeros.get(i).size()));
        }

        JOptionPane.showMessageDialog(null, "<html><pre>" + sb.toString() + "</pre></html>",
                "Información de ArrayLists", JOptionPane.PLAIN_MESSAGE);
    }

    public void Prom(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-10s %-10s\n", "Fila", "Suma", "Promedio"));

        for (int fila = 0; fila < ListaNumeros.size(); fila++) {
            ArrayList<Integer> filaDatos = ListaNumeros.get(fila);
            int suma = 0;
            for (int num : filaDatos) {
                suma += num;
            }
            double promedio = (double) suma / filaDatos.size();
            sb.append(String.format("%-10d %-10d %-10.2f\n", fila + 1, suma, promedio));
        }

        JOptionPane.showMessageDialog(null, "<html><pre>" + sb.toString() + "</pre></html>",
                "Suma y Promedio por Fila", JOptionPane.PLAIN_MESSAGE);
    }

    public void AddArrayList() {
        int NumFila = GetNum("indicar el arraylist a adicionar un elemento");
        ArrayList<Integer> Elems = new ArrayList<>();
        int NumElem = GetNum("indicar cuantos numeros va a ingresar");
        for (int i=0; i<NumElem; i++){
            Elems.add(GetNum("adicionar el elemento#"+(i+1)));
        }
        if (NumFila <= ListaNumeros.size() && NumFila > 0) {
            ArrayList<Integer> fila = ListaNumeros.get(NumFila - 1);
            for (Integer elemento : Elems) {
                fila.add(elemento);
            }
            JOptionPane.showMessageDialog(null, "Elementos agregados correctamente a la fila " + NumFila,
                    "Agregación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "El número de fila especificado no existe",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Show(){
        StringBuilder sb = new StringBuilder();

        /*Encabezado*/
        int maxCol = 0;
        for (ArrayList<Integer> fila : ListaNumeros) {
            if (fila.size() > maxCol) {
                maxCol = fila.size();
            }
        }

        sb.append(String.format("%10s", ""));
        for (int col = 0; col < maxCol; col++) {
            sb.append(String.format("%10s", "Col#"+(col + 1)));
        }
        sb.append("\n");

        for (int fila = 0; fila < ListaNumeros.size(); fila++) {
            sb.append(String.format("%10s","fil#" + (fila + 1)));
            for (int col = 0; col < ListaNumeros.get(fila).size(); col++) {
                sb.append(String.format("%10s", ListaNumeros.get(fila).get(col)));
            }
            sb.append("\n");
        }

        JOptionPane.showMessageDialog(null, "<html><pre>" + sb.toString() + "</pre></html>",
                "Datos Numerados", JOptionPane.PLAIN_MESSAGE);
    }

    public void ReadData() {
        ListaNumeros.clear();
        String root = System.getProperty("user.dir");
        try {
            FileReader File = new FileReader(root + "\\src\\data\\Numeros.txt");
            BufferedReader Br = new BufferedReader(File);

            String line = Br.readLine();
            while (line != null) {
                ArrayList<Integer> fila = new ArrayList<>();
                String[] parts = line.split(" ");
                for (String part : parts) {
                    try {
                        int num = Integer.parseInt(part);
                        fila.add(num);
                    } catch (NumberFormatException e) {
                        System.out.println("El elemento '" + part + "' no es un número válido.");
                        JOptionPane.showMessageDialog(null, "El elemento "+part +" no es un número válido.", "Validación", JOptionPane.ERROR_MESSAGE);
                    }
                }
                ListaNumeros.add(fila);
                line = Br.readLine();
            }
            Br.close();
        } catch (IOException e) {
            System.out.print("Hubo un error: " + e);
        }
    }

    public void WriteData() {
        String root = System.getProperty("user.dir");
        try {
            FileWriter fileWriter = new FileWriter(root + "\\src\\data\\Numeros.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (ArrayList<Integer> fila : ListaNumeros) {
                for (int num : fila) {
                    bufferedWriter.write(num + " ");
                }
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            System.out.print("Hubo un error: " + e);
        }
    }
}
