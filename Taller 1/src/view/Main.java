package view;
import bean.Ajedrez;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static utility.InputPane.GetDato;

public class Main {
    public static void main(String[] args) {
        Ajedrez A = new Ajedrez();
        A.Show();
        A.InputHourse();
        A.Show();
    }

}