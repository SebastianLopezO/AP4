package Herencia;

// Clase Gallina (clase hija de Ave)
public class Gallina extends Ave {
    @Override
    public void hacerSonido() {
        System.out.println("La gallina hace un sonido: ¡Cot Cot!");
    }
}