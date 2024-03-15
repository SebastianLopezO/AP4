package logic;

import bean.Vendedor;

import java.util.ArrayList;

public class Vendedores {
    private ArrayList<Vendedor> ListaVendedores;
    public String Name;

    public Vendedores(String Name){
        ListaVendedores = new ArrayList<Vendedor>();
        this.Name = Name;
    }


}
