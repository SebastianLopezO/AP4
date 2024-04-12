package logic;


import bean.TarjetaCredito;
import utility.FileManager;
import utility.Html;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tarjetas {
    private ArrayList<TarjetaCredito> Tarjetas;
    private ArrayList<TarjetaCredito> Tarjetas_MasterCard;
    private ArrayList<TarjetaCredito> Tarjetas_Visa;

    public Tarjetas() {
        Tarjetas = new ArrayList<TarjetaCredito>();
        Tarjetas_MasterCard = new ArrayList<TarjetaCredito>();
        Tarjetas_Visa = new ArrayList<TarjetaCredito>();

        try {
            String root = System.getProperty("user.dir");
            FileManager file = new FileManager(root + "/src/Data/Tarjetas.txt");
            String[] lineas = file.readFile().split("-");

            for (int i = 0; i < lineas.length; i++) {
                String[] tarjetas = lineas[i].split(";");
                TarjetaCredito tarjeta = new TarjetaCredito(tarjetas[0], tarjetas[1], tarjetas[2], tarjetas[3],
                        tarjetas[4], tarjetas[5]);
                Tarjetas.add(tarjeta);
            }
        } catch (IOException e) {
            System.out.println("Hubo un error al leer el archivo de Tarjetas de Credito: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hubo un error al leer el archivo de Tarjetas de Credito: " + e.getMessage());
        }
        Pattern mastercard = Pattern.compile("MasterCard");
        for (TarjetaCredito tarjeta : Tarjetas) {

            Matcher match = mastercard.matcher(tarjeta.getTipo());
            if (match.matches()) {
                Tarjetas_MasterCard.add(tarjeta);
            } else {
                Tarjetas_Visa.add(tarjeta);
            }
        }

    }

    public ArrayList<TarjetaCredito> getTarjetas() {
        return Tarjetas;
    }

    public void setTarjetas(ArrayList<TarjetaCredito> tarjetas) {
        Tarjetas = tarjetas;
    }

    public ArrayList<TarjetaCredito> getTarjetas_MasterCard() {
        return Tarjetas_MasterCard;
    }

    public void setTarjetas_MasterCard(ArrayList<TarjetaCredito> tarjetas_MasterCard) {
        Tarjetas_MasterCard = tarjetas_MasterCard;
    }

    public ArrayList<TarjetaCredito> getTarjetas_Visa() {
        return Tarjetas_Visa;
    }

    public void setTarjetas_Visa(ArrayList<TarjetaCredito> tarjetas_Visa) {
        Tarjetas_Visa = tarjetas_Visa;
    }

    public String CardsYear(String year) {
        Pattern patron = Pattern.compile("/" + year);
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h3>Tarjetas MasterCard y Visa en el año "+year+"</h3><table border='1'>");
        html.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>");
        html.append("<th>Id</th><th>Codigo</th><th>Fecha</th><th>Tipo</th><th>Nombre</th><th>Apellido</th>");
        html.append("</tr>");

        boolean isGrayDark = true;
        String bgColor = isGrayDark ? "#404040" : "#737373";

        html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>").append("<tr><td colspan='8'>").append("MASTERCARD").append("</td>").append("</tr>");

        for (TarjetaCredito tarjetaCredito : Tarjetas_MasterCard) {
            Matcher match = patron.matcher(tarjetaCredito.getFecha());
            if (match.find()) {
                bgColor = isGrayDark ? "#404040" : "#737373";

                html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
                html.append("<td>").append(tarjetaCredito.getNumero()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getCodigo()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getFecha()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getTipo()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getNombre()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getApellido()).append("</td>");
                html.append("</tr>");

                isGrayDark = !isGrayDark;
            }
        }
        bgColor = isGrayDark ? "#404040" : "#737373";
        html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>").append("<tr><td colspan='8'>").append("VISA").append("</td>").append("</tr>");
        for (TarjetaCredito tarjetaCredito : Tarjetas_Visa) {
            Matcher match = patron.matcher(tarjetaCredito.getFecha());
            if (match.find()) {
                bgColor = isGrayDark ? "#404040" : "#737373";

                html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
                html.append("<td>").append(tarjetaCredito.getNumero()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getCodigo()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getFecha()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getTipo()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getNombre()).append("</td>");
                html.append("<td>").append(tarjetaCredito.getApellido()).append("</td>");
                html.append("</tr>");

                isGrayDark = !isGrayDark;
            }
        }
        html.append("</table></body></html>");
        Html File = new Html("Tarjetas en el año "+year,"Bancolombia");
        File.AddBody(html.toString());
        File.Export("Tarjeta_Bancolombia_Year_"+year);

        return html.toString();
    }

    public void Insert(String nombre, String apellido, String numero, String fecha, String codigo, String tipo) {
        try {
            String root = System.getProperty("user.dir");
            FileManager file = new FileManager(root + "/src/Data/Tarjetas.txt");
            String split = ";";
            file.adicionarLinea("\n"+tipo + split + numero + split + fecha + split + nombre + split + apellido + split + codigo);
            TarjetaCredito tarjeta = new TarjetaCredito(tipo, numero, fecha, nombre, apellido, codigo);
            Tarjetas.add(tarjeta);
            Pattern Tipo = Pattern.compile("MasterCard");
            Matcher match = Tipo.matcher(tarjeta.getTipo());
            if (match.matches()) {
                Tarjetas_MasterCard.add(tarjeta);
            } else {
                Tarjetas_Visa.add(tarjeta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean NewCard(String numero) {
        boolean bandera = true;
        for (TarjetaCredito tarjetaCredito : Tarjetas) {
            if (tarjetaCredito.getNumero().equals(numero)) {
                bandera = false;
                break;
            }
        }
        return bandera;
    }

    public String ShowCards() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h3>Tarjetas MasterCard y Visa</h3><table border='1'>");
        html.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>");
        html.append("<th>Id</th><th>Codigo</th><th>Fecha</th><th>Tipo</th><th>Nombre</th><th>Apellido</th>");
        html.append("</tr>");

        boolean isGrayDark = true;
        String bgColor = isGrayDark ? "#404040" : "#737373";

        html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>").append("<tr><td colspan='8'>").append("MASTERCARD").append("</td>").append("</tr>");
        for (TarjetaCredito tarjetaCredito : Tarjetas_MasterCard) {
            bgColor = isGrayDark ? "#404040" : "#737373";

            html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
            html.append("<td>").append(tarjetaCredito.getNumero()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getCodigo()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getFecha()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getTipo()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getNombre()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getApellido()).append("</td>");
            html.append("</tr>");

            isGrayDark = !isGrayDark;

        }
        bgColor = isGrayDark ? "#404040" : "#737373";
        html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>").append("<tr><td colspan='8'>").append("VISA").append("</td>").append("</tr>");
        for (TarjetaCredito tarjetaCredito : Tarjetas_Visa) {
            bgColor = isGrayDark ? "#404040" : "#737373";

            html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
            html.append("<td>").append(tarjetaCredito.getNumero()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getCodigo()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getFecha()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getTipo()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getNombre()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getApellido()).append("</td>");
            html.append("</tr>");

            isGrayDark = !isGrayDark;
        }

        html.append("</table></body></html>");
        Html File = new Html("Tarjetas","Bancolombia");
        File.AddBody(html.toString());
        File.Export("Tarjeta_Bancolombia");

        return html.toString();
    }

    public String ShowMasterCard() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h3>Tarjetas MasterCard</h3><table border='1'>");
        html.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>");
        html.append("<th>Id</th><th>Codigo</th><th>Fecha</th><th>Tipo</th><th>Nombre</th><th>Apellido</th>");
        html.append("</tr>");

        boolean isGrayDark = true;

        for (TarjetaCredito tarjetaCredito : Tarjetas_MasterCard) {
            String bgColor = isGrayDark ? "#404040" : "#737373";

            html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
            html.append("<td>").append(tarjetaCredito.getNumero()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getCodigo()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getFecha()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getTipo()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getNombre()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getApellido()).append("</td>");
            html.append("</tr>");

            isGrayDark = !isGrayDark;
        }

        html.append("</table></body></html>");
        Html File = new Html("Tarjetas_MasterCard","Bancolombia");
        File.AddBody(html.toString());
        File.Export("Tarjetas_MasterCard");
        return html.toString();
    }

    public String ShowVisa() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><h3>Tarjetas Visas</h3><table border='1'>");
        html.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>");
        html.append("<th>Id</th><th>Codigo</th><th>Fecha</th><th>Tipo</th><th>Nombre</th><th>Apellido</th>");
        html.append("</tr>");

        boolean isGrayDark = true;

        for (TarjetaCredito tarjetaCredito : Tarjetas_Visa) {
            String bgColor = isGrayDark ? "#404040" : "#737373";

            html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
            html.append("<td>").append(tarjetaCredito.getNumero()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getCodigo()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getFecha()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getTipo()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getNombre()).append("</td>");
            html.append("<td>").append(tarjetaCredito.getApellido()).append("</td>");
            html.append("</tr>");

            isGrayDark = !isGrayDark;
        }
        html.append("</table></body></html>");
        Html File = new Html("Tarjetas_Visa","Bancolombia");
        File.AddBody(html.toString());
        File.Export("Tarjetas_Visa");
        return html.toString();
    }

}
