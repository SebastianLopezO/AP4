package logic;

import bean.Nodo;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class NodosCuatro {
    private ArrayList<Nodo> Array;

    public NodosCuatro() { // constructor
        Array = new ArrayList<Nodo>();
    }

    public ArrayList<String> NumerosIgualesSeguidos(){ // Buscar dos numeros iguales en cualquier parte del string de entrada
        ArrayList<String> codigos = new ArrayList<String>();
        Pattern patron = Pattern.compile("00|11|22|33|44|55|66|77|88|99");
        for (Nodo nodo : Array) { // recorro todo el Array buscando quien cumple con el patron
            if(patron.matcher(nodo.getCodigo()).find()){
                codigos.add(nodo.getCodigo());
            }
        }
        return codigos;
    }

    public String Mostrar(){ // devuelve un string que contiene todos los datos del Array
        String s = "";
        for (Nodo nodo : Array) {
            int i = 1;
            s += "Nodo "+i+": \n  Dato1: "+ nodo.getDato1()+"\n  Dato2: "+ nodo.getDato2()+"\n  Codigo: "+ nodo.getCodigo()+"\n\n";
        }
        return s;
    }

    public ArrayList<Nodo> getArray() {
        return Array;
    }

    public void setArray(ArrayList<Nodo> array) {
        Array = array;
    }


}
