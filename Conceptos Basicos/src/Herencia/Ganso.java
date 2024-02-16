package Herencia;

// Clase Ganso (clase hija de Ave)
public class Ganso extends Ave {
    @Override
    public void hacerSonido() {
        System.out.println("El ganso hace un sonido: ¡Honk!");
    }
}