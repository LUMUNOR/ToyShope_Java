package Filewriter;

import java.io.FileWriter;
import java.io.IOException;

public class Filewriter {

    public Filewriter(){}

    public void work(StringBuilder info){
        try( FileWriter writer = new FileWriter("Prizes.txt",true))
        {
           writer.write(info.toString());
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
    }
    
}