package view;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*Letras*/
        ArrayList<Character> Letters = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D','E','F','G','H'));

        /*Definición de Tablero*/
        int Tmz = 8, Blq = Tmz ^ 2;
        ArrayList<ArrayList<Integer>> Tablero = new ArrayList<>();

        for (int i = 0; i < Tmz; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < Tmz; j++) {
                if ((i + j) % 2 == 0) {
                    fila.add(0); // blanca
                } else {
                    fila.add(1); // Negra
                }
            }
            Tablero.add(fila);
        }
        //Ingreso manual


        ShowTabConsol(Tablero);
        showTabPane(Tablero);
        String Position = GetDato("Posición","el caballo (ejm: A8)");
        if(Position.matches("[A-Za-z]\\d+")){
            int posC = Letters.indexOf(Character.toUpperCase(Position.charAt(0)));
            int posF = 8-Integer.parseInt(Position.substring(1));
            

            if ((posC + posF) % 2 == 0) {
                Tablero.get(posF).set(posC, 2);
            } else {
                Tablero.get(posF).set(posC, 3);
            }

            ArrayList<int[]> Pos = PosHourse(posF,posC);
            for (int[] item : Pos) {
                int x = item[0];
                int y = item[1];
                if ((x + y) % 2 == 0) {
                    Tablero.get(x).set(y,4);
                } else {
                    Tablero.get(x).set(y,5);
                }

            }

            showTabPane(Tablero);
            ShowTabConsol(Tablero);
        }else{
            JOptionPane.showMessageDialog(null, "La posición no es válida.", "Validación", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static ArrayList<int[]> PosHourse(int x, int y) {
        ArrayList<int[]> posiciones = new ArrayList<>();

        int[][] movimientos = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 } };

        for (int[] movimiento : movimientos) {
            int newX = x + movimiento[0];
            int newY = y + movimiento[1];

            if (esMov(newX, newY)) {
                posiciones.add(new int[] { newX, newY });
            }
        }

        return posiciones;
    }

    public static boolean esMov(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
    private static String GetDato(String Type,String Option) {
        String line;
        while (true) {
            line = JOptionPane.showInputDialog("Ingrese un/una " + Type + " para " + Option + ": ");
            if(line != null){
                if (line.trim().isEmpty()) {
                    System.out.println("No puedes ingresar un/una " + Type +" vacia para " + Option);
                } else {
                    return line;
                }

            }else{
                return "";
            }
        }
    }

    private static void ShowTabConsol(ArrayList<ArrayList<Integer>> Tab){
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

    private static void showTabPane(ArrayList<ArrayList<Integer>> Tab) {
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