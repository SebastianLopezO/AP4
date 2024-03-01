package logic;

import bean.Partido;

import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

import static utility.InputPane.GetDato;
import static utility.InputPane.GetNum;

public class Partidos {
    private ArrayList<Partido> ListaPartidos;
    public String Name;

    public Partidos(String name) {
        ListaPartidos = new ArrayList<Partido>();
        this.Name = name;
    }

    public void DelNotDraw() {
        for (Partido partido : ListaPartidos) {
            if (partido.getGoalsLocal() != partido.getGoalsVisit()) {
                ListaPartidos.remove(partido);
            }
        }
    }

    public void WinLocal() {
        ArrayList<Partido> Duplicate = new ArrayList<>(ListaPartidos);
        for (Partido partido : ListaPartidos) {
            if (partido.getGoalsLocal() <= partido.getGoalsVisit()) {
                ListaPartidos.remove(partido);
            }
        }
        Show();
        ListaPartidos = Duplicate;
    }

    public void WinVisit() {
        ArrayList<Partido> Duplicate = new ArrayList<>(ListaPartidos);
        for (Partido partido : ListaPartidos) {
            if (partido.getGoalsLocal() >= partido.getGoalsVisit()) {
                ListaPartidos.remove(partido);
            }
        }
        Show();
        ListaPartidos = Duplicate;
    }

    public void Draw() {
        ArrayList<Partido> Duplicate = new ArrayList<>(ListaPartidos);
        for (Partido partido : ListaPartidos) {
            if (partido.getGoalsLocal() != partido.getGoalsVisit()) {
                ListaPartidos.remove(partido);
            }
        }
        Show();
        ListaPartidos = Duplicate;
    }

    public void Rout() {
        ArrayList<Partido> Duplicate = new ArrayList<>(ListaPartidos);
        for (Partido partido : ListaPartidos) {
            if (Math.abs(partido.getGoalsLocal() - partido.getGoalsVisit()) < 3) {
                ListaPartidos.remove(partido);
            }
        }
        Show();
        ListaPartidos = Duplicate;
    }


    public void Show() {
        if (!ListaPartidos.isEmpty()) {
            ShowConsole();
            ShowPane();
        } else {
            System.out.println("La lista de partidos de " + this.Name + " esta vacia");
            JOptionPane.showMessageDialog(null, "La lista de partidos de " + this.Name + " esta vacia", "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ShowConsole() {
        System.out.printf("%-25s %-15s %-10s %-25s %-15s%n", "Equipo Local", "Goles Locales", "Vs", "Equipo Visitante", "Goles Visitante");
        for (Partido partido : ListaPartidos) {
            System.out.printf("%-25s %-15d %-10s %-25s %-15d%n", partido.getTeamLocal(), partido.getGoalsLocal(), "vs", partido.getTeamVisit(), partido.getGoalsVisit());
        }
    }

    public void ShowPane() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body><table border='1'>");
        html.append("<tr><th>Equipo Local</th><th>Goles Locales</th><th>vs</th><th>Equipo Visitante</th><th>Goles Visitante</th></tr>");

        for (Partido partido : ListaPartidos) {
            html.append("<tr>");
            html.append("<td>").append(partido.getTeamLocal()).append("</td>");
            html.append("<td>").append(partido.getGoalsLocal()).append("</td>");
            html.append("<td>vs</td>");
            html.append("<td>").append(partido.getTeamVisit()).append("</td>");
            html.append("<td>").append(partido.getGoalsVisit()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table></body></html>");

        JOptionPane.showMessageDialog(null, html.toString(), "Tabla de Partidos de" + this.Name, JOptionPane.INFORMATION_MESSAGE);
    }

    public void Input(){
        String equipoLocal = GetDato("nombre de equipo", " el equipo local");
        String equipoVisitante = GetDato("nombre de equipo", "el equipo visitante");
        int golesLocal = GetNum("los goles locales");
        int golesVisitante = GetNum("los goles visitantes");

        ListaPartidos.add(new Partido(equipoLocal, equipoVisitante, golesLocal, golesVisitante));
    }

    public void ReadData() {
        ListaPartidos.clear();
        String root = System.getProperty("user.dir");
        try {
            FileReader File = new FileReader(root + "\\src\\data\\Partidos.txt");
            BufferedReader Br = new BufferedReader(File);

            String line = Br.readLine();
            while (line != null) {
                String[] parts = line.split("::");
                ListaPartidos.add(new Partido(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
                line = Br.readLine();
            }
            Br.close();
        } catch (IOException e) {
            System.out.print("Hubo un error: " + e);
        }
    }

    public void WriteData() {
        String root = System.getProperty("user.dir");
        try {
            FileWriter fileWriter = new FileWriter(root + "\\src\\data\\Partidos.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Partido partido : ListaPartidos) {
                bufferedWriter.write(partido.getTeamLocal() + "::" + partido.getTeamVisit() + "::" + partido.getGoalsLocal() + "::" + partido.getGoalsVisit());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            System.out.print("Hubo un error: " + e);
        }
    }


}
