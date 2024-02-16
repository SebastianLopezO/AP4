public class Main {
    public static void main(String[] args) {

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

