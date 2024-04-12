package view;

import bean.Menu;
import bean.Numeros;
import logic.NodosUno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class MenuNodosUno extends Menu {

    public MenuNodosUno(String title) {
        super(title);
    }

    public void menu() {
        NodosUno p1 = new NodosUno();
        while (true) {
            int opt;
            try {
                opt = Integer.parseInt(input(
                        "Menu:  \n1. Ingresar nodos automaticamente. \n2. Ingresar nodos manualmente.\n3. Mostrar ArrayList. \n4. Promedios \n0. Salir"));
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

                case 1: // añadir automaticamente
                    msg("Añadiendo nodos hasta el siguiente numero primo...");
                    p1.siguientesNodos();
                    break;

                case 2: // añadir manualmente
                    int Nciclos;
                    if (!p1.getArrayList().isEmpty()) {
                        Nciclos = p1.siguientePrimo() - p1.getArrayList().size();
                    } else
                        Nciclos = 2;
                    for (int i = 1; i <= Nciclos; i++) {
                        String cadena = ValidacionNumeros(p1.getArrayList().size()+1, p1);
                        p1.getArrayList().add(new Numeros(
                                Arrays.stream(cadena.split(" ")).mapToInt(Integer::parseInt).toArray()));
                    }
                    break;

                case 3:
                    if (!p1.getArrayList().isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        ArrayList<Numeros> n = p1.getArrayList();
                        for (int i = 0; i < n.size(); i++) {
                            sb.append("Nodo " + (i + 1) + "\n\tN1: " + n.get(i).getN1() + "\n\tN2: " + n.get(i).getN2()
                                    + "\n\tN3: " + n.get(i).getN3() + "\n");
                        }
                        msgScroll(sb.toString());
                    } else
                        msg("El ArrayList esta vacio.");
                    break;

                case 4:
                    msgScroll(p1.promedios());
                    break;

                default:
                    msg("opcion invalida.");
                    break;
            }

        }
    }

    public String Validaciones(String patron, String msginput) {// metodo para realizar todas las valdiaciones con
        // expresiones regulares
        Pattern Patron = Pattern.compile(patron);
        String input;
        while (true) {
            input = input(msginput).trim();
            if (!Patron.matcher(input).matches()) { // validar el formato correcto
                msg("Formato invalido");
            } else
                return input;
        }
    }

    public String ValidacionNumeros(int digitos, NodosUno p1) {
        while (true) {
            String cadena = Validaciones("\\d{" + digitos + "} \\d{" + digitos + "} \\d{" + digitos + "}",
                    "Ingrese 3 numeros separados por un espacio, cada numero de " + digitos + " digitos y cada numero tiene que ser mas grande que el anterior (ej: # (#+1) (#+2))");

            if (p1.verificarCadena(cadena+" ", digitos))
                return cadena;
            else msg("Cadad numero debe ser mayor que el anterior");
        }
    }
}
