
/**
 * Write a description of CondoCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class CondoCount {
    private HashMap<String,Integer> map;
    
    public CondoCount(){
        map = new HashMap<String,Integer>();
        
    }
    
    public void buildCondoMap(int start,String dna){
        map.clear();
        for (int i = start; i < dna.length(); i=i+3){
            if (i+3 >= dna.length()){
                break;
            }
            String temp = dna.substring(i,i+3);
            if(!map.containsKey(temp)){
                map.put(temp,1);
            }
            else{
                map.put(temp,map.get(temp)+1);
            }
        }
    }
    
    public String getMostCommonCondo(){
        int highest = 0;
        String target = "";
        for (String s : map.keySet()){
            if (highest < map.get(s)){
                highest = map.get(s);
                target = s;
            }
        }
        return target;
    }
    
    public void printCondoCounts(int start, int end){
        for (String s : map.keySet()){
            if (map.get(s) >= start && map.get(s) <= end){
                System.out.println(s + "\t" + map.get(s));
            }
        }
        System.out.println(map.size());
    }
    
    public void tester (){
        FileResource fr = new FileResource("dnaMystery2.txt");
        String dna = fr.asString();
        buildCondoMap(0,dna);
        printCondoCounts(1,1000);
        //System.out.printf(dna);
    }
    
}
