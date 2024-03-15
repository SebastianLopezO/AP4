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
        Listas Lista = null;
        while (app) {
            ej = true;
            Option = Menu();
            switch (Option){
                case "ArrayList Simple":
                    Lista = new Listas(GetDato("Nombre","el nombre de la lista"));
                    break;
                case "ArrayList Objetos":
                    break;
                case "ArrayList de ArrayList":
                    break;
                case "Ajedrez":
                    Tablero = new Ajedrez();
                    break;
                case "Salir":
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
        String[] options = { "ArrayList Simple", "ArrayList Objetos", "ArrayList de ArrayList", "Ajedrez" ,"Salir" };
        String option = (String) JOptionPane.showInputDialog(null, "Seleccionar un Ejercicio: ", "Menu de Ejercicios: ",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return option;

    }

    public static String MenuEj(String Opt) {
        String[] options = {};
        switch (Opt){
            case "ArrayList Simple":
                options = new String[]{"Llenar el ArrayList", "Adicionar nuevo elemento en posición específica", "Listar el ArrayList", "Borrar elemento de una posición específica", "Borrar un elemento específico", "Volver"};
                break;
            case "ArrayList Objetos":
                options = new String[]{"Leer archivo plano original", "Mostrarlo en pantalla", "Eliminar partidos que resultado no es empate y mostrar arrayList", "Partidos de futbol donde el ganador fue el equipo local", "Partidos de futbol donde el ganador fue el equipo visitante", "Partidos donde hubo empate", "Partidos donde hubo goleada (diferencia de goles mayor o igual a tres)", "Ingresar información de cada partido y su resultado", "Leer nuevamente el archivo plano inicial y con los adicionados", "Volver"};
                break;
            case "ArrayList de ArrayList":
                options = new String[] {"Cargar el ArrayList-ArrayList", "Eliminar un entero dado", "Mostrar el ArrayList", "Borrar ArrayList vacíos", "Calcular tamaño de cada arrayList", "Adicionar Datos", "Realizar la suma y el promedio de cada fila (ArrayList)", "Poderle adicionar un(s) elementos en una fila(ArrayList) determinado", "Diseñe un ejemplo donde se utilice una ArrayList de ArrayList y dentro de este último un objeto con varios atributos","Volver"};
                break;
            case "Ajedrez":
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