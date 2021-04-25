/*
@Clase: Libro
Modela el comportamiento de un Libro
@atrib: reviews, reads, versionFinal, lecturaFinal, lock, readsKey, reviewsKey
 */
package task;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Libro {

    private int reviews, reads;
    private boolean versionFinal, lecturaFinal;
    private ReentrantReadWriteLock lock;
    private Object readsKey, reviewsKey;


    public Libro() {
        reviews=0;
        reads=0;
        versionFinal=false;
        lecturaFinal=false;
        lock=new ReentrantReadWriteLock();
        readsKey=new Object();
        reviewsKey = new Object();
    }
    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    /*
    @return: reviews
    retorna la cantidad de revisiones que obtuvo el libro
     */
    public int getReviews() {
        synchronized (reviewsKey) {
            return reviews;
        }
    }

    /*
    @return: reads
    retorn la cantidad de veces que el Libro fue leido.
     */
    public int getReads() {
        synchronized (readsKey) {
            return reads;
        }
    }

    public boolean isLecturaFinal() { return lecturaFinal;}

    public void incReads(){
        synchronized (readsKey){
            reads++;
            if(this.reads == 20){
                lecturaFinal = true;
            }
        }
    }
    public void incReviews(){
            reviews++;
            if(this.reviews == 10){
                versionFinal = true;
        }
    }
}
