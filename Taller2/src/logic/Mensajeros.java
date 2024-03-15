package logic;

import bean.File;
import bean.Mensajero;
import bean.Vendedor;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static utility.InputPane.*;

public class Mensajeros implements File {
    private ArrayList<Mensajero> ListaMensajeros;
    public String Name;

    public Mensajeros(String Name){
        ListaMensajeros = new ArrayList<Mensajero>();
        this.Name = Name;
    }

    public void Input(){
        int cedula = GetNum("la cedula para el mensajero");
        if(!isExist(cedula)){
            String nombres = GetDato("nombre","para el mensajero");
            String apellidos = GetDato("apellido","para el mensajero");
            int anosExperiencia = GetNum("los años de experiencia del mensajero");
            int edad = GetAge("la edad para el mensajero");
            String eps =  GetDato("EPS","para el mensajero");
            String arl =  GetDato("Pension","para el mensajero");
            String pension =  GetDato("Pension","para el mensajero");

            ListaMensajeros.add(new Mensajero(cedula, nombres, apellidos, edad, eps, pension, arl));
            System.out.println("El Mensajero con la cedula "+cedula+" ha sido agregado");
            JOptionPane.showMessageDialog(null, "El Mensajero con la cedula "+cedula+" ha sido agregado");
        }else{
            System.out.println("El Mensajero con la cedula "+cedula+" ya existe");
            JOptionPane.showMessageDialog(null, "El Mensajero con la cedula "+cedula+" ya existe");
        }
        ShowConsoleMsj(cedula); ShowPaneMsj(cedula);
    }

    public void SearchMsj(){
        int cedula = GetNum("la cedula para buscar el Mensajero");
        if(isExist(cedula)){
            ShowConsoleMsj(cedula); ShowPaneMsj(cedula);
        }else{
            System.out.println("El Mensajero con la cedula "+cedula+" no existe");
            JOptionPane.showMessageDialog(null, "El Mensajero con la cedula "+cedula+" no existe");
        }
    }

    public void perARL(String arl){
        int count = 0;

        for (Mensajero mensajero : ListaMensajeros) {
            if (mensajero.getArl().equals(arl)) {
                count++;
            }
        }

        double per = (double) count / ListaMensajeros.size() * 100;

        System.out.println("Porcentaje de Mensajeros con ARL '" + arl + "': " + per + "%");
        JOptionPane.showMessageDialog(null, "Porcentaje de Mensajeros con ARL '" + arl + "': " + per + "%", "Porcentaje de Mensajeros", JOptionPane.INFORMATION_MESSAGE);
        FilterEPS(arl);
    }

    public void FilterARL(String arl){
        ArrayList<Mensajero> Duplicate = new ArrayList<>(ListaMensajeros);

        Iterator<Mensajero> iterator = ListaMensajeros.iterator();
        while (iterator.hasNext()) {
            Mensajero mensajero = iterator.next();
            if (!mensajero.getArl().equals(arl)) {
                iterator.remove();
            }
        }

        Show();
        ListaMensajeros = Duplicate;
    }

    public void perPen(String pension){
        int count = 0;

        for (Mensajero mensajero : ListaMensajeros) {
            if (mensajero.getPension().equals(pension)) {
                count++;
            }
        }

        double per = (double) count / ListaMensajeros.size() * 100;

        System.out.println("Porcentaje de Mensajeros con Pension '" + pension + "': " + per + "%");
        JOptionPane.showMessageDialog(null, "Porcentaje de Mensajeros con Pension '" + pension + "': " + per + "%", "Porcentaje de Mensajeros", JOptionPane.INFORMATION_MESSAGE);
        FilterEPS(pension);
    }

    public void FilterPen(String pension){
        ArrayList<Mensajero> Duplicate = new ArrayList<>(ListaMensajeros);

        Iterator<Mensajero> iterator = ListaMensajeros.iterator();
        while (iterator.hasNext()) {
            Mensajero mensajero = iterator.next();
            if (!mensajero.getPension().equals(pension)) {
                iterator.remove();
            }
        }

        Show();
        ListaMensajeros = Duplicate;
    }

    public void perEPS(String eps){
        int count = 0;

        for (Mensajero mensajero : ListaMensajeros) {
            if (mensajero.getEps().equals(eps)) {
                count++;
            }
        }

        double per = (double) count / ListaMensajeros.size() * 100;

        System.out.println("Porcentaje de Mensajeros con EPS '" + eps + "': " + per + "%");
        JOptionPane.showMessageDialog(null, "Porcentaje de Mensajeros con EPS '" + eps + "': " + per + "%", "Porcentaje de Vendedores", JOptionPane.INFORMATION_MESSAGE);
        FilterEPS(eps);
    }

    public void FilterEPS(String eps){
        ArrayList<Mensajero> Duplicate = new ArrayList<>(ListaMensajeros);

        Iterator<Mensajero> iterator = ListaMensajeros.iterator();
        while (iterator.hasNext()) {
            Mensajero mensajero = iterator.next();
            if (!mensajero.getEps().equals(eps)) {
                iterator.remove();
            }
        }

        Show();
        ListaMensajeros = Duplicate;
    }

    public void Show() {
        if (!ListaMensajeros.isEmpty()) {
            ShowConsole();
            ShowPane();
        } else {
            System.out.println("La lista de Mensajeros de " + this.Name + " esta vacia");
            JOptionPane.showMessageDialog(null, "La lista de Mensajeros de " + this.Name + " esta vacia", "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ShowConsoleMsj(int cedula) {
        Mensajero mensajero = Search(cedula);
        if (mensajero != null) {
            System.out.println("Datos del Mensajero:");
            System.out.println("Cedula: " + mensajero.getCedula());
            System.out.println("Nombres: " + mensajero.getNombres());
            System.out.println("Apellidos: " + mensajero.getApellidos());
            System.out.println("Edad: " + mensajero.getEdad());
            System.out.println("EPS: " + mensajero.getEps());
            System.out.println("ARL: " + mensajero.getArl());
            System.out.println("Pension: " + mensajero.getPension());
        } else {
            System.out.println("No se encontró un mensajero con la cédula especificada.");
        }
    }

    public void ShowPaneMsj(int cedula) {
        Mensajero mensajero = Search(cedula);
        if (mensajero != null) {
            StringBuilder html = new StringBuilder();
            html.append("<html><body>");
            html.append("<h2>Datos del Vendedor</h2>");
            html.append("<p><b>Cedula:</b> ").append(mensajero.getCedula()).append("</p>");
            html.append("<p><b>Nombres:</b> ").append(mensajero.getNombres()).append("</p>");
            html.append("<p><b>Apellidos:</b> ").append(mensajero.getApellidos()).append("</p>");
            html.append("<p><b>Edad:</b> ").append(mensajero.getEdad()).append("</p>");
            html.append("<p><b>EPS:</b> ").append(mensajero.getEps()).append("</p>");
            html.append("<p><b>ARL:</b> ").append(mensajero.getArl()).append("</p>");
            html.append("<p><b>Pension:</b> ").append(mensajero.getPension()).append("</p>");
            html.append("</body></html>");

            JOptionPane.showMessageDialog(null, html.toString(), "Datos del Mensajero", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un mensajero con la cédula especificada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isExist(int cedula){
        for (Mensajero mensajero : ListaMensajeros) {
            if (mensajero.getCedula() == cedula) {
                return true;
            }
        }
        return false;
    }

    private Mensajero Search(int cedula) {
        for (Mensajero mensajero : ListaMensajeros) {
            if (mensajero.getCedula() == cedula) {
                return mensajero;
            }
        }
        return null;
    }

    public void ShowConsole() {
        String header = String.format("%s de %s", "Lista de Mensajero", this.Name);
        int headerLength = header.length();
        int separatorLength = 140; // Longitud de la línea separadora
        int padding = (separatorLength - headerLength) / 2;
        String separatorLine = "-".repeat(separatorLength);

        // Encabezado centrado
        System.out.println(separatorLine);
        System.out.printf("|%" + (padding - 1) + "s%s%" + (padding) + "s|%n", "", header, "");
        System.out.println(separatorLine);

        // Columnas
        System.out.printf("|   %-10s| %-25s| %-25s| %-5s| %-25s| %-25s| %-25s   |%n", "Cedula", "Nombres", "Apellidos", "Edad", "EPS", "ARL", "Pension");
        System.out.println(separatorLine);

        // Datos de los vendedores
        for (Mensajero mensajero : ListaMensajeros) {
            System.out.printf("|   %-10d| %-25s| %-25s| %-5d| %-25s| %-25s| %-25s   |%n",
                    mensajero.getCedula(),
                    mensajero.getNombres(),
                    mensajero.getApellidos(),
                    mensajero.getEdad(),
                    mensajero.getEps(),
                    mensajero.getArl(),
                    mensajero.getPension());
        }

        // Pie de la tabla
        System.out.println(separatorLine);
    }

    public void ShowPane() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><table border='1'>");
        html.append("<tr><th>Cedula</th><th>Nombres</th><th>Apellidos</th><th>Edad</th><th>EPS</th><th>ARL</th><th>Pension</th></tr>");

        for (Mensajero mensajero : ListaMensajeros) {
            html.append("<tr>");
            html.append("<td>").append(mensajero.getCedula()).append("</td>");
            html.append("<td>").append(mensajero.getNombres()).append("</td>");
            html.append("<td>").append(mensajero.getApellidos()).append("</td>");
            html.append("<td>").append(mensajero.getEdad()).append("</td>");
            html.append("<td>").append(mensajero.getEps()).append("</td>");
            html.append("<td>").append(mensajero.getArl()).append("</td>");
            html.append("<td>").append(mensajero.getPension()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        JOptionPane.showMessageDialog(null, html.toString(), "Lista de Mensajeros de " + this.Name, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ReadData() {
        ListaMensajeros.clear();
        String root = System.getProperty("user.dir");
        try (BufferedReader br = new BufferedReader(new FileReader(root + "\\src\\data\\Mensajeros.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int cedula = Integer.parseInt(parts[0]);
                String nombres = parts[1];
                String apellidos = parts[2];
                int edad = Integer.parseInt(parts[3]);
                String eps =parts[4];
                String arl =parts[5];
                String pension =parts[6];
                ListaMensajeros.add(new Mensajero(cedula, nombres, apellidos, edad, eps, arl, pension));
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(root + "\\src\\data\\Mensajeros.txt"))) {
            for (Mensajero mensajero : ListaMensajeros) {
                bw.write(String.format("%d;%s;%s;%d;%s;%s;%s\n",
                        mensajero.getCedula(),
                        mensajero.getNombres(),
                        mensajero.getApellidos(),
                        mensajero.getEdad(),
                        mensajero.getEps(),
                        mensajero.getArl(),
                        mensajero.getPension()));
            }
            System.out.println("Datos escritos en el archivo correctamente.");
            JOptionPane.showMessageDialog(null, "Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Hubo un error al escribir en el archivo: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hubo un error al escribir en el archivo: " + e.getMessage());
        }
    }
}
