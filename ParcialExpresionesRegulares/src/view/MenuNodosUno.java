package view;

import bean.Menu;
import bean.Numeros;
import logic.NodosUno;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class MenuNodosUno extends Menu {

    public MenuNodosUno(String title) {
        super(title);
    }

    public void menu() {
        NodosUno nodosUno = new NodosUno();
        while (true) {
            String[] Options = {
                    "Generar nodos",
                    "Ingresar nodos",
                    "Mostrar ArrayList",
                    "Promedios",
                    "Volver",
            };
            String opt;
            try {
                opt = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la opción para Nodos: ",
                        "NodosUno",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Options,
                        Options[0]);
            } catch (Exception e) {
                msg("Se necesita una opcion valida " + e.getMessage());
                continue;
            }

            switch (opt) {
                case "Volver": {
                    return;
                }

                case "Generar nodos":
                    msg("Añadiendo nodos hasta el siguiente numero primo...");
                    nodosUno.NextNodo();
                    break;

                case "Ingresar nodos":
                    int Nciclos;
                    if (!nodosUno.getArrayList().isEmpty()) {
                        Nciclos = nodosUno.NextPrimo() - nodosUno.getArrayList().size();
                    } else
                        Nciclos = 2;
                    for (int i = 1; i <= Nciclos; i++) {
                        String cadena = ValidateNumber(nodosUno.getArrayList().size()+1, nodosUno);
                        nodosUno.getArrayList().add(new Numeros(
                                Arrays.stream(cadena.split(" ")).mapToInt(Integer::parseInt).toArray()));
                    }
                    break;

                case "Mostrar ArrayList":
                    if (!nodosUno.getArrayList().isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        ArrayList<Numeros> n = nodosUno.getArrayList();
                        for (int i = 0; i < n.size(); i++) {
                            sb.append("Nodo " + (i + 1) + "\n\tN1: " + n.get(i).getN1() + "\n\tN2: " + n.get(i).getN2()
                                    + "\n\tN3: " + n.get(i).getN3() + "\n");
                        }
                        msgScroll(sb.toString());
                    } else
                        msg("El ArrayList esta vacio.");
                    break;

                case "Promedios":
                    msgScroll(nodosUno.Average());
                    break;

                default:
                    msg("opcion invalida.");
                    break;
            }

        }
    }

    public String ValidateRegex(String patron, String msginput) {
        Pattern Patron = Pattern.compile(patron);
        String input;
        while (true) {
            input = input(msginput).trim();
            if (!Patron.matcher(input).matches()) {
                msg("Formato invalido");
            } else
                return input;
        }
    }

    public String ValidateNumber(int digitos, NodosUno p1) {
        while (true) {
            String cadena = ValidateRegex("\\d{" + digitos + "} \\d{" + digitos + "} \\d{" + digitos + "}",
                    "Ingrese 3 numeros separados por un espacio, cada numero de " + digitos + " digitos y cada numero tiene que ser mas grande que el anterior (ej: # (#+1) (#+2))");

            if (p1.ValidateString(cadena+" ", digitos))
                return cadena;
            else msg("Cadad numero debe ser mayor que el anterior");
        }
    }
}
