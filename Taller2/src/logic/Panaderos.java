package logic;

import bean.File;
import bean.Panadero;
import bean.Vendedor;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static utility.InputPane.GetAge;
import static utility.InputPane.GetDato;
import static utility.InputPane.GetNum;

public class Panaderos implements File {
    private ArrayList<Panadero> ListaPanaderos;
    public String Name;

    public Panaderos(String Name){
        ListaPanaderos = new ArrayList<Panadero>();
        this.Name = Name;
    }

    public void Input(){
        int cedula = GetNum("la cedula para el Panadero");
        if(!isExist(cedula)){
            String nombres = GetDato("nombre","para el Panadero");
            String apellidos = GetDato("apellido","para el Panadero");
            int anosExperiencia = GetNum("los años de experiencia del Panadero");
            int edad = GetAge("la edad para el Panadero");

            ListaPanaderos.add(new Panadero(cedula, nombres, apellidos, anosExperiencia, edad));
            System.out.println("El Panadero con la cedula "+cedula+" ha sido agregado");
            JOptionPane.showMessageDialog(null, "El Panadero con la cedula "+cedula+" ha sido agregado");
        }else{
            System.out.println("El Panadero con la cedula "+cedula+" ya existe");
            JOptionPane.showMessageDialog(null, "El Panadero con la cedula "+cedula+" ya existe");
        }
        ShowConsolePan(cedula); ShowPanePan(cedula);
    }

    public void SearchPan(){
        int cedula = GetNum("la cedula para buscar el Panadero");
        if(isExist(cedula)){
            ShowConsolePan(cedula); ShowPanePan(cedula);
        }else{
            System.out.println("El Panadero con la cedula "+cedula+" no existe");
            JOptionPane.showMessageDialog(null, "El Panadero con la cedula "+cedula+" no existe");
        }
    }

    public void PerAnosExp(){
        int count = 0;

        for (Panadero panadero : ListaPanaderos) {
            int anosExp = panadero.getAnosExperiencia();
            if (anosExp >= 2 && anosExp <= 5) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No hay Panaderos con años de experiencia entre 2 y 5.");
            JOptionPane.showMessageDialog(null, "No hay panaderos con años de experiencia entre 2 y 5.", "Porcentaje de Panaderos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            double porcentaje = (double) count / ListaPanaderos.size() * 100;
            System.out.println("El porcentaje de Panaderos con años de experiencia entre 2 y 5 es: " + porcentaje + "%");
            JOptionPane.showMessageDialog(null, "El porcentaje de Panaderos con años de experiencia entre 2 y 5 es: " + porcentaje + "%", "Porcentaje de Panaderos", JOptionPane.INFORMATION_MESSAGE);
            FilterAnosExp();
        }
    }

    public void FilterAnosExp(){
        ArrayList<Panadero> Duplicate = new ArrayList<>(ListaPanaderos);

        Iterator<Panadero> iterator = ListaPanaderos.iterator();
        while (iterator.hasNext()) {
            Panadero panadero = iterator.next();
            int anosExp = panadero.getAnosExperiencia();
            if (anosExp < 2 || anosExp > 5) {
                iterator.remove();
            }
        }

        Show();
        ListaPanaderos = Duplicate;
    }

    public void Show() {
        if (!ListaPanaderos.isEmpty()) {
            ShowConsole();
            ShowPane();
        } else {
            System.out.println("La lista de Panaderos de " + this.Name + " esta vacia");
            JOptionPane.showMessageDialog(null, "La lista de Panaderos de " + this.Name + " esta vacia", "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ShowConsolePan(int cedula) {
        Panadero panadero = Search(cedula);
        if (panadero != null) {
            System.out.println("Datos del Panadero:");
            System.out.println("Cedula: " + panadero.getCedula());
            System.out.println("Nombres: " + panadero.getNombres());
            System.out.println("Apellidos: " + panadero.getApellidos());
            System.out.println("Años de Experiencia: " + panadero.getAnosExperiencia());
            System.out.println("Edad: " + panadero.getEdad());
        } else {
            System.out.println("No se encontró un Panadero con la cédula especificada.");
        }
    }

    public void ShowPanePan(int cedula) {
        Panadero panadero = Search(cedula);
        if (panadero != null) {
            StringBuilder html = new StringBuilder();
            html.append("<html><body>");
            html.append("<h2>Datos del Panadero</h2>");
            html.append("<p><b>Cedula:</b> ").append(panadero.getCedula()).append("</p>");
            html.append("<p><b>Nombres:</b> ").append(panadero.getNombres()).append("</p>");
            html.append("<p><b>Apellidos:</b> ").append(panadero.getApellidos()).append("</p>");
            html.append("<p><b>Edad:</b> ").append(panadero.getEdad()).append("</p>");
            html.append("<p><b>Años de Experiencia:</b> ").append(panadero.getAnosExperiencia()).append("</p>");
            html.append("</body></html>");

            JOptionPane.showMessageDialog(null, html.toString(), "Datos del Panadero", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un Panadero con la cédula especificada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isExist(int cedula){
        for (Panadero panadero : ListaPanaderos) {
            if (panadero.getCedula() == cedula) {
                return true;
            }
        }
        return false;
    }

    private Panadero Search(int cedula) {
        for (Panadero panadero : ListaPanaderos) {
            if (panadero.getCedula() == cedula) {
                return panadero;
            }
        }
        return null;
    }

    public void ShowConsole() {
        String header = String.format("%s de %s", "Lista de Panaderos", this.Name);
        int headerLength = header.length();
        int separatorLength = 90; // Longitud de la línea separadora
        int padding = (separatorLength - headerLength) / 2;
        String separatorLine = "-".repeat(separatorLength);

        // Encabezado centrado
        System.out.println(separatorLine);
        System.out.printf("%" + padding + "s%s%" + padding + "s%n", "", header, "");
        System.out.println(separatorLine);

        // Columnas
        System.out.printf("%-10s %-15s %-10s %-18s %-5s%n", "Cedula", "Nombres", "Apellidos", "Años de Experiencia", "Edad");
        System.out.println(separatorLine);

        // Datos de los vendedores
        for (Panadero panadero : ListaPanaderos) {
            System.out.printf("%-10d %-15s %-10s %-18d %-5d%n",
                    panadero.getCedula(),
                    panadero.getNombres(),
                    panadero.getApellidos(),
                    panadero.getAnosExperiencia(),
                    panadero.getEdad());
        }

        // Pie de la tabla
        System.out.println(separatorLine);
    }

    public void ShowPane() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><table border='1'>");
        html.append("<tr><th>Cedula</th><th>Nombres</th><th>Apellidos</th><th>Años de Exp</th><th>Edad</th></tr>");

        for (Panadero panadero : ListaPanaderos) {
            html.append("<tr>");
            html.append("<td>").append(panadero.getCedula()).append("</td>");
            html.append("<td>").append(panadero.getNombres()).append("</td>");
            html.append("<td>").append(panadero.getApellidos()).append("</td>");
            html.append("<td>").append(panadero.getAnosExperiencia()).append("</td>");
            html.append("<td>").append(panadero.getEdad()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        JOptionPane.showMessageDialog(null, html.toString(), "Lista de Panaderos de " + this.Name, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ReadData() {
        ListaPanaderos.clear();
        String root = System.getProperty("user.dir");
        try (BufferedReader br = new BufferedReader(new FileReader(root + "\\src\\data\\Panaderos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int cedula = Integer.parseInt(parts[0]);
                String nombres = parts[1];
                String apellidos = parts[2];
                int anosExperiencia = Integer.parseInt(parts[3]);
                int edad = Integer.parseInt(parts[4]);
                ListaPanaderos.add(new Panadero(cedula, nombres, apellidos, anosExperiencia, edad));
            }
            System.out.println("Datos leídos del archivo correctamente.");
            JOptionPane.showMessageDialog(null, "Datos leídos del archivo correctamente.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Hubo un error al leer el archivo: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hubo un error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public void WriteData() {
        String root = System.getProperty("user.dir");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(root + "\\src\\data\\Panaderos.txt"))) {
            for (Panadero panadero : ListaPanaderos) {
                bw.write(String.format("%d;%s;%s;%d;%d\n",
                        panadero.getCedula(),
                        panadero.getNombres(),
                        panadero.getApellidos(),
                        panadero.getAnosExperiencia(),
                        panadero.getEdad()));
            }
            System.out.println("Datos escritos en el archivo correctamente.");
            JOptionPane.showMessageDialog(null, "Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Hubo un error al escribir en el archivo: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hubo un error al escribir en el archivo: " + e.getMessage());
        }
    }
}