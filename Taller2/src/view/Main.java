package view;


import logic.Mensajeros;
import logic.Panaderos;
import logic.Vendedores;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Mensajeros ListVendedores = new Mensajeros("PanPan");
        ListVendedores.Show();
        ListVendedores.ReadData();
        ListVendedores.Show();
    }

    public static String MenuTrab() {

        String[] Options = {
                "ListA",
                "ListB",
                "ListC",
                "ListD",
                "ListAns",
                "Operacion entre Listas",
                "Salir"
        };

        String Option = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la lista: ",
                "Variables",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);
        return Option;
    }
}