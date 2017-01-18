
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TestCaesarCipherTwo {
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public String halfOfString(String message, int start){
        String result = "";
        for (int i = start; i < message.length(); i = i+2){
            result += message.substring(i,i+1);
        }
        return result;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.toString();
        CaesarCipherTwo cct = new CaesarCipherTwo(17,3);
        System.out.println()
    }
    
    public String breakCaesarCipher(String input){
        
    }
}
