package logic;

import bean.File;
import bean.Vendedor;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static utility.InputPane.*;

public class Vendedores implements File {
    private ArrayList<Vendedor> ListaVendedores;
    public String Name;

    public Vendedores(String Name){
        ListaVendedores = new ArrayList<Vendedor>();
        this.Name = Name;
    }

    public void Input(){
        int cedula = GetNum("la cedula para el vendedor");
        if(!isExist(cedula)){
            String nombres = GetDato("nombre","para el vendedor");
            String apellidos = GetDato("apellido","para el vendedor");
            int anosExperiencia = GetNum("los años de experiencia del vendedor");
            int edad = GetAge("la edad para el vendedor");
            String eps =  GetDato("EPS","para el vendedor");
            ListaVendedores.add(new Vendedor(cedula, nombres, apellidos, anosExperiencia, edad, eps));
            System.out.println("El Vendedor con la cedula "+cedula+" ha sido agregado");
            JOptionPane.showMessageDialog(null, "El Vendedor con la cedula "+cedula+" ha sido agregado");
        }else{
            System.out.println("El Vendedor con la cedula "+cedula+" ya existe");
            JOptionPane.showMessageDialog(null, "El Vendedor con la cedula "+cedula+" ya existe");
        }
        ShowConsoleVen(cedula); ShowPaneVen(cedula);
    }

    public void SearchVen(){
        int cedula = GetNum("la cedula para buscar el vendedor");
        if(isExist(cedula)){
            ShowConsoleVen(cedula); ShowPaneVen(cedula);
        }else{
            System.out.println("El Vendedor con la cedula "+cedula+" no existe");
            JOptionPane.showMessageDialog(null, "El Vendedor con la cedula "+cedula+" no existe");
        }
    }

    public void Promedio(){
        int count = 0;

        for (Vendedor vendedor : ListaVendedores) {
            int anosExp = vendedor.getAnosExperiencia();
            if (anosExp >= 2 && anosExp <= 5) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No hay trabajadores con años de experiencia entre 2 y 5.");
            JOptionPane.showMessageDialog(null, "No hay trabajadores con años de experiencia entre 2 y 5.");
        } else {
            double promedio = (double) ListaVendedores.size() / count;
            System.out.println("El promedio de años de experiencia de trabajadores con años entre 2 y 5 es: " + promedio);
            JOptionPane.showMessageDialog(null, "El promedio de años de experiencia de trabajadores con años entre 2 y 5 es: " + promedio);
        }
    }

    public void perEPS(){
        int count = 0;

        for (Vendedor vendedor : ListaVendedores) {
            String eps = vendedor.getEps();
            if (eps.matches("Sura")) {
                count++;
            }
        }

    }

    public void ShowConsoleVen(int cedula) {
        Vendedor vendedor = Search(cedula);
        if (vendedor != null) {
            System.out.println("Datos del Vendedor:");
            System.out.println("Cedula: " + vendedor.getCedula());
            System.out.println("Nombres: " + vendedor.getNombres());
            System.out.println("Apellidos: " + vendedor.getApellidos());
            System.out.println("Años de Experiencia: " + vendedor.getAnosExperiencia());
            System.out.println("Edad: " + vendedor.getEdad());
            System.out.println("EPS: " + vendedor.getEps());
        } else {
            System.out.println("No se encontró un vendedor con la cédula especificada.");
        }
    }

    public void ShowPaneVen(int cedula) {
        Vendedor vendedor = Search(cedula);
        if (vendedor != null) {
            StringBuilder html = new StringBuilder();
            html.append("<html><body>");
            html.append("<h2>Datos del Vendedor</h2>");
            html.append("<p><b>Cedula:</b> ").append(vendedor.getCedula()).append("</p>");
            html.append("<p><b>Nombres:</b> ").append(vendedor.getNombres()).append("</p>");
            html.append("<p><b>Apellidos:</b> ").append(vendedor.getApellidos()).append("</p>");
            html.append("<p><b>Edad:</b> ").append(vendedor.getEdad()).append("</p>");
            html.append("<p><b>Años de Experiencia:</b> ").append(vendedor.getAnosExperiencia()).append("</p>");
            html.append("<p><b>EPS:</b> ").append(vendedor.getEps()).append("</p>");
            html.append("</body></html>");

            JOptionPane.showMessageDialog(null, html.toString(), "Datos del Vendedor", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un vendedor con la cédula especificada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isExist(int cedula){
        for (Vendedor vendedor : ListaVendedores) {
            if (vendedor.getCedula() == cedula) {
                return true;
            }
        }
        return false;
    }

    private Vendedor Search(int cedula) {
        for (Vendedor vendedor : ListaVendedores) {
            if (vendedor.getCedula() == cedula) {
                return vendedor;
            }
        }
        return null;
    }

    public void ShowConsole() {
        String header = String.format("%s de %s", "Lista de Vendedores", this.Name);
        int headerLength = header.length();
        int separatorLength = 90; // Longitud de la línea separadora
        int padding = (separatorLength - headerLength) / 2;
        String separatorLine = "-".repeat(separatorLength);

        // Encabezado centrado
        System.out.println(separatorLine);
        System.out.printf("%" + padding + "s%s%" + padding + "s%n", "", header, "");
        System.out.println(separatorLine);

        // Columnas
        System.out.printf("%-10s %-15s %-10s %-18s %-25s%n", "Cedula", "Nombres", "Apellidos", "Años de Experiencia", "Edad", "EPS");
        System.out.println(separatorLine);

        // Datos de los vendedores
        for (Vendedor vendedor : ListaVendedores) {
            System.out.printf("%-10d %-15s %-10s %-18d %-5d %-25s%n",
                    vendedor.getCedula(),
                    vendedor.getNombres(),
                    vendedor.getApellidos(),
                    vendedor.getAnosExperiencia(),
                    vendedor.getEdad(),
                    vendedor.getEps());
        }

        // Pie de la tabla
        System.out.println(separatorLine);
    }

    public void ShowPane() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><table border='1'>");
        html.append("<tr><th>Cedula</th><th>Nombres</th><th>Apellidos</th><th>Años de Exp</th><th>Edad</th><th>EPS</th></tr>");

        for (Vendedor vendedor : ListaVendedores) {
            html.append("<tr>");
            html.append("<td>").append(vendedor.getCedula()).append("</td>");
            html.append("<td>").append(vendedor.getNombres()).append("</td>");
            html.append("<td>").append(vendedor.getApellidos()).append("</td>");
            html.append("<td>").append(vendedor.getAnosExperiencia()).append("</td>");
            html.append("<td>").append(vendedor.getEdad()).append("</td>");
            html.append("<td>").append(vendedor.getEps()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        JOptionPane.showMessageDialog(null, html.toString(), "Lista de Vendedores de " + this.Name, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ReadData() {
        ListaVendedores.clear();
        String root = System.getProperty("user.dir");
        try (BufferedReader br = new BufferedReader(new FileReader(root + "\\src\\data\\Vendedores.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int cedula = Integer.parseInt(parts[0]);
                String nombres = parts[1];
                String apellidos = parts[2];
                int anosExperiencia = Integer.parseInt(parts[3]);
                int edad = Integer.parseInt(parts[4]);
                String eps =parts[5];
                ListaVendedores.add(new Vendedor(cedula, nombres, apellidos, anosExperiencia, edad, eps));
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(root + "\\src\\data\\Vendedores.txt"))) {
            for (Vendedor vendedor : ListaVendedores) {
                bw.write(String.format("%d;%s;%s;%d;%d;%s\n",
                        vendedor.getCedula(),
                        vendedor.getNombres(),
                        vendedor.getApellidos(),
                        vendedor.getAnosExperiencia(),
                        vendedor.getEdad(),
                        vendedor.getEps()));
            }
            System.out.println("Datos escritos en el archivo correctamente.");
            JOptionPane.showMessageDialog(null, "Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Hubo un error al escribir en el archivo: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hubo un error al escribir en el archivo: " + e.getMessage());
        }
    }
}
