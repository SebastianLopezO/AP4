package utility;

import javax.swing.*;
import java.util.ArrayList;

public class InputPane {
    public static String GetDato(String Type,String Option) {
        String line;
        while (true) {
            line = JOptionPane.showInputDialog("Ingrese un/una " + Type + " para " + Option + ": ");
            if(line != null){
                if (line.trim().isEmpty()) {
                    System.out.println("No puedes ingresar un/una " + Type +" vacia para " + Option);
                    JOptionPane.showMessageDialog(null, "No puedes ingresar un/una " + Type +" vacia para " + Option);
                } else {
                    return line;
                }

            }else{
                return "";
            }
        }
    }

    public static int GetNum(String Option) {
        int num;
        while (true) {
            try {
                num = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Numero para "+Option+ ": "));
                return num;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "No se ha insertado un numero.");
                System.out.println("No ha insertado un numero, error" + ex);
            }
        }
    }

    public static String GetOption(ArrayList<String> options, String title){
        String[] Options = options.toArray(new String[0]);

        String Option = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una Opción para "+title+": ",
                title,
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);

        return Option;
    }
}
