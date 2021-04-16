package task;

import java.util.ArrayList;
import java.util.Random;
import static java.lang.Thread.sleep;


public class Escritor implements Runnable{

    Random rand = new Random();
    private ArrayList<Integer> librosRevisados= new ArrayList<>();
    private Libro[] libros = Biblioteca.getLibros(); //tiene los libros de la biblioteca

    @Override
    public void run() {
        while(librosRevisados.size() < 24){
            int i = rand.nextInt(24); //Elije un nro random para ubicar el indice del libro
            if( !librosRevisados.contains(i) ){
                revisarLibro(libros[i]);
            }
        }
        System.out.println("TODOS LOS LIBROS ESTAN REVISADOS por: "+Thread.currentThread().getName());
    }

    public void revisarLibro(Libro libro){
        libro.getLock().writeLock().lock();
        try {
            sleep(rand.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            libro.getLock().writeLock().unlock();
        }
        libro.incReviews();
        librosRevisados.add(libro.getId()); //no se controla porque es de este Thread
    }
}
