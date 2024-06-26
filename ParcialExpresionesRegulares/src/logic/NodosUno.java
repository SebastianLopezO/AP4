package logic;

import bean.Numeros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import static utility.Clr.*;

public class NodosUno {
    ArrayList<Numeros> n;

    public NodosUno() {
        n = new ArrayList<>();
    }


    // metodos
    public void NextNodo() {

        if (n.isEmpty()) {
            n.add(NewNodo(1));
            n.add(NewNodo(2));
            return;
        }

        // buscar siguiente primo
        int sp = NextPrimo();

        // Insertar siguiente primo
        int diff = sp - n.size();
        for (int i = 0; i < diff; i++) {
            n.add(NewNodo(n.size() + 1));
        }
    }

    public int NextPrimo() {
        // buscar siguiente primo
        int sp = 0;
        for (sp = n.size() + 1; sp <= n.size() + 20; sp++) {
            if (isPrimo(sp))
                break;
        }
        System.out.println(BG_B + "Siguiente primo (punto 2) -> " + sp + RT);
        return sp;
    }

    private boolean isPrimo(int n) {
        //Criba de Eratóstenes
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int limit = (int) Math.sqrt(n) + 1;
        for (int i = 5; i < limit; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public Numeros NewNodo(int digitos) {
        int x;
        while (true) {
            String cadena = "";
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    do {
                        x = RandomNumber(digitos);
                    } while (!(x <= ((Math.pow(10, digitos)) - 3)));
                } else {

                    x = RandomNumber(digitos);

                }

                cadena += x + " ";
            }

            // verificar cadena.
            if (ValidateString(cadena, digitos))
                return new Numeros(Arrays.stream(cadena.split(" ")).mapToInt(Integer::parseInt).toArray());
        }
    }

    public String Average() {
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
    public boolean ValidateString(String cadena, int digitos) {
        Pattern p = Pattern.compile("\\d{" + digitos + "} \\d{" + digitos + "} \\d{" + digitos + "} ");
        if (p.matcher(cadena).matches()) {
            int[] nms = Arrays.stream(cadena.split(" ")).mapToInt(Integer::parseInt).toArray();
            if (nms[0] < nms[1] && nms[1] < nms[2]) {
                System.out.println(BG_G + "[!] Cadena Valida: " + Arrays.toString(nms) + RT);
                return true;
            }
            System.out.println(BG_R+"[x] Cadena Invalida: " + Arrays.toString(nms) + RT);
        }
        return false;
    }

    public int RandomNumber(int digitos) {
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
