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
            synchronized (libroaRevisar){
                libroaRevisar.getLock().writeLock().lock();
            }
            revisarLibro(libroaRevisar);
            synchronized (libroaRevisar){
                if(!libroaRevisar.getLock().hasQueuedThreads()){
                    libroaRevisar.notify();
                }
            }
            librosNoRevisados.remove(i);
        }
        System.out.println("while escritor");
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
