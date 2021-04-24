package task;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Libro {

    private int reviews, reads;
    private boolean versionFinal, lecturaFinal;
    private ReentrantReadWriteLock lock;
    private Object readsKey;


    public Libro() {
        reviews=0;
        reads=0;
        versionFinal=false;
        lecturaFinal=false; // indica que fue leido por los 20 lectores en versión final.
        lock=new ReentrantReadWriteLock();
        readsKey=new Object();
    }

    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    public int getReviews() { return reviews; }

    public int getReads() {
        return reads;
    }

    public boolean getVersionFinal() {
        return versionFinal;
    }

    public boolean getLecturaFinal() { return lecturaFinal;}

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
