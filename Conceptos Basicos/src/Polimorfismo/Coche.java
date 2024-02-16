package Polimorfismo;

public class Coche implements Transporte {
    private int velocidad;

    public Coche(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public void avanzar() {
        System.out.println("El coche está conduciendo a " + velocidad + " km/h");
    }

    @Override
    public void frenar() {
        System.out.println("El coche está frenando");
    }
}