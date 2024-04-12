package logic;

import java.util.ArrayList;

public class NodosDos {
    ArrayList<ArrayList<Integer>> an;

    public NodosDos() {
        this.an = new ArrayList<ArrayList<Integer>>();
    }

    public void crearNodo(ArrayList<Integer> numeros) { crearNodo(false, numeros); }
    public void crearNodo() { crearNodo(true, null); }
    public void crearNodo(boolean automatico, ArrayList<Integer> numeros) {
        if(automatico && an.size() == 0) an.add(crearArrayList(3));
        else if (automatico) an.add(crearArrayList( siguientePrimo(an.get(an.size()-1).size()) ));
        else an.add(numeros);
    }


    private ArrayList<Integer> crearArrayList(int e) {
        ArrayList<Integer> nuevo = new ArrayList<>();

        int primero = 1000;
        int x;
        for (int i = 0; i < e; i++) {
            do {
                x = numeroAleatorio();
            } while (x > primero);

            if (nuevo.isEmpty()) { // si es el primero
                x += e+5; // seguro anti-bucleinfinito
                primero = x;
                nuevo.add(x);
            } else nuevo.add(x);
        }
        return nuevo;
    }


    public int siguientePrimo(int actualSize) {
        // buscar siguiente primo();
        int sp = 0;
        for (sp = actualSize+1; sp <= actualSize + 20; sp++) {
            if (esPrimo(sp)) break;
        }
        System.out.println("Siguiente primo (punto 2) -> "+sp);
        return sp;
    }
    private boolean esPrimo(int n) { // se podria reducir el rango de division hasta la mita del nuemero
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private int numeroAleatorio() {
        int num = (int) (Math.random() * 100);;
        // System.out.println(num);
        return num;
    }

    public String NumerosMayores(){ // busca los numeros mayores de cada nodo
        String s="";
        for (ArrayList<Integer> arrayList : an) {
            int max = Integer.MIN_VALUE, i = 1;
            for (Integer integer : arrayList) {
                if(integer > max) max = integer;
            }
            s += "Nodo "+i+": "+max+"\n";
            i++;
        }
        return s;
    }

    // getters and setters
    public ArrayList<ArrayList<Integer>> getAn() {
        return an;
    }

    public void setAn(ArrayList<ArrayList<Integer>> an) {
        this.an = an;
    }
}

