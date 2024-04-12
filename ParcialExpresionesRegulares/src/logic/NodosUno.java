package logic;

import bean.Numeros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class NodosUno {
    ArrayList<Numeros> n;

    public NodosUno() {
        n = new ArrayList<>();
    }


    // metodos
    public void siguientesNodos() {

        if (n.isEmpty()) {
            n.add(crearNodo(1));
            n.add(crearNodo(2));
            return;
        }

        // buscar siguiente primo();
        int sp = siguientePrimo();

        // añadir el siguiente primo
        int diff = sp - n.size();
        for (int i = 0; i < diff; i++) {
            n.add(crearNodo(n.size() + 1));
        }
    }

    public int siguientePrimo() {
        // buscar siguiente primo();
        int sp = 0;
        for (sp = n.size() + 1; sp <= n.size() + 20; sp++) {
            if (esPrimo(sp))
                break;
        }
        System.out.println("Siguiente primo (punto 2) -> " + sp);
        return sp;
    }

    private boolean esPrimo(int n) { // se podria reducir el rango de division hasta la mita del nuemero
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public Numeros crearNodo(int digitos) {
        int x;
        while (true) {
            // crear cadena
            String cadena = "";
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    do {
                        x = numeroAleatorio(digitos);
                    } while (!(x <= ((Math.pow(10, digitos)) - 3)));
                } else {

                    x = numeroAleatorio(digitos);

                }

                cadena += x + " ";
            }

            // verificar cadena.
            if (verificarCadena(cadena, digitos))
                return new Numeros(Arrays.stream(cadena.split(" ")).mapToInt(Integer::parseInt).toArray());
        }
    }

    public String promedios() {
        String s = "";
        int indexNodosizquierda = 0;
        int indexNodosderecha = n.size() - 1;
        while (indexNodosizquierda <= indexNodosderecha) {
            if (indexNodosizquierda != indexNodosderecha) {
                s += "Nodo " + (indexNodosizquierda + 1) + ": " + (((float) (n.get(indexNodosizquierda).getN1()
                        + n.get(indexNodosizquierda).getN2() + n.get(indexNodosizquierda).getN3())) / 3) + "\n\n";
                s += "Nodo " + (indexNodosderecha + 1) + ": " + (((float) (n.get(indexNodosderecha).getN1()
                        + n.get(indexNodosderecha).getN2() + n.get(indexNodosderecha).getN3())) / 3) + "\n\n";
            } else
                s += "Nodo " + (indexNodosderecha + 1) + ": " + (((float) (n.get(indexNodosderecha).getN1()
                        + n.get(indexNodosderecha).getN2() + n.get(indexNodosderecha).getN3())) / 3) + "\n\n";
            indexNodosizquierda++;
            indexNodosderecha--;
        }
        return s;
    }

    // verificacion
    public boolean verificarCadena(String cadena, int digitos) {
        Pattern p = Pattern.compile("\\d{" + digitos + "} \\d{" + digitos + "} \\d{" + digitos + "} ");
        if (p.matcher(cadena).matches()) {
            int[] nms = Arrays.stream(cadena.split(" ")).mapToInt(Integer::parseInt).toArray();
            if (nms[0] < nms[1] && nms[1] < nms[2]) {
                System.out.println("[!] Cadena valida: " + Arrays.toString(nms));
                return true;
            }
            System.out.println("[x] Cadena invalida: " + Arrays.toString(nms));
        }
        return false;
    }

    // UTILIDAD
    public int numeroAleatorio(int digitos) {
        int num = (int) Math.floor(Math.random() * (Math.pow(10, digitos)));
        // System.out.println(num);
        return num;
    }

    // Getters and Setters
    public ArrayList<Numeros> getArrayList() {
        return n;
    }

    public void setArrayList(ArrayList<Numeros> n) {
        this.n = n;
    }
}
