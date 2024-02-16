package Mascotas;

public class Perro {
    private String nombre;
    private int edad;
    private String raza;

    public Perro(String nombre, int edad, String raza) {
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
    }

    public void ladrar() {
        System.out.println(nombre + " está ladrando: ¡Guau! ¡Guau!");
    }

    public void presentarse() {
        System.out.println("Hola, soy " + nombre + ", tengo " + edad + " años y soy un perro de raza " + raza + ".");
    }
}