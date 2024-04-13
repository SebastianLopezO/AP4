package view;

import bean.Docente;
import bean.Menu;
import logic.Docentes;
import utility.Html;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class MenuDocentes extends Menu {
    public MenuDocentes(String title) {
        super(title);
    }

    public void menu() {
        Docentes docentes = new Docentes();
        while (true) {
            String opt;
            try {
                String[] Options ={
                        "Añadir nuevo docente",
                        "Buscar porcentaje de docentes por tipo",
                        "Buscar docentes por tipo y un mes",
                        "Mostrar los docentes",
                        "Mostrar total de intentos",
                        "Cargar Docentes",
                        "Volver"
                };

                opt = (String) JOptionPane.showInputDialog(
                        null,
                        "Selecciona una opción: ",
                        "Docentes",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Options,
                        Options[0]);

            } catch (Exception e) {
                msg("Se necesita una opcion valida." + e.getMessage());
                continue;
            }


            String Type;
            boolean continuar;
            try {


                switch (opt) {
                    case "Añadir nuevo docente":

                        // patrones
                        Pattern patronCC = Pattern.compile("[0-9]{6,10}");
                        Pattern patronFecha = Pattern.compile("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})"); // (dd)-(mm)-(mmmm)

                        String Id;
                        int Attempts = 0;

                        do {
                            Id = input("Ingresa el numero de identificación del docente (Un numero de 6 a 10 Digitos seguidos, sin puntos ni comas): ").trim();
                            continuar = !patronCC.matcher(Id).matches();
                            if (continuar) {
                                msg("El numero de Identificación no coincide con el formato!");
                                Attempts++;
                            }
                        } while (continuar);

                        String Name = input("Ingresa el nombre del docente: ");

                        if (!docentes.UniqueID(Id)) {
                            msg("El numero de identificación ya existe!");
                            break;
                        }

                        String Date;
                        do {
                            Date = input("Ingrese la fecha en formato (dd-mm-aaaa): ").trim();
                            continuar = !patronFecha.matcher(Date).matches();
                            if (continuar) msg("La Fecha no coincide con el formato!");
                        } while (continuar);

                        Type = TypeTeachers();
                        docentes.AddTeacher(new Docente(Id,Name, Type, Date, Attempts));
                        break;

                    case "Buscar porcentaje de docentes por tipo":
                        Type = TypeTeachers();
                        msg("El porcentaje de los docentes " + (Type.equals("Ocasional") ? " " : "de ") + Type + " son: " + docentes.PercentageType(Type) + "%");
                        break;

                    case "Buscar docentes por tipo y un mes":
                        String Month;
                        Pattern patronMes = Pattern.compile("0[1-9]|1[0-2]");

                        do {
                            Month = input("Escriba el mes en formato numerico (01 - 12 y sin espacioes)").trim();
                            continuar = !patronMes.matcher(Month).matches();
                            if (continuar) msg("El mes no cuenta con el formato adecuado");
                        } while (continuar);

                        Type = TypeTeachers();

                        ArrayList<Docente> docentesMonth = docentes.TeachersForMonth(Month,Type);
                        ShowTeachers(docentesMonth);
                        break;

                    case "Mostrar los docentes":
                        ArrayList<Docente> ListDocentes = docentes.getListTeachers();
                        ShowTeachers(ListDocentes);
                        break;

                    case "Mostrar total de intentos":
                        msg("El total de intentos de cedula fueron: " + docentes.Attempts());
                        break;

                    case "Cargar Docentes":
                        docentes.LoadTeachers();
                        break;

                    case "Volver": {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void ShowTeachers(ArrayList<Docente> docentes) {
            Iterator<Docente> i = docentes.iterator();
            StringBuilder html = new StringBuilder();
            html.append("<html><body><table border='1'>");
            html.append("<tr style='background-color: #000000; color: #ffffff; font-weight: bold;'>");
            html.append("<th>Id</th><th>Nombre</th><th>Tipo</th><th>Fecha</th><th>Intentos</th>");
            html.append("</tr>");

            boolean isGrayDark = true;

            while (i.hasNext()) {
                Docente d = i.next();

                String bgColor = isGrayDark ? "#404040" : "#737373";

                html.append("<tr style='background-color: ").append(bgColor).append("; color: #ffffff;'>");
                html.append("<td>").append(d.getId()).append("</td>");
                html.append("<td>").append(d.getName()).append("</td>");
                html.append("<td>").append(d.getType()).append("</td>");
                html.append("<td>").append(d.getDate()).append("</td>");
                html.append("<td>").append(d.getAttempts()).append("</td>");
                html.append("</tr>");

                isGrayDark = !isGrayDark;
            }
            html.append("</table></body></html>");
            Html File = new Html("Docentes","POLI");
            File.AddBody(html.toString());
            File.Export("Docentes_POLI");
            msgHtml(html.toString(),400,500);

    }

    private String TypeTeachers() {
        String[] Options = {
                "Planta",
                "Ocasional",
                "Cátedra"
        };

        String Option = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el tipo de Docente: ",
                "Docente",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);
        return Option;
    }

}
