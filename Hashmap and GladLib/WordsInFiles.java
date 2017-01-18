
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    private ArrayList<String> wordsStore;
    
    public WordsInFiles (){
        map = new HashMap<String,ArrayList<String>>();
        wordsStore = new ArrayList<String>();
    }
    
    private void addWordsFromFile (File f){
        FileResource fr = new FileResource(f);
        for (String s : fr.words()){
            if (!map.containsKey(s)){
                ArrayList<String> temp = new ArrayList<String>(); 
                temp.add(f.getName());
                map.put(s,temp);
            }
            else{
                if (!map.get(s).contains(f.getName())){
                    map.get(s).add(f.getName());
                }
            }
        }
    }
    
    public void buildWordFileMap (){
        if (!map.isEmpty()){
            map.clear();
        }
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber (){
        int highest = 0;
        int current = 0;
        for (String s : map.keySet()){
            current = map.get(s).size();
            if (current > highest){
                highest = current;
            }
        }
        return highest;
    }
    
    public ArrayList<String> wordsInNumFile (int number){
        ArrayList<String> wordsLoad = new ArrayList<String>();
        int test = 0;
        for (String s : map.keySet()){
            if (number == map.get(s).size()){
                wordsLoad.add(s);
                test ++;
                //System.out.println(s);
            }
        }
        System.out.println(test);
        return wordsLoad;
    }
    
    public void printFilesIn (String word){
        for (String s : map.keySet()){
            if (s.equals(word)){
                for (int i = 0; i < map.get(s).size(); i++){
                    System.out.println((map.get(s)).get(i));
                }
            }
        }
    }
    
    public void tester(){
        buildWordFileMap();
        //ArrayList<String> temp = wordsInNumFile(4);
        printFilesIn("sea");
        //printFilesIn("cats");
        //map.clear();
    }
}
