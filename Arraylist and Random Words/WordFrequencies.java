
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        String temp = "";
        FileResource fr = new FileResource();
        
        for (String s : fr.words()){
            temp = s.toLowerCase();
            int idx = myWords.indexOf(temp);
            if(idx == -1){
                myWords.add(temp);
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(idx);
                myFreqs.set(idx,value+1);
            }
        }
    }
    
    public void tester(){
        findUnique();
        System.out.println("Number of unique words: " + myWords.size() );
        /*
        for (int i = 0; i < myWords.size(); i++){
            System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        }*/
        System.out.print("The word occurs most ofter and its count are: " + myWords.get(findIndexOfMax()));
        System.out.println(" " + myFreqs.get(findIndexOfMax()));
    }
    
    public int findIndexOfMax(){
        int current = 0;
        int highest = 0;
        int idx = 0;
        for (int i = 0; i < myFreqs.size(); i++){
            current = myFreqs.get(i);
            if (current > highest){
                highest = current;
                idx = i;
            }
        }
        return idx;
    }
    
    
}
