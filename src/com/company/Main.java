package com.company;

import task.Biblioteca;
import task.Loggeador;

public class Main {

    public static void main(String[] args) {
	    Biblioteca biblioteca = new Biblioteca();
	    boolean finish = false;
	    Loggeador logger = new Loggeador();

	    while(!finish){

            logger.escribir(biblioteca.estadoActual());
            finish = true;
            for (int i = 0; i < 20; i++) {
                finish = finish && (biblioteca.getLectores()[i].getState() == Thread.State.TERMINATED);
            }
            for (int i = 0; i < 10; i++) {
                finish = finish && (biblioteca.getEscritores()[i].getState() == Thread.State.TERMINATED);
            }
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	    /*
     TODO: agregar log c/2 seg
          -Cantidad de libros con la totalidad de escritores que lo revisaron (atributo reviews)
          -Cantidad de libros con la totalidad de lecturas (atributo reads)

     TODO: VERIFICAR TODOS LOS LIBROS AL FINALIZAR LA EJECUCION
     */
    }
}
