package view;


import logic.Mensajeros;
import logic.Panaderos;
import logic.Vendedores;
import utility.Html;

import javax.management.ObjectName;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        boolean app=true, action=false;
        String Option="", OptionAct="";

        Mensajeros mensajeros = null;
        Vendedores vendedores = null;
        Panaderos panaderos = null;

        while (app){
            action = true;
            Option = MenuTrab();
            switch (Option){
                case "Panaderos":
                    panaderos = new Panaderos("PanPan");
                    break;
                case "Vendedores":
                    vendedores = new Vendedores("PanPan");
                    break;
                case "Mensajeros":
                    mensajeros = new Mensajeros("PanPan");
                    break;
                case "Resumen":
                    break;
                case "Salir":
                    System.out.println("Hasta luego, vuelva pronto");
                    Export(vendedores, mensajeros, panaderos);
                    app = false;
                    action = false;
                    break;
            }

            while (action){
                OptionAct = MenuAct(Option);

                switch (OptionAct){
                    case "Cargar Panaderos":
                        panaderos.ReadData();
                        break;
                    case "Insertar Panadero":
                        panaderos.Input();
                        break;
                    case "Mostrar Panaderos":
                        panaderos.Show();
                        break;
                    case "Buscar Panadero":
                        panaderos.SearchPan();
                        break;
                    case "Porcentaje Panaderos con 2 y 5 años de experiencia":
                        panaderos.PerAnosExp();
                        break;
                    case "Guardar Panaderos":
                        panaderos.WriteData();
                        break;
                    case "Cargar Vendedores":
                        vendedores.ReadData();
                        break;
                    case "Insertar Vendedor":
                        vendedores.Input();
                        break;
                    case "Mostrar Vendedores":
                        vendedores.Show();
                        break;
                    case "Buscar Vendedor":
                        vendedores.SearchVen();
                        break;
                    case "Porcentaje Vendedores con 2 y 5 años de experiencia":
                        vendedores.PerAnosExp();
                        break;
                    case "Porcentaje Vendedores con EPS Sura":
                        vendedores.perEPS("Sura");
                        break;
                    case "Guardar Vendedores":
                        vendedores.WriteData();
                        break;
                    case "Cargar Mensajeros":
                        mensajeros.ReadData();
                        break;
                    case "Insertar Mensajero":
                        mensajeros.Input();
                        break;
                    case "Mostrar Mensajeros":
                        mensajeros.Show();
                        break;
                    case "Buscar Mensajero":
                        mensajeros.SearchMsj();
                        break;
                    case "Porcentaje Mensajeros con Pension Porvenir":
                        mensajeros.perPen("Porvenir");
                        break;
                    case "Porcentaje Mensajeros con ARL Colseguros":
                        mensajeros.perARL("Protección");
                        break;
                    case "Porcentaje Mensajeros con EPS Sura":
                        mensajeros.perEPS("Sura");
                        break;
                    case "Guardar Mensajeros":
                        mensajeros.WriteData();
                        break;
                    case "Volver":
                        action=false;
                        break;
                }
            }
        }
    }

    public static String MenuTrab() {

        String[] Options = {
                "Panaderos",
                "Vendedores",
                "Mensajeros",
                "Resumen",
                "Salir"
        };

        String Option = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el tipo de trabajador: ",
                "Trabajadores",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);
        return Option;
    }

    public static String MenuAct(String Opt) {
        String[] options = {};
        switch (Opt){
            case "Panaderos":
                options = new String[]{"Cargar Panaderos", "Insertar Panadero", "Mostrar Panaderos", "Buscar Panadero",  "Porcentaje Panaderos con 2 y 5 años de experiencia", "Guardar Panaderos", "Volver"};
                break;
            case "Vendedores":
                options = new String[]{"Cargar Vendedores", "Insertar Vendedor", "Mostrar Vendedores", "Buscar Vendedor",  "Porcentaje Vendedores con 2 y 5 años de experiencia", "Porcentaje Vendedores con EPS Sura" ,"Guardar Vendedores", "Volver"};
                break;
            case "Mensajeros":
                options = new String[] {"Cargar Mensajeros", "Insertar Mensajero", "Mostrar Mensajeros", "Buscar Mensajero",  "Porcentaje Mensajeros con ARL Colseguros", "Porcentaje Mensajeros con Pension Porvenir", "Porcentaje Mensajeros con EPS Sura" ,"Guardar Mensajeros", "Volver"};
                break;
            case "Resumen":
                options = new String[]{"Volver",};
                break;
        }

        String option = (String) JOptionPane.showInputDialog(null, "Seleccionar un Ejercicio: ", "Menu de Ejercicios: ",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return option;

    }

    public static void Export(Vendedores vendedores, Mensajeros mensajeros, Panaderos panaderos) {
        //Archivo
        Html FileProyect = new Html("Trabajadores","PanPan");
        FileProyect.AddBody(vendedores.ExportWorkerHtml());
        FileProyect.AddBody(mensajeros.ExportWorkerHtml());
        FileProyect.AddBody(panaderos.ExportWorkerHtml());
        FileProyect.Export("Memory");
    }
}