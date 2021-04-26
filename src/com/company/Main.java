/*
@author: Perseverance
@version: 1.1
@Clase: Main
 */
package com.company;

import task.Biblioteca;
import task.Escritor;
import task.Lector;
import task.Logger;

public class Main {

    /*
    Metodo principal donde corre el programa.
    Se crea la biblioteca, se crean arreglos del tipo Threads que contendran los escritores y los lectores.
    Setea un boolean para la finalizacion del programa.
    Crea el objeto Logger para luego escribir las actualizaciones durante la ejecucion del programa.
     */
    public static void main(String[] args) {
	    Biblioteca biblioteca = new Biblioteca();
        Thread[] escritores = new Thread[10];
        Thread[] lectores = new Thread[20];
	    boolean finish = false;
	    Logger logger = new Logger();

        /*
        Los siguientes bucles inicializan Threads a partir de un target's Runnable's de la clase Escritores y Lectores
        */
        for (int i = 0; i < escritores.length; i++) {
            escritores[i] = new Thread(new Escritor(), "escritor");
            escritores[i].start();
        }
        for (int i = 0; i < lectores.length; i++) {
            lectores[i] = new Thread(new Lector());
            lectores[i].start();
        }

        /*
        Este bucle imprime por pantalla el estado de la biblioteca cada 2 seg.
        Ademas evalua el estado de los lectores y escritores para saber si finalizaron sus respectivas tareas.
         */
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
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        biblioteca.verify();


    }
}
