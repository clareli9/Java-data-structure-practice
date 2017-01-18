
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> myChar;
    private ArrayList<Integer> myIdx;
    
    public CharactersInPlay(){
        myChar = new ArrayList<String>();
        myIdx = new ArrayList<Integer>();
        
    }
    
    public void update(String person){
        
    }
    
    public void findAllCharacters(){
        String possibleChar = "";
        int idx = 0;
        FileResource fr = new FileResource();
        for (String line : fr.lines()){
            if (line.indexOf(".") != -1){
                possibleChar = line.substring(0,line.indexOf("."));
                if (myChar.indexOf(possibleChar) == -1){
                    myChar.add(possibleChar);
                    myIdx.add(1);
                }
                else{
                    idx = myChar.indexOf(possibleChar);
                    myIdx.set(idx,myIdx.get(idx)+1);
                }
            }
        }
        
    }
    
    public void tester(){
        findAllCharacters();
        int current = 0;
        int most = 0;
        int target = 0;
        for (int i = 0; i < myIdx.size(); i++){
            current = myIdx.get(i);
            if (current > most){
                most = current;
                target = i;
            } 
        }
        
        //System.out.println(myChar.get(target) + "\t" + myIdx.get(target));
        charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for (int i = 0; i < myIdx.size(); i++){
            if (myIdx.get(i) >= num1 && myIdx.get(i) <= num2){
                System.out.println(myChar.get(i) + "\t" + myIdx.get(i));
            }
        }
    }
    
}
