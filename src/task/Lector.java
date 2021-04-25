/*
@Clase: Lector
Modela el comportamiento de una Lector que debe leer 24 Libros con todas las precauciones necesarias de
escritura/lectura que se deben tener en contextos de concurrencia.
 */
package task;

import java.util.ArrayList;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Lector implements Runnable {

    Random rand = new Random(); //se usa para ver los tiempos en mls.
    private final ArrayList<Libro> librosNoLeidosVF = new ArrayList<>(Biblioteca.getLibros()); //se usa para ver los indices de los libros leidos en version final.

    @Override
    public void run() {
        while (!librosNoLeidosVF.isEmpty()) {
            boolean isVf = false;
            int i = rand.nextInt(librosNoLeidosVF.size()); //Elige un nro random para ubicar el indice del libro
            Libro libroaLeer = librosNoLeidosVF.get(i);
            pedirReadLock(libroaLeer);
            isVf=comprobarVF(libroaLeer);
            leerLibro(libroaLeer);
            if (isVf) {marcarLibroLeido(i);}
        }
    }

    /*
    Comprueba version final antes de mandar el hilo a dormir
    Evita que mientras el hilo duerme la variable versionFinal pueda llegar a cambiar
    */
    public boolean comprobarVF(Libro libro){
        return libro.getReviews() == 10;
    }

    public void marcarLibroLeido(int i){
        librosNoLeidosVF.remove(i);
    }

    public void leerLibro(Libro libro){
        try {
            sleep(rand.nextInt(100)); //simula el tiempo que demora en leer
        } catch (InterruptedException e) {
            e.printStackTrace(); //busca la ruta del error
        } finally{
            libro.getLock().readLock().unlock(); //asegura que se libera el lock(); evita deadlock
        }
        if(libro.getReviews() == 10){ //si solo si es version final
            libro.incReads(); //incrementa la variable reads de libro leido
        }
   }

    public void pedirReadLock(Libro libro){
        synchronized (libro) {
            if (libro.getLock().isWriteLocked() && libro.getLock().hasQueuedThreads()) { //si no hay escritor en el libro y no hay cola de espera(esscritores)
                try {
                    libro.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                libro.getLock().readLock().lock();
            }
        }
    }
}
