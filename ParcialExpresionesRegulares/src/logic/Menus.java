package logic;

import bean.Menu;
import view.*;

import javax.swing.*;

public class Menus extends Menu {
    public Menus(String title) {
        super(title);
    }

    public void menu() {
        while (true) {
            String[] Options = {
                    "Ejercicio Nodos Uno",
                    "Ejercicio Nodos Dos",
                    "Ejercicio Docentes",
                    "Ejercicio Nodos Cuatro",
                    "Ejercicio Tarjetas de Credito",
                    "Salir"
            };

            String opt = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecciona un Ejercicio: ",
                    "Menu Principal",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Options,
                    Options[0]);

            switch (opt) {

                case "Ejercicio Nodos Uno":
                    new MenuNodosUno("Menu de Nodos Uno.").menu();
                    break;

                case "Ejercicio Nodos Dos":
                    new MenuNodosDos("Menu de Nodos Dos").menu();
                    break;

                case "Ejercicio Docentes":
                    new MenuDocentes("Menu Docentes").menu();
                    break;

                case "Ejercicio Nodos Cuatro":
                    new MenuNodosCuatro("Menu de Nodos Cuatro").menu();
                    break;

                case "Ejercicio Tarjetas de Credito":
                    new MenuTarjetas("Menu Tarjetas de Credito").menu();
                    break;

                case "Salir":
                    System.exit(0);
                    break;

                default:
                    msg("opcion invalida.");
                    break;
            }
        }

    }
}
