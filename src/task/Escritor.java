/*
@Clase: Escritor
Modela el comportamiento de una Escritor que debe revisar 24 Libros con todas las precauciones necesarias de
escritura/lectura que se deben tener en contextos de concurrencia.
 */
package task;

import java.util.ArrayList;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Escritor implements Runnable{

    Random rand = new Random();
    private ArrayList<Libro> librosNoRevisados = new ArrayList<>(Biblioteca.getLibros());

    @Override
    public void run() {
        while(!librosNoRevisados.isEmpty()){
            int i = rand.nextInt(librosNoRevisados.size()); //Elige un nro random para ubicar el indice del libro
            Libro libroaRevisar=librosNoRevisados.get(i);
            pedirWriteLock(libroaRevisar);
            revisarLibro(libroaRevisar);
            notificarLectores(libroaRevisar);
            librosNoRevisados.remove(i);
        }
    }

    public void pedirWriteLock(Libro libro){
        synchronized (libro){
            libro.getLock().writeLock().lock();
        }
    }

    public void notificarLectores(Libro libro){
        synchronized (libro){
            if(!libro.getLock().hasQueuedThreads()){
                libro.notify();
            }
        }
    }

    public void revisarLibro(Libro libro){
        try {
            sleep(rand.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            libro.incReviews();
            libro.getLock().writeLock().unlock();
        }

    }


}
