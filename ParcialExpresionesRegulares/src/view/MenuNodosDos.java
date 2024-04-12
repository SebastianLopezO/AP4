package view;

import bean.Menu;
import logic.NodosDos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

public class MenuNodosDos extends Menu {
    public MenuNodosDos(String title) {
        super(title);
    }

    public void menu() {
        NodosDos p2 = new NodosDos();
        while (true) {
            int opt;
            try {
                opt = Integer.parseInt(input(
                        "Menu: \n1. Añadir nuevo nodo manualmente \n2. Añadir nuevo nodo automaticamente \n3. Mostrar nodos \n4. El numero mayor de cada nodo \n 0. Salir "));
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

                case 1: // crear Nuevo Nodo Manualmente
                    int menor;
                    try {
                        menor = Integer.parseInt(input("Ingrese el primer numero.").trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }

                    int elementos;
                    if (!p2.getAn().isEmpty()) {
                        elementos = p2.siguientePrimo(p2.getAn().get(p2.getAn().size() - 1).size()) - 1;
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
                            p2.crearNodo(arrayList);
                        } else {
                            msg("Solo numeros menores que el primero!");
                        }
                    } else
                        msg("El patron no concuerda!");
                    break;

                case 2: // crear automaticamente
                    p2.crearNodo();
                    break;

                case 3: // mostrar
                    ArrayList<ArrayList<Integer>> an = p2.getAn();
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

                case 4: // mostrar los numeros mayores de cada nodo
                    an = p2.getAn();
                    if (!an.isEmpty())
                        msgScroll(p2.NumerosMayores());
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
