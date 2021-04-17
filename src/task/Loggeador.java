package task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Loggeador {

    public void escribir(String cadena){
        try(FileWriter file= new FileWriter( "C://Users//Emi//Desktop//log.txt");
            PrintWriter pw= new PrintWriter(file);){
            pw.println(cadena);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
