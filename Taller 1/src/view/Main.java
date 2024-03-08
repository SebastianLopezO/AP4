package view;
import bean.Ajedrez;
import bean.ListNumeros;
import bean.Listas;
import logic.Partidos;
import static utility.InputPane.GetDato;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        boolean app = true, ej = false, action=false;
        String Option, OptionEj, OptionAc;
        Ajedrez Tablero = null;
        while (app) {
            ej = true;
            Option = Menu();
            switch (Option){
                case "1. ArrayList Simple":
                    Listas Lista = new Listas(GetDato("Nombre","el nombre de la lista"));
                    break;
                case "2. ArrayList Objetos":
                    break;
                case "3. ArrayList de ArrayList":
                    break;
                case "4. Ajedrez":
                    Tablero = new Ajedrez();
                    break;
                case "5. Salir":
                    app=false; ej=false; action=false;
                    break;
            }

            while (ej){
                action = true;
                OptionEj = MenuEj(Option);
                switch (OptionEj){
                    case "Mostrar Tablero":
                        Tablero.Show();
                        break;
                    case "Ingrese Posición del Caballo":
                        Tablero.InputHourse();
                        break;
                    case "Volver":
                        ej = false; action = false;
                        break;
                }
            }
        }
    }

    public static String Menu() {
        String[] options = { "1. ArrayList Simple", "2. ArrayList Objetos", "3. ArrayList de ArrayList", "4. Ajedrez" ,"5. Salir" };
        String option = (String) JOptionPane.showInputDialog(null, "Seleccionar un Ejercicio: ", "Menu de Ejercicios: ",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return option;

    }

    public static String MenuEj(String Opt) {
        String[] options = {};
        switch (Opt){
            case "1. ArrayList Simple":
                options = new String[]{"1. ArrayList Simple", "2. ArrayList Objetos", "3. ArrayList de ArrayList", "4. Ajedrez", "5. Salir"};
                break;
            case "2. ArrayList Objetos":
                options = new String[]{"1. ArrayList Simple", "2. ArrayList Objetos", "3. ArrayList de ArrayList", "4. Ajedrez", "5. Salir"};
                break;
            case "3. ArrayList de ArrayList":
                options = new String[]{"1. ArrayList Simple", "2. ArrayList Objetos", "3. ArrayList de ArrayList", "4. Ajedrez", "5. Salir"};
                break;
            case "4. Ajedrez":
                options = new String[]{"Mostrar Tablero", "Ingrese Posición del Caballo", "Volver",};
                break;
        }

        String option = (String) JOptionPane.showInputDialog(null, "Seleccionar un Ejercicio: ", "Menu de Ejercicios: ",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return option;

    }


    public static void EjercicioNumeros(){
        ListNumeros Nums = new ListNumeros("Resultados clima");
        Nums.ReadData();
        Nums.Show();
        Nums.AllSize();
        Nums.Prom();
    }

    public static void EjercicioFutbol(){
        Partidos PartidosEU = new Partidos("Liga Europea");
        PartidosEU.ReadData();
        PartidosEU.Show();
        PartidosEU.Input();
        PartidosEU.Show();
        PartidosEU.WriteData();
    }

    public static void EjercicioAjedrez(){
        Ajedrez A = new Ajedrez();
        A.InputHourse();
    }

    public static void EjercicioListas(){
        Listas Lista = new Listas("A");
        Lista.Fill();
        Lista.Show();
        Lista.AddElem();
        Lista.Show();
        Lista.DelPos();
        Lista.Show();
        Lista.RandomFill();
        Lista.Show();
        Lista.DelElem();
        Lista.Show();
        Lista.Truncate();
        Lista.Show();
    }

}