
package task;

import java.util.ArrayList;

public class Biblioteca {

    private static ArrayList<Libro> libros = new ArrayList<>(); //Utilizamos static pq estamos en un contexto estático.
    private int cantidadLibros = 24;


    public Biblioteca() {
        for (int i=0; i < cantidadLibros;i++){
            libros.add(new Libro());
        }
    }

    public boolean verify(){
        int cont = 0;
        for (int i = 0; i < cantidadLibros; i++) {
            /*
            Se asegura de que haya leidos en VF 20 libros revisados por c/escritor
             */
            if(libros.get(i).getReads() == 20 && libros.get(i).getReviews() == 10){
                cont++;
            }
        }
        return cont == 24;
    }

    public static ArrayList<Libro> getLibros() {
        return libros;
    }

    /*
    @param:
    @return:
     */
    public String estadoActual(){
        String cadena= "Cantidad de libros revisados por todos los escritores: " + revisionesFinales() + "\n" + "Cantidad de libros leidos por todos los lectores en versión final: " + lecturasFinales();
        return cadena;
    }

    public int lecturasFinales(){
        int cont=0;
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros.get(i).getLecturaFinal()) {
                cont++;
            }
        }
        return cont;
    }
    public int revisionesFinales(){
        int cont=0;
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros.get(i).getVersionFinal()) {
                cont++;
            }
        }
        return cont;
    }
}
