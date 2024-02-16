import Herencia.*;
import Polimorfismo.*;
public class Main {
    public static void main(String[] args) {
        // Crear instancias de cada ave
        Ganso ave1 = new Ganso();
        Pato ave2 = new Pato();
        Gallina ave3 = new Gallina();

        // Llamar al método hacerSonido de cada ave
        ave1.Comer();
        ave2.Comer();
        ave3.Comer();

        //Diferente Sonido
        ave1.hacerSonido();
        ave2.hacerSonido();
        ave3.hacerSonido();
    }

    public static void EjercicoPolimorfismo(){
        Avión avion = new Avión(900);
        Caballo caballo = new Caballo(40);
        Coche coche = new Coche(120);

        avion.avanzar();
        avion.frenar();

        caballo.avanzar();
        caballo.frenar();

        coche.avanzar();
        coche.frenar();
    }

    public static void EjercicioHerencia(){
        // Crear instancias de cada ave
        Ganso ave1 = new Ganso();
        Pato ave2 = new Pato();
        Gallina ave3 = new Gallina();

        // Llamar al método hacerSonido de cada ave
        ave1.Comer();
        ave2.Comer();
        ave3.Comer();

        //Diferente Sonido
        ave1.hacerSonido();
        ave2.hacerSonido();
        ave3.hacerSonido();

    }
    public static void EjercicioCaja(){
        // Crear una instancia de la clase Caja
        Caja miCaja = new Caja(10, 20);

        // Abrir la caja
        miCaja.abrirse();

        // Guardar un objeto en la caja
        miCaja.guardar("Libro");

        // Cerrar la caja
        miCaja.cerrarse();
    }

    public  static  void EjercicioPersona(){
        // Crear instancias de la clase Persona
        Persona maria = new Persona("María", 25, "mujer");
        Persona juan = new Persona("Juan", 30, "hombre");
        Persona pedro = new Persona("Pedro", 28, "hombre");

        // Presentar a cada persona
        maria.presentarse();
        juan.presentarse();
        pedro.presentarse();
    }
}

