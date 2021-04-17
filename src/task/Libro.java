package task;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Libro {

    private int reviews, reads, id;
    private boolean versionFinal, lecturaFinal;
    private ReadWriteLock lock;
    private Object readsKey, reviewsKey;


    public Libro(int id) {
        reviews=0;
        reads=0;
        versionFinal=false;
        lecturaFinal=false; // indica que fue leido por los 20 lectores en versión final.
        lock=new ReentrantReadWriteLock();
        this.id = id;
        readsKey=new Object();
        reviewsKey = new Object();
    }

    public ReadWriteLock getLock() {
        return lock;
    }
    public int getId() { return id; }

    public int getReviews() { return reviews; }

    public int getReads() {
        return reads;
    }

    public boolean isVersionFinal() {
        return versionFinal;
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
        synchronized (reviewsKey){
            reviews++;
            if(this.reviews == 10){
                versionFinal = true;
            }
        }
    }
}
