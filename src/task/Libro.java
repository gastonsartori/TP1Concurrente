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

    public boolean isVersionFinal() {
        return versionFinal;
    }

    public boolean isLecturaFinal() { return lecturaFinal;}

    //Metodo para revisar que el libro este verificado(reads=20 && reviews=10)
    public boolean isVerificado(){ return (versionFinal && reads==20); }

    public int getId() { return id; }

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
