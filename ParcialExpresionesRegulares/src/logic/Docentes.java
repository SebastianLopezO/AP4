package logic;

import bean.Docente;
import utility.FileManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Docentes {

    private ArrayList<Docente> ListTeachers;

    public Docentes() {
        ListTeachers = new ArrayList<>();
    }

    public Docentes(ArrayList<Docente> ListTeachers) {
        this.ListTeachers = ListTeachers;
    }

    public void LoadTeachers() {
        try {
            String root = System.getProperty("user.dir");
            FileManager fm = new FileManager(root + "/src/Data/Docentes.txt");
            ArrayList<String> lineas = fm.readFileArrayList();
            Iterator<String> i = lineas.iterator();
            while (i.hasNext()) {
                String[] d = i.next().split(";");
                ListTeachers.add(new Docente(d[0], d[1], d[2],d[3], Integer.parseInt(d[4])));
                System.out.println("Se ha añadido un docente con la información -> " + Arrays.stream(d).toList());
            }
        } catch (Exception e) {
            System.out.println("Hubo un error al leer el archivo de Docentes: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hubo un error al leer el archivo de Docentes: " + e.getMessage());
        }
    }

    public void AddTeacher(Docente d) {
        ListTeachers.add(d);
    }

    public double PercentageType(String tipo) {
        if (ListTeachers.isEmpty()) return 0;

        double porcentaje = 0;
        Iterator<Docente> i = ListTeachers.iterator();
        while (i.hasNext()) {
            porcentaje += i.next().getType().equals(tipo) ? 1 : 0;
        }
        return (porcentaje / ListTeachers.size()) * 100;
    }

    public ArrayList<Docente> TeachersForMonth(String Month, String Type) {
        ArrayList<Docente> docentesMes = new ArrayList<>();
        Iterator<Docente> i = ListTeachers.iterator();
        while (i.hasNext()) {
            Docente d = i.next();
            if (d.getDate().split("-")[1].equals(Month) && d.getType().equals(Type))
                docentesMes.add(d);
        }

        return docentesMes;
    }

    public boolean UniqueID(String Id) {
        Iterator<Docente> i = ListTeachers.iterator();
        while (i.hasNext()) {
            if (i.next().getId().equals(Id)) return false;
        }
        return true;
    }

    public int Attempts() {
        if (ListTeachers.isEmpty()) return 0;

        int intentos = 0;
        Iterator<Docente> i = ListTeachers.iterator();
        while (i.hasNext()) {
            intentos += i.next().getAttempts();
        }
        return intentos;
    }

    public ArrayList<Docente> getListTeachers() {
        return ListTeachers;
    }

    public void setListTeachers(ArrayList<Docente> listTeachers) {
        this.ListTeachers = listTeachers;
    }
}
