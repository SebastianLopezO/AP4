package view;


import bean.Nodo;
import bean.Menu;
import logic.NodosCuatro;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MenuNodosCuatro extends Menu {

    public MenuNodosCuatro(String title) {
        super(title);
    }

    @Override
    public void menu() {
        NodosCuatro nodosCuatro = new NodosCuatro();
        while (true) {
            String[] Options = {
                    "Ingresar un nodo nuevo",
                    "Buscar codigos con 2 numeros iguales seguidos",
                    "Mostrar ArrayList",
                    "Volver"
            };

            String opt;
            try {
                opt = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la opción para el Nodo: ",
                        "Nodos Cuatro",
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

                case "Ingresar un nodo nuevo":
                    String dato1 = ValidationsRegex("[A-Z]\\d{3}\\s\\d{3}-\\d{6}",
                            "Ingrese el dato 1 (X### ###-###### , X representa una letra mayuscula, # representa un digito)");
                    String dato2 = ValidationsRegex("1\\d{3}[A-Z]{6}[3-9]{2}",
                            "Ingrese el dato 2 (1###XXXXXX[3-9][3-9] , X representa una letra mayuscula, # representa un digito, [3-9] un numero entre el 3 y el 9)");
                    String codigo = ValidationsRegex("E-ISSN\\s\\d{4}-\\d{4}",
                            "Ingrese el codigo (E-ISSN ####-#### , # representa un digito)");
                    nodosCuatro.getArray().add(new Nodo(dato1, dato2, codigo));
                    break;
                case "Buscar codigos con 2 numeros iguales seguidos":
                    if (!nodosCuatro.getArray().isEmpty()) {
                        ArrayList<String> codigos = nodosCuatro.NumberEqualsNext();
                        String s = "";
                        for (String string : codigos) {
                            s += string + "\n";
                        }
                        msgScroll(s);
                    } else
                        msg("El Array esta vacio");

                    break;
                case "Mostrar ArrayList":
                    if (!nodosCuatro.getArray().isEmpty())
                        msgScroll(nodosCuatro.Show());
                    else
                        msg("El Array esta vacio");
                    break;
                default:
                    break;
            }
        }
    }

    public String ValidationsRegex(String patron, String msginput) {
        Pattern Patron = Pattern.compile(patron);
        String input;
        while (true) {
            input = input(msginput);
            if (!Patron.matcher(input).matches()) {
                msg("Formato invalido");
            } else
                return input;
        }
    }

}
