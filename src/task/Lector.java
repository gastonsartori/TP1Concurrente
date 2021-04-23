package task;

import java.util.ArrayList;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Lector implements Runnable {

    Random rand = new Random(); //se usa para ver los tiempos en mls.
    private ArrayList<Libro> librosNoLeidosVF = new ArrayList<>(Biblioteca.getLibros()); //se usa para ver los indices de los libros leidos en version final.

    @Override
    public void run() {
        while (!librosNoLeidosVF.isEmpty()) {
            int i = rand.nextInt(librosNoLeidosVF.size()); //Elige un nro random para ubicar el indice del libro
            Libro libroaLeer = librosNoLeidosVF.get(i);
            synchronized (libroaLeer) {
                if (libroaLeer.getLock().isWriteLocked() && libroaLeer.getLock().hasQueuedThreads()) { //si no hay escritor en el libro y no hay cola de espera(esscritores)
                    try {
                        libroaLeer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    libroaLeer.getLock().readLock().lock();
                }
            }
            leerLibro(libroaLeer);
        }
    }

    public void leerLibro(Libro libro){
        try {
            sleep(rand.nextInt(100)); //simula el tiempo que demora en leer
        } catch (InterruptedException e) {
            e.printStackTrace(); //busca la ruta del error
        } finally{
            libro.getLock().readLock().unlock(); //asegura que se libera el lock(); evita deadlock
        }
        if(libro.isVersionFinal()){ //si solo si es version final
            libro.incReads(); //incrementa la variable reads de libro leido
        }
   }
}
