package utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    File f;

    public FileManager(String path) throws IOException {
        f = new File(path);
    }

    public ArrayList<String> readFileArrayList() {
        Scanner s = null;
        ArrayList<String> lineas = new ArrayList<>();
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                lineas.add(s.nextLine());

            }

        } catch (Exception e) {
           System.out.println(Clr.R + "Se ha generado un error: " + e.getMessage() + Clr.RT);

        } finally {
            try {
                if (s != null) s.close();
            } catch (Exception e2) {
                System.out.println(Clr.R + "Se ha generado un error: " + e2.getMessage() + Clr.RT);
            }
        }
        return lineas;
    }
}
