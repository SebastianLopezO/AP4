package Herencia;

// Clase Pato (clase hija de Ave)
public class Pato extends Ave {
    @Override
    public void hacerSonido() {
        System.out.println("El pato hace un sonido: ¡Quack!");
    }
}