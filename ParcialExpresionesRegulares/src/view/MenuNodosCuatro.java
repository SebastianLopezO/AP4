package view;


import bean.Nodo;
import bean.Menu;
import logic.NodosCuatro;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MenuNodosCuatro extends Menu {

    public MenuNodosCuatro(String title) {
        super(title);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void menu() {
        // TODO Auto-generated method stub
        NodosCuatro p4 = new NodosCuatro();
        while (true) {
            int opt;
            try {
                opt = Integer.parseInt(input(
                        "Menu 🗿: \n1. Ingresar un nodo nuevo \n2. Buscar codigos con 2 numeros iguales seguidos. \n3. Mostrar ArrayList. \n0. Salir."));
            } catch (Exception e) {
                e.printStackTrace();
                // control de la excepcion.
                if (e.toString().contains("Cannot parse null string"))
                    return;
                msg("Se necesita una opcion valida.");
                continue;
            }
            switch (opt) {
                case 0: {
                    return;
                }

                case 1: // ingresar un nodo nuevo
                    String dato1 = Validaciones("[A-Z]\\d{3}\\s\\d{3}-\\d{6}",
                            "Ingrese el dato 1 (X### ###-###### , X representa una letra mayuscula, # representa un digito)");
                    String dato2 = Validaciones("1\\d{3}[A-Z]{6}[3-9]{2}",
                            "Ingrese el dato 2 (1###XXXXXX[3-9][3-9] , X representa una letra mayuscula, # representa un digito, [3-9] un numero entre el 3 y el 9)");
                    String codigo = Validaciones("E-ISSN\\s\\d{4}-\\d{4}",
                            "Ingrese el codigo (E-ISSN ####-#### , # representa un digito)");
                    p4.getArray().add(new Nodo(dato1, dato2, codigo));
                    break;
                case 2: // mostrar codigos con 2 numeros iguales seguidos
                    if (!p4.getArray().isEmpty()) {
                        ArrayList<String> codigos = p4.NumerosIgualesSeguidos();
                        String s = "";
                        for (String string : codigos) {
                            s += string + "\n";
                        }
                        msgScroll(s);
                    } else
                        msg("El Array esta vacio");

                    break;
                case 3: // mostrar el array
                    if (!p4.getArray().isEmpty())
                        msgScroll(p4.Mostrar());
                    else
                        msg("El Array esta vacio");
                    break;
                default:
                    break;
            }
        }
    }

    public String Validaciones(String patron, String msginput) {// metodo para realizar todas las valdiaciones con
        // expresiones regulares
        Pattern Patron = Pattern.compile(patron);
        String input;
        while (true) {
            input = input(msginput);
            if (!Patron.matcher(input).matches()) { // validar el formato correcto
                msg("Formato invalido");
            } else
                return input;
        }
    }

}
