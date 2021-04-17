package task;


public class Biblioteca {

    private static Libro[] libros = new Libro[24]; //Utilizamos static pq estamos en un contexto estatico.

    public Biblioteca() {

        //Se crean los libros primero...
        for (int i=0; i< libros.length;i++){
            libros[i]=new Libro(i);
        }

    }

    public boolean verify(){
        int cont = 0;
        for (int i = 0; i < libros.length; i++) {
            if(libros[i].getReads() == 20 && libros[i].getReviews() == 10){
                cont++;
            }
        }
        return cont == 24;
    }

    public static Libro[] getLibros() {
        return libros;
    }

    public String estadoActual(){
        String cadena= "Cantidad de libros revisados por todos los escritores: " + revisionesFinales() + "\n" + "Cantidad de libros leidos por todos los lectores en versión final: " + lecturasFinales();
        return cadena;
    }

    public int lecturasFinales(){
        int cont=0;
        for (int i = 0; i < libros.length; i++) {
            if (libros[i].isLecturaFinal()) {
                cont++;
            }
        }
        return cont;
    }
    public int revisionesFinales(){
        int cont=0;
        for (int i = 0; i < libros.length; i++) {
            if (libros[i].isVersionFinal()) {
                cont++;
            }
        }
        return cont;
    }
}
