package bean;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import static utility.InputPane.GetDato;

public class Ajedrez {
    private int Tmz;
    private ArrayList<Character> Letters;
    ArrayList<ArrayList<Integer>> Tab;

    public Ajedrez(){
        this.Tmz = 8;
        this.Letters = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D','E','F','G','H'));
        this.Tab = new ArrayList<>();
        for (int i = 0; i < Tmz; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < Tmz; j++) {
                if ((i + j) % 2 == 0) {
                    fila.add(0); // Negra
                } else {
                    fila.add(1); // Blanca
                }
            }
            Tab.add(fila);
        }
    }

    public void InputHourse(){
        Show();
        String Position = GetDato("Posición","el caballo (ejm: A8)");
        if(Position.matches("^[A-H][1-8]$")){
            PosHourse(Position);
            Show();
        }else{
            System.out.println("Debes ingresar una posición valida");
            JOptionPane.showMessageDialog(null, "Debes ingresar una posición valida", "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void PosHourse(String Position) {
        int posC = Letters.indexOf(Character.toUpperCase(Position.charAt(0)));
        int posF = 8-Integer.parseInt(Position.substring(1));
        if ((posC + posF) % 2 == 0) {
            Tab.get(posF).set(posC, 2);
        } else {
            Tab.get(posF).set(posC, 3);
        }
        ArrayList<int[]> Pos = new ArrayList<>();

        int[][] movs = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 } };

        for (int[] mov : movs) {
            int newX = posF + mov[0];
            int newY = posC + mov[1];

            if (esMov(newX, newY)) {
                Pos.add(new int[] { newX, newY });
            }
        }
        System.out.print("Las posiciones habiles son: ");
        String msj = "";
        for (int[] item : Pos) {
            int x = item[0];
            int y = item[1];
            System.out.print(Letters.get(y)+""+(8-x)+" ");
            msj +=Letters.get(y)+""+(8-x)+" ";
            if ((x + y) % 2 == 0) {
                Tab.get(x).set(y,4);
            } else {
                Tab.get(x).set(y,5);
            }
        }
        System.out.println();

        JOptionPane.showMessageDialog(null, msj, "Posiciones Habiles", JOptionPane.INFORMATION_MESSAGE);

    }


    public boolean esMov(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public void Show(){
        ShowTabConsol();
        showTabPane();
    }

    private void ShowTabConsol(){
        System.out.println("  ╔═══════════════════════════════════════════╗");
        System.out.println("  ║       A   B   C   D   E   F   G   H       ║");
        System.out.println("  ║     _________________________________     ║");
        int Tmz = Tab.size();
        for (int i = 0; i < Tmz; i++) {
            int pos = Tmz-i;
            System.out.print("  ║  "+pos+"  ");
            for (int j = 0; j < Tmz; j++) {
                int blq = Tab.get(i).get(j);
                switch (blq){
                    case 0:
                        System.out.print("|███");
                        break;
                    case 1:
                        System.out.print("|   ");
                        break;
                    case 2:
                        System.out.print("|█♞█");
                        break;
                    case 3:
                        System.out.print("| ♞ ");
                        break;
                    case 4:
                        System.out.print("|█©█");
                        break;
                    case 5:
                        System.out.print("| © ");
                        break;
                }
            }
            System.out.print("|     ║ ");
            System.out.println();
        }
        System.out.println("  ║     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     ║");
        System.out.println("  ╚═══════════════════════════════════════════╝");
    }

    private void showTabPane() {
        StringBuilder htmlTable = new StringBuilder("<html>");
        htmlTable.append("  <table border='1' cellpadding='5'>");
        htmlTable.append("<tr><th></th><th>A</th><th>B</th><th>C</th><th>D</th><th>E</th><th>F</th><th>G</th><th>H</th></tr>");

        int Tmz = Tab.size();
        for (int i = 0; i < Tmz; i++) {
            int pos = Tmz - i;
            htmlTable.append("<tr><td>").append(pos).append("</td>");
            for (int j = 0; j < Tmz; j++) {
                int blq = Tab.get(i).get(j);
                String color = blq % 2 == 0 ? "black" : "white";
                String cld = "";

                switch (blq) {
                    case 0:
                        cld = "&nbsp;";
                        break;
                    case 1:
                        cld = "&nbsp;";
                        break;
                    case 2:
                        cld = "<span style='color: white;'>&#9822;</span>"; // Caballo blanco
                        break;
                    case 3:
                        cld = "<span style='color: black;'>&#9822;</span>"; // Caballo negro
                        break;
                    case 4:
                        cld = "<span style='color: white;'>&#169;</span>"; // Símbolo blanco
                        break;
                    case 5:
                        cld = "<span style='color: black;'>&#169;</span>"; // Símbolo negro
                        break;
                    default:
                        cld = "&nbsp;"; // Espacio vacío por defecto
                        break;
                }

                htmlTable.append("<td style='background-color: ").append(color).append(";'>").append(cld).append("</td>");
            }
            htmlTable.append("</tr>");
        }

        htmlTable.append("  </table>");
        htmlTable.append("</html>");

        JOptionPane.showMessageDialog(null, htmlTable.toString(), "Tablero de ajedrez", JOptionPane.INFORMATION_MESSAGE);
    }
}
