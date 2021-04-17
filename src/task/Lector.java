package task;

import java.util.ArrayList;
import java.util.Random;
import static java.lang.Thread.sleep;

public class Lector implements Runnable {

    Random rand = new Random(); //se usa para ver los tiempos en mls.
    private ArrayList<Integer> librosLeidosVF = new ArrayList<>(); //se usa para ver los indices de los libros leidos en version final.
    private Libro[] libros = Biblioteca.getLibros(); //tiene los libros de la biblioteca

    @Override
    public void run() {
        while(librosLeidosVF.size() < 24){
            int i = rand.nextInt(24); //Elije un nro random para ubicar el indice del libro
            if(!(libros[i].getLock().isWriteLocked()) && !(libros[i].getLock().hasQueuedThreads())){
                if( !librosLeidosVF.contains(i) ){
                    leerLibro(libros[i]);
                }
            }
        }
    }

    //TODO Los escritores tienen prioridad por sobre los lectores en el acceso a un libro.
    public void leerLibro(Libro libro){
        libro.getLock().readLock().lock();
        try {
            sleep(rand.nextInt(100)); //simula el tiempo que demora en leer
        } catch (InterruptedException e) {
            e.printStackTrace(); //busca la ruta del error
        } finally{
            libro.getLock().readLock().unlock(); //asegura que se libera el lock(); evita deadlock
        }
        if(libro.isVersionFinal()){ //si solo si es version final
            libro.incReads(); //incrementa la variable reads de libro leido
            librosLeidosVF.add(libro.getId()); //agrega el indice del libro al ArrayList<Integer>
        }
   }
}
