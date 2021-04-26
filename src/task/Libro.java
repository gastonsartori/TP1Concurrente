/*
@Clase: Libro
Modela el comportamiento de un Libro
@atrib: reviews, reads, versionFinal, lecturaFinal, lock, readsKey, reviewsKey
 */
package task;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Libro {

    private int reviews, reads;
    private ReentrantReadWriteLock lock;
    private final Object readsKey;


    public Libro() {
        reviews=0;
        reads=0;
        lock=new ReentrantReadWriteLock();
        readsKey=new Object();
    }
    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    /*
    @return: reviews
    retorna la cantidad de revisiones que obtuvo el libro
     */
    public int getReviews() { return reviews; }
    /*
    @return: reads
    retorn la cantidad de veces que el Libro fue leido.
    */
    public int getReads() {
        synchronized (readsKey) {
            return reads;
        }
    }

    public void incReads(){
        synchronized (readsKey){
            reads++;
        }
    }

    public void incReviews(){
            reviews++;
    }
}
