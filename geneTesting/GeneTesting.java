
/**
 * Write a description of GeneTesting here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
public class GeneTesting {
    public String findProtein(String dna){
        int start = dna.indexOf("atg");
        if(start == -1){
            return "N";
        }
        
        int stop = dna.indexOf("tag",start+3);
        if(stop == -1){
            return "N";
        }
        
        if((start-stop)%3 == 0){
            return dna.substring(start,stop+3);
        }
        else{
            stop = dna.indexOf("tga",start+3);
            if(stop == -1){
            return "N";
            
        }
            if((start - stop) % 3 == 0){
                return dna.substring(start,stop+3);
            }
            else
            {
                return"None";
            }
    }
}


public void testing()
{
    String a = "cccaaatggggttttga";
    String target = "atggggttttga";
    String result = findProtein(a);
    if(target.equals(result)){
        System.out.println("Find" + result);
    }
    else{
        System.out.println("Not found");
    }
}

public void realTesting()
{
    DirectoryResource dr = new DirectoryResource();
    for(File f : dr.selectedFiles()){
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println("read" + s.length() + "characters");
        String result = findProtein(s);
        System.out.println("found" + result);
    }
}
}