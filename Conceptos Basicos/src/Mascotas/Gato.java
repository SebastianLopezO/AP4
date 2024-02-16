package Mascotas;

public class Gato {
    private String nombre;
    private int edad;
    private String color;

    public Gato(String nombre, int edad, String color) {
        this.nombre = nombre;
        this.edad = edad;
        this.color = color;
    }

    public void maullar() {
        System.out.println(nombre + " está maullando: ¡Miau! ¡Miau!");
    }

    public void presentarse() {
        System.out.println("Hola, soy " + nombre + ", tengo " + edad + " años y soy un gato de color " + color + ".");
    }
}