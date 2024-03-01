package utility;

import javax.swing.*;

public class InputPane {
    public static String GetDato(String Type,String Option) {
        String line;
        while (true) {
            line = JOptionPane.showInputDialog("Ingrese un/una " + Type + " para " + Option + ": ");
            if(line != null){
                if (line.trim().isEmpty()) {
                    System.out.println("No puedes ingresar un/una " + Type +" vacia para " + Option);
                } else {
                    return line;
                }

            }else{
                return "";
            }
        }
    }
}
