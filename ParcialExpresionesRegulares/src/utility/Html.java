package utility;


import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Html {
    public String Head;
    public String Body;

    public Html(String Type, String Name) {
        //Head Estandar
        this.Head = "<head><title>Lista de "+Type+" de "+Name+"</title><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"></head>";
        this.Body = "<body></body>";
    }

    public void AddBody(String html) {
        this.Body += html;
    }

    public void Export(String FileName) {
        try {
            // Crear archivo HTML
            File archivo = new File("src/view/frame/" + FileName + ".html");
            archivo.createNewFile();

            // Escribir código HTML en archivo
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.Head + this.Body);
            bw.close();

            // Abrir el archivo HTML en el navegador predeterminado
            Desktop.getDesktop().browse(archivo.toURI());
        } catch (IOException e) {
            System.out.println("Error al Exportar resultado: " + e);
        }
    }
}
