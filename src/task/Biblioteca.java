/*
@Clase: Biblioteca
Modela el comportamiento de una Biblioteca que contiene 24 Libros.
 */
package task;

import java.util.ArrayList;

public class Biblioteca {

    private static ArrayList<Libro> libros = new ArrayList<>(); //Utilizamos static pq estamos en un contexto estático.
    private int cantidadLibros = 24;

    /*
    @Constructor: Biblioteca
    Inicializa los 24 Libros por defecto y los agrega a la lista de libros.
     */
    public Biblioteca() {
        for (int i=0; i < cantidadLibros;i++){
            libros.add(new Libro());
        }
    }

    /*
    @see: Desde Libros -> [getReads() ; getReviews]
    @return: boolean
    Indica si se llego a las 20 lecturas y 10 revisiones de cada Libro.
     */
    public void verify(){
        for (int i = 0; i < cantidadLibros; i++) {
            /*
            Se asegura de que haya leidos en VF 20 libros revisados por c/escritor
             */
            System.out.println("LIBRO " + i + "--> Reviews=" + libros.get(i).getReviews() + " Reads= " + libros.get(i).getReads() + "\n");
        }

    }

    /*
    @return: static ArrayList<Libro>
    retorna la lista estatica de libros que contiene la Biblioteca
     */
    public static ArrayList<Libro> getLibros(){
        return libros;
    }

    /*
    @return: String
    @see: [revisionesFinales() ; lecturasFinales()]
    retorna una texto que describe el estado actual de la Biblioteca.
     */
    public String estadoActual(){
        String cadena= "Cantidad de libros revisados por todos los escritores: " + revisionesFinales() + "\n" + "Cantidad de libros leidos por todos los lectores en versión final: " + lecturasFinales();
        return cadena;
    }

    /*
    Este metodo evalua si cada libro tuvo su lectura final, y luego incrementa la variable cont.
    @return: cont
     */
    public int lecturasFinales(){
        int cont=0;
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros.get(i).getReads() == 20) {
                cont++;
            }
        }
        return cont;
    }
    /*
    Este metodo evalua si cada libro tuvo su revision final, y luego incrementa la variable cont.
    @return: cont
     */
    public int revisionesFinales(){
        int cont=0;
        for (int i = 0; i < cantidadLibros; i++) {
            if (libros.get(i).getReviews() == 10) {
                cont++;
            }
        }
        return cont;
    }
}
