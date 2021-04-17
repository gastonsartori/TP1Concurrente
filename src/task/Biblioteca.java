package task;


public class Biblioteca {

    private static Libro[] libros = new Libro[24]; //Utilizamos static pq estamos en un contexto estatico.
    private Thread[] escritores = new Thread[10];
    private Thread[] lectores = new Thread[20];

    public Biblioteca() {

        //Se crean los libros primero...
        for (int i=0; i< libros.length;i++){
            libros[i]=new Libro(i);
        }

        //Inicializo los escritores segundo
        for (int i = 0; i < escritores.length; i++) {
            escritores[i] = new Thread(new Escritor());
            escritores[i].start();
        }

        //Inicializo los lectores
        for (int i = 0; i < lectores.length; i++) {
            lectores[i] = new Thread(new Lector());
            lectores[i].start();
        }

    }

    //TODO: revisar que se ejecute solo al finalizar.
    public boolean verify(){
        int cont = 0;
        for (int i = 0; i < libros.length; i++) {
            if(libros[i].isVerificado()){
                cont++;
            }
        }
        return cont == 24;
    }

    public Thread[] getEscritores() {
        return escritores;
    }

    public Thread[] getLectores() {
        return lectores;
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
