
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class WordLengths {
    /*
    public boolean countOrNot(String s, int index){
        int offset = 1;
        if(index == 0 || index == s.length()){
            if(Character.isLetter(s.charAt(index))){
                return true;
            }else{
                return false;
            }
            
        }
        else if(index > 0 && index < s.length()){
            if(Character.isLetter(s.charAt(index))){
                return true;
            }
            else{
                
            }
        }
    }*/
    
    public boolean countOrNot(String s, int index){
        
        if(index == 0 || index == s.length() - 1){
           if(Character.isLetter(s.charAt(index))){
              return true;
           }
           else{
              return false;
           } 
        }
        else{
              return true;
        }
           
    }
    
    public void countWordLengths(FileResource resource, int[] counts){
        int wordCount = 0;
        for(String s : resource.words()){
            for(int i= 0; i < s.length(); i++){
                if(countOrNot(s,i)){
                    wordCount ++;
                }
                
            }
            counts[wordCount] ++;
            wordCount = 0;
        }
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int []counts = new int[31];
        countWordLengths(fr,counts);
        for(int i = 0; i < counts.length; i++){
            if(counts[i] != 0){
                System.out.println(counts[i] + " words of length " + i);
            }
        }
    }
}
