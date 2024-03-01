package bean;

import javax.swing.*;
import java.util.ArrayList;

import static utility.InputPane.*;

public class Listas {
    private ArrayList<String> list;
    public String Name;

    public Listas(String name){
        this.list = new ArrayList<String>();
        this.Name = name;
    }

    public void Fill(){
        list.clear();
        for (int i=1; i<=10; i++){
            list.add("Elemento#"+i);
        }
    }

    public void RandomFill(){
        list.clear();
        for (int i=1; i<=10; i++){
            list.add("Elemento#"+(int)(Math.random()*10+1));
        }
    }

    public void AddElem(){
        int pos = GetNum("la posición para el elemento a insertar");
        if(pos>=0 && pos<= list.size()){
            String elem = GetDato("Elemento","añadir a la lista");
            list.add(pos,elem);
        }else{
            System.out.println("Debes ingresar una posición valida");
            JOptionPane.showMessageDialog(null, "Debes ingresar una posición valida", "Validación", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void Show(){
        String msj ="List"+this.Name+":{ ";
        for (String elem : list) {
            msj += elem + " / ";
        }
        msj +="}";
        System.out.print(msj);
        JOptionPane.showMessageDialog(null, msj, "Elementos de la Lista"+this.Name, JOptionPane.INFORMATION_MESSAGE);
    }

    public void DelPos(){
        int pos = GetNum("la posición a eliminar");
        if (pos >= 0 && pos < list.size()) {
            list.remove(pos);
            JOptionPane.showMessageDialog(null, "Elemento eliminado en la posición " + pos);
        } else {
            JOptionPane.showMessageDialog(null, "Posición inválida");
        }
    }

    public void DelElem(){
        String elem = GetOption(list,"Eliminar elemento de la lista "+this.Name);
        if (list.contains(elem)) {
            list.removeAll(java.util.Collections.singleton(elem));
            JOptionPane.showMessageDialog(null, "El Elemento " + elem + " fue eliminado");
        } else {
            JOptionPane.showMessageDialog(null, "El Elemento " + elem + " no fue encontrado en la lista");
        }
    }

    public void Truncate(){
        list.clear();
    }
}
