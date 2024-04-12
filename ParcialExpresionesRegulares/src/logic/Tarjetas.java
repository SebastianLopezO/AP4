package logic;

import bean.TarjetaCredito;
import utility.FileManager;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tarjetas {
    private ArrayList<TarjetaCredito> Tarjetas;
    private ArrayList<TarjetaCredito> Tarjetas_MasterCard;
    private ArrayList<TarjetaCredito> Tarjetas_Visa;

    public Tarjetas() {
        Tarjetas = new ArrayList<TarjetaCredito>();
        Tarjetas_MasterCard = new ArrayList<TarjetaCredito>();
        Tarjetas_Visa = new ArrayList<TarjetaCredito>();

        try {
            String root = System.getProperty("user.dir");
            FileManager file = new FileManager(root + "/src/Data/Tarjetas.txt");
            String[] lineas = file.readFile().split("-");

            for (int i = 0; i < lineas.length; i++) {
                String[] tarjetas = lineas[i].split(";");
                TarjetaCredito tarjeta = new TarjetaCredito(tarjetas[0], tarjetas[1], tarjetas[2], tarjetas[3],
                        tarjetas[4], tarjetas[5]);
                Tarjetas.add(tarjeta);
            }
        } catch (IOException e) {
            System.out.println("Hubo un error al leer el archivo de Tarjetas de Credito: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Hubo un error al leer el archivo de Tarjetas de Credito: " + e.getMessage());
        }
        Pattern mastercard = Pattern.compile("MasterCard");
        for (TarjetaCredito tarjeta : Tarjetas) {

            Matcher match = mastercard.matcher(tarjeta.getTipo());
            if (match.matches()) {
                Tarjetas_MasterCard.add(tarjeta);
            } else {
                Tarjetas_Visa.add(tarjeta);
            }
        }

    }

    public ArrayList<TarjetaCredito> getTarjetas() {
        return Tarjetas;
    }

    public void setTarjetas(ArrayList<TarjetaCredito> tarjetas) {
        Tarjetas = tarjetas;
    }

    public ArrayList<TarjetaCredito> getTarjetas_MasterCard() {
        return Tarjetas_MasterCard;
    }

    public void setTarjetas_MasterCard(ArrayList<TarjetaCredito> tarjetas_MasterCard) {
        Tarjetas_MasterCard = tarjetas_MasterCard;
    }

    public ArrayList<TarjetaCredito> getTarjetas_Visa() {
        return Tarjetas_Visa;
    }

    public void setTarjetas_Visa(ArrayList<TarjetaCredito> tarjetas_Visa) {
        Tarjetas_Visa = tarjetas_Visa;
    }

    public String CardsYear(String año) { // buscar todas las tarjetas con un año determinado desde los arraylist de
        // mastercar y visa
        Pattern patron = Pattern.compile("/" + año);
        String s = "--------MASTERCARD---------\n";
        for (TarjetaCredito tarjetaCredito : Tarjetas_MasterCard) {// recorro el arraylist tarjetas_mastercard buscando,
            // y agregando las tarjetas con el año
            // correspondiente, y luego las agrego a un string
            Matcher match = patron.matcher(tarjetaCredito.getFecha());
            if (match.find()) {
                s += "\nNumero: " + tarjetaCredito.getNumero() +
                        "\nFecha: " + tarjetaCredito.getFecha() +
                        "\nCodigo: " + tarjetaCredito.getCodigo() +
                        "\nTipo: " + tarjetaCredito.getTipo() +
                        "\nNombre: " + tarjetaCredito.getNombre() +
                        "\npellido: " + tarjetaCredito.getApellido() + "\n\n";
            }
        }
        s += "------------VISA--------------\n";
        for (TarjetaCredito tarjetaCredito : Tarjetas_Visa) {// recorro el arraylist tarjetas_visa buscando, y agregando
            // las tarjetas con el año correspondiente, y luego las
            // agrego a un string
            Matcher match = patron.matcher(tarjetaCredito.getFecha());
            if (match.find()) {
                s += "\nNumero: " + tarjetaCredito.getNumero() +
                        "\nFecha: " + tarjetaCredito.getFecha() +
                        "\nCodigo: " + tarjetaCredito.getCodigo() +
                        "\nTipo: " + tarjetaCredito.getTipo() +
                        "\nNombre: " + tarjetaCredito.getNombre() +
                        "\npellido: " + tarjetaCredito.getApellido() + "\n\n";
            }
        }
        return s;
    }

    public void Ingresar(String nombre, String apellido, String numero, String fecha, String codigo, String tipo) {// ingresar
        // una
        // tarjeta
        // nueva
        try {
            FileManager file = new FileManager("/src/Data/Tarjetas.txt");
            file.adicionarLinea("\n"+tipo + "," + numero + "," + fecha + "," + nombre + "," + apellido + "," + codigo);
            TarjetaCredito tarjeta = new TarjetaCredito(tipo, numero, fecha, nombre, apellido, codigo);
            Tarjetas.add(tarjeta);
            Pattern Tipo = Pattern.compile("MasterCard");
            Matcher match = Tipo.matcher(tarjeta.getTipo());
            if (match.matches()) {
                Tarjetas_MasterCard.add(tarjeta);
            } else {
                Tarjetas_Visa.add(tarjeta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean TarjetaNueva(String numero) {// verificar si el numero de tarjeta ingresado es nuevo
        boolean bandera = true;
        for (TarjetaCredito tarjetaCredito : Tarjetas) {// recorro el arraylist tarjetas buscando si ya hay una tarjeta
            // con el numero ingresado
            if (tarjetaCredito.getNumero().equals(numero)) {
                bandera = false;
                break;
            }
        }
        return bandera;
    }

    public String ShowCards() {
        String s = "--------MASTERCARD---------\n";
        for (TarjetaCredito tarjetaCredito : Tarjetas_MasterCard) {// recorro el arraylist tarjetas_mastercard agregando
            // todas las tarjetas a un string
            s += "\nNumero: " + tarjetaCredito.getNumero() +
                    "\nFecha: " + tarjetaCredito.getFecha() +
                    "\nCodigo: " + tarjetaCredito.getCodigo() +
                    "\nTipo: " + tarjetaCredito.getTipo() +
                    "\nNombre: " + tarjetaCredito.getNombre() +
                    "\npellido: " + tarjetaCredito.getApellido() + "\n\n";

        }
        s += "------------VISA--------------\n";
        for (TarjetaCredito tarjetaCredito : Tarjetas_Visa) {// recorro el arraylist tarjetas_visa agregando todas
            // las tarjetas a un string
            s += "\nNumero: " + tarjetaCredito.getNumero() +
                    "\nFecha: " + tarjetaCredito.getFecha() +
                    "\nCodigo: " + tarjetaCredito.getCodigo() +
                    "\nTipo: " + tarjetaCredito.getTipo() +
                    "\nNombre: " + tarjetaCredito.getNombre() +
                    "\npellido: " + tarjetaCredito.getApellido() + "\n\n";

        }
        return s;
    }

    public String ShowMasterCard() { // Mostrar todas las tarjetas mastercard
        String s = "--------MASTERCARD---------\n";
        for (TarjetaCredito tarjetaCredito : Tarjetas_MasterCard) {// recorro el arraylist tarjetas_mastercard agregando
            // todas las tarjetas a un string
            s += "\nNumero: " + tarjetaCredito.getNumero() +
                    "\nFecha: " + tarjetaCredito.getFecha() +
                    "\nCodigo: " + tarjetaCredito.getCodigo() +
                    "\nTipo: " + tarjetaCredito.getTipo() +
                    "\nNombre: " + tarjetaCredito.getNombre() +
                    "\npellido: " + tarjetaCredito.getApellido() + "\n\n";

        }
        return s;
    }

    public String ShowVisa() { // Mostrar todas las tarjetas visa
        String s = "------------VISA--------------\n";
        for (TarjetaCredito tarjetaCredito : Tarjetas_Visa) {// recorro el arraylist tarjetas_visa agregando todas
            // las tarjetas a un string
            s += "\nNumero: " + tarjetaCredito.getNumero() +
                    "\nFecha: " + tarjetaCredito.getFecha() +
                    "\nCodigo: " + tarjetaCredito.getCodigo() +
                    "\nTipo: " + tarjetaCredito.getTipo() +
                    "\nNombre: " + tarjetaCredito.getNombre() +
                    "\npellido: " + tarjetaCredito.getApellido() + "\n\n";

        }
        return s;
    }

}
