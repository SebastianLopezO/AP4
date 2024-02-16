package Polimorfismo;

public class Caballo implements Transporte {
    private int velocidad;

    public Caballo(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public void avanzar() {
        System.out.println("El caballo está galopando a " + velocidad + " km/h");
    }

    @Override
    public void frenar() {
        System.out.println("El caballo está frenando");
    }
}