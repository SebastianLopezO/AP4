package logic;

import java.util.ArrayList;

public class NodosDos {
    ArrayList<ArrayList<Integer>> an;

    public NodosDos() {
        this.an = new ArrayList<ArrayList<Integer>>();
    }

    public void NewNodo(ArrayList<Integer> numeros) { NewNodo(false, numeros); }
    public void NewNodo() { NewNodo(true, null); }
    public void NewNodo(boolean automatico, ArrayList<Integer> numeros) {
        if(automatico && an.size() == 0) an.add(NewList(3));
        else if (automatico) an.add(NewList( NextPrimo(an.get(an.size()-1).size()) ));
        else an.add(numeros);
    }


    private ArrayList<Integer> NewList(int e) {
        ArrayList<Integer> nuevo = new ArrayList<>();

        int primero = 1000;
        int x;
        for (int i = 0; i < e; i++) {
            do {
                x = RandomNumber();
            } while (x > primero);

            if (nuevo.isEmpty()) { // si es el primero
                x += e+5; // seguro anti-bucleinfinito
                primero = x;
                nuevo.add(x);
            } else nuevo.add(x);
        }
        return nuevo;
    }


    public int NextPrimo(int actualSize) {
        // buscar siguiente primo();
        int sp = 0;
        for (sp = actualSize+1; sp <= actualSize + 20; sp++) {
            if (isPrimo(sp)) break;
        }
        System.out.println("Siguiente primo (punto 2) -> "+sp);
        return sp;
    }
    private boolean isPrimo(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private int RandomNumber() {
        int num = (int) (Math.random() * 100);;
        return num;
    }

    public String NumbersMax(){
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

