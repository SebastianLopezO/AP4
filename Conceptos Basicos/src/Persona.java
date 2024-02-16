public class Persona {
    private String nombre;
    private int edad;
    private String genero;

    public Persona(String nombre, int edad, String genero) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public void presentarse() {
        System.out.println("Hola, soy " + nombre + ", tengo " + edad + " años y soy " + genero + ".");
    }
}