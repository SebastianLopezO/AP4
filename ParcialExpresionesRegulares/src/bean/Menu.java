package bean;

import javax.swing.*;
import java.awt.*;

import static utility.Clr.*;

public abstract class Menu  {

    private String title;

    public Menu(String title) {
        this.title = title;
    }

    abstract public void menu();

    public void msg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "title", 1);
    }

    public String input(String msg) {
        return JOptionPane.showInputDialog(null, msg, title, 3) ;
    }

    public void msgHtml(String msg, int width, int height){
        JEditorPane editorPane = new JEditorPane("text/html", msg);
        editorPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setPreferredSize(new Dimension( width,height));

        JOptionPane.showMessageDialog(null, scrollPane, "Lista de " + this.title, JOptionPane.INFORMATION_MESSAGE);

    }
    public void msgScroll(String msg) {
        JTextArea textArea = new JTextArea(msg);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, title, 1);
    }



}
