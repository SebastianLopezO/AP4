package view;

import logic.Vendedores;

public class Main {
    public static void main(String[] args) {

        Vendedores ListaVendedores = new Vendedores("PanPan");
        ListaVendedores.ReadData();
        ListaVendedores.ShowConsole();
        ListaVendedores.ShowPane();
        ListaVendedores.perEPS("Sura");
        ListaVendedores.PerAnosExp();
    }
}