package view;
import bean.Ajedrez;
import bean.Listas;
import logic.Partidos;

public class Main {
    public static void main(String[] args) {
        Partidos PartidosEU = new Partidos("Liga Europea");
        PartidosEU.ReadData();
        PartidosEU.Show();
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