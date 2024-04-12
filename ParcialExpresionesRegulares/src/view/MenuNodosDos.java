package view;

import bean.Menu;
import logic.NodosDos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

public class MenuNodosDos extends Menu {
    public MenuNodosDos(String title) {
        super(title);
    }

    public void menu() {
        NodosDos nodosDos = new NodosDos();
        while (true) {
            String[] Options = {
                    "Añadir nuevo nodo manualmente",
                    "Añadir nuevo nodo automaticamente",
                    "Mostrar nodos",
                    "El numero mayor de cada nodo",
                    "Volver"
            };

            String opt;
            try {
                opt = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la opción para el Nodo: ",
                        "NodosDos",
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

                case "Añadir nuevo nodo manualmente":
                    int menor;
                    try {
                        menor = Integer.parseInt(input("Ingrese el primer numero.").trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }

                    int elementos;
                    if (!nodosDos.getAn().isEmpty()) {
                        elementos = nodosDos.NextPrimo(nodosDos.getAn().get(nodosDos.getAn().size() - 1).size()) - 1;
                    } else
                        elementos = 2;

                    String numeros = input(
                            "Ingrese " + elementos + " numeros menores que el anterior (separados por un espacio).")
                            .trim();

                    Pattern patron = Pattern.compile("-?\\d+(?:\\s+-?\\d+){" + (elementos - 1) + "}");
                    if (patron.matcher(numeros).matches()) {
                        System.out.println(numeros);
                        int[] numerosVec = Arrays.stream(numeros.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                        System.out.println(Arrays.toString(numerosVec));

                        int max = Integer.MIN_VALUE;
                        for (int i = 0; i < numerosVec.length; i++) {
                            if (max < numerosVec[i])
                                max = numerosVec[i];
                        }

                        if (menor > max) {
                            msg("Añadiendo!");
                            ArrayList<Integer> arrayList = new ArrayList<>();
                            arrayList.add(menor);
                            for (int num : numerosVec) {
                                arrayList.add(num);
                            }
                            System.out.println(arrayList);
                            nodosDos.NewNodo(arrayList);
                        } else {
                            msg("Solo numeros menores que el primero!");
                        }
                    } else
                        msg("El patron no concuerda!");
                    break;

                case "Añadir nuevo nodo automaticamente": // crear automaticamente
                    nodosDos.NewNodo();
                    break;

                case "Mostrar nodos":
                    ArrayList<ArrayList<Integer>> an = nodosDos.getAn();
                    if (!an.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        Iterator<ArrayList<Integer>> anI = an.iterator();
                        while (anI.hasNext()) {
                            ArrayList<Integer> arrayListInterno = anI.next();
                            Iterator<Integer> anInternoIterador = arrayListInterno.iterator();
                            sb.append("Nodo -> Tamaño: " + arrayListInterno.size() + "\n");
                            while (anInternoIterador.hasNext()) {
                                sb.append(anInternoIterador.next() + (anInternoIterador.hasNext() ? " , " : "\n\n"));
                            }
                        }
                        msgScroll(sb.toString());
                    } else
                        msg("El ArrayList esta vacio.");
                    break;

                case "El numero mayor de cada nodo":
                    an = nodosDos.getAn();
                    if (!an.isEmpty())
                        msgScroll(nodosDos.NumbersMax());
                    else
                        msg("El ArrayList esta vacio.");
                    break;

                default:
                    msg("opcion invalida.");
                    break;
            }

        }
    }
}
