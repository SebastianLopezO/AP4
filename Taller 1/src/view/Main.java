package view;
import bean.Ajedrez;
import bean.ListNumeros;
import bean.Listas;
import logic.Partidos;

public class Main {
    public static void main(String[] args) {
        EjercicioFutbol();
    }

    public static void EjercicioNumeros(){
        ListNumeros Nums = new ListNumeros("Resultados clima");
        Nums.ReadData();
        Nums.Show();
        Nums.AllSize();
        Nums.Prom();
    }

    public static void EjercicioFutbol(){
        Partidos PartidosEU = new Partidos("Liga Europea");
        PartidosEU.ReadData();
        PartidosEU.Show();

        PartidosEU.Rout(); //Goleada
        PartidosEU.WinVisit();
        PartidosEU.WinLocal();
        PartidosEU.DelNotDraw();
    }

    public static void EjercicioAjedrez(){
        Ajedrez A = new Ajedrez();
        A.InputHourse();
    }

    public static void EjercicioListas(){
        Listas Lista = new Listas("A");
        Lista.Fill();
        Lista.Show();
        Lista.AddElem();
        Lista.Show();
        Lista.DelPos();
        Lista.Show();
        Lista.RandomFill();
        Lista.Show();
        Lista.DelElem();
        Lista.Show();
        Lista.Truncate();
        Lista.Show();
    }

}