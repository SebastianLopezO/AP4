package view;


import bean.Menu;
import logic.Tarjetas;

import javax.swing.*;
import java.util.regex.Pattern;

public class MenuTarjetas extends Menu {

    public MenuTarjetas(String title) {
        super(title);
    }

    @Override
    public void menu() {
        Tarjetas tarjetas = new Tarjetas();
        while (true) {
            String[] Options = {
                    "Ingresar una nueva Tarjeta de Credito",
                    "Mostrar Tarjetas de Credito",
                    "Mostrar Tarjetas de Credito MasterCard",
                    "Mostrar Tarjetas de Credito Visa",
                    "Mostrar Tarjetas de Credito por un Año determinado",
                    "Volver"
            };

            String opt;
            try {
                opt = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione una opción para las tarjetas: ",
                        "Tarjetas de Credito",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Options,
                        Options[0]);
            } catch (Exception e) {
                msg("Se necesita una opcion valida " + e.getMessage());
                continue;
            }

            switch (opt) {

                case "Mostrar Tarjetas de Credito":
                    msgHtml(tarjetas.ShowCards(),530,500);
                    break;

                case "Mostrar Tarjetas de Credito MasterCard":
                    msgHtml(tarjetas.ShowMasterCard(),530,500);
                    break;

                case "Mostrar Tarjetas de Credito Visa":
                    msgHtml(tarjetas.ShowVisa(),480,500);
                    break;

                case "Mostrar Tarjetas de Credito por un Año determinado":
                    String Year = ValidateRegex("\\d{2}", "Ingrese el año");
                    msgHtml(tarjetas.CardsYear(Year),505,500);
                    break;

                case "Ingresar una nueva Tarjeta de Credito":
                    String numero = ValidateNumbersCards(tarjetas);
                    String fecha = ValidateRegex("(0[1-9]|1[0-2])/(\\d{2})", "Ingrese la fecha (mm/yy)");
                    String codigo = ValidateRegex("\\d{3}", "Ingrese el codigo (numero de 3 digitos)");
                    String tipo = ValidateRegex("(MasterCard|Visa)", "Ingrese el tipo de tarjeta (MaterCard o Visa)");
                    String nombre = ValidateRegex(".+", "Ingrese el nombre del dueño de la tarjeta").trim();
                    String apellido = ValidateRegex(".+", "Ingrese el apellido del dueño de la tarjeta").trim();
                    tarjetas.Insert(nombre, apellido, numero, fecha, codigo, tipo);
                    msg("Se ha ingresado exitosamente la tarjeta de Credito");
                    break;

                default:
                    break;

                case "Volver": {
                    return;
                }
            }
        }
    }

    public String ValidateRegex(String patron, String msginput) {
        Pattern Patron = Pattern.compile(patron);
        String input;
        while (true) {
            input = input(msginput);
            if (!Patron.matcher(input).matches()) { // validar el formato correcto
                msg("Formato invalido");
            } else
                return input;
        }
    }

    public String ValidateNumbersCards(Tarjetas tarjetas) {
        String input;
        while (true) {
            input = ValidateRegex("\\d{16}", "Ingrese el numero de la tarjeta (numero de 16 digitos)");
            if (!tarjetas.NewCard(input)) {
                msg("La tarjeta ya existe");
            } else
                return input;
        }
    }

    private String TypeCards() {
        String[] Options = {
                "Visa",
                "MasterCard",
        };

        String Option = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el tipo de Tarjeta de Credito: ",
                "Tarjetas de Credito",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Options,
                Options[0]);
        return Option;
    }
}

