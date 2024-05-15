package utility;

public class TestSpeedisPrimo {
    public static void main(String[] args) {
        int numero = 1511;
        long inicio, fin;
        double originalTime, optimizadoTime;

        // Medición del tiempo para el algoritmo original
        inicio = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            isPrimoT(numero);
        }
        fin = System.nanoTime();
        originalTime = (fin - inicio) / 1e6; // Convertir a milisegundos

        // Medición del tiempo para el algoritmo optimizado
        inicio = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            isPrimoCE(numero);
        }
        fin = System.nanoTime();
        optimizadoTime = (fin - inicio) / 1e6; // Convertir a milisegundos

        // Imprimir los resultados
        System.out.println("Tiempo de ejecución del algoritmo original: " + originalTime + " ms");
        System.out.println("Tiempo de ejecución del algoritmo optimizado: " + optimizadoTime + " ms");
    }

    //Busqueda Numeros Primos Uno a uno = O(n)
    public static boolean isPrimoT(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //Busqueda de Numeros Primo Criba de Eratóstenes  = O(qrt(n))
    public static boolean isPrimoCE(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int limite = (int) Math.sqrt(n) + 1;
        for (int i = 5; i < limite; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
