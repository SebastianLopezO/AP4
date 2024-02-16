package Polimorfismo;

public class Avión implements Transporte {
    private int velocidad;

    public Avión(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public void avanzar() {
        System.out.println("El avión está volando a " + velocidad + " km/h");
    }

    @Override
    public void frenar() {
        System.out.println("El avión está reduciendo velocidad");
    }
}
