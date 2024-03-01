package logic;

import bean.Numeros;
import bean.Partido;

import java.util.ArrayList;

public class ListNumeros {
    private ArrayList<Numeros> ListaNumeros;
    public String Name;

    public ListNumeros(String name) {
        ListaNumeros = new ArrayList<Numeros>();
        this.Name = name;
    }
}
