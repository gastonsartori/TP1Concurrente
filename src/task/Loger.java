package task;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Loger {

    public void escribir(String cadena){
        try(FileWriter archivo= new FileWriter( "log.txt", true);
            PrintWriter pw= new PrintWriter(archivo)){
            pw.println(cadena);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
