public class Caja {
    private double ancho;
    private double alto;
    private boolean cerrada;

    public Caja(double ancho, double alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.cerrada = true; // Al crear la caja, inicialmente está cerrada
    }

    public void cerrarse() {
        if (!cerrada) {
            System.out.println("La caja ya está cerrada.");
        } else {
            cerrada = true;
            System.out.println("La caja se ha cerrado correctamente.");
        }
    }

    public void abrirse() {
        if (cerrada) {
            System.out.println("La caja ya está abierta.");
        } else {
            cerrada = false;
            System.out.println("La caja se ha abierto correctamente.");
        }
    }

    public void guardar(String objeto) {
        if (cerrada) {
            System.out.println("No se puede guardar el objeto, la caja está cerrada.");
        } else {
            System.out.println("Se ha guardado el objeto \"" + objeto + "\" dentro de la caja.");
        }
    }
}