package com.company;

import task.Biblioteca;
import task.Escritor;
import task.Lector;
import task.Loggeador;

public class Main {

    public static void main(String[] args) {
	    Biblioteca biblioteca = new Biblioteca();
        Thread[] escritores = new Thread[10];
        Thread[] lectores = new Thread[20];
	    boolean finish = false;
	    Loggeador logger = new Loggeador();

        //Inicializo los escritores
        for (int i = 0; i < escritores.length; i++) {
            escritores[i] = new Thread(new Escritor());
            escritores[i].start();
        }

        //Inicializo los lectores
        for (int i = 0; i < lectores.length; i++) {
            lectores[i] = new Thread(new Lector());
            lectores[i].start();
        }

	    while(!finish){

            logger.escribir(biblioteca.estadoActual());
            finish = true;
            for (int i = 0; i < 20; i++) {
                finish = finish && (lectores[i].getState() == Thread.State.TERMINATED);
            }
            for (int i = 0; i < 10; i++) {
                finish = finish && (escritores[i].getState() == Thread.State.TERMINATED);
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
