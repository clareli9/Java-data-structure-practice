
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class TestCaesarCipher {
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
    
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int key = 0;
        if(maxDex < 4){
            key = 26 - (4 - maxDex);
        }
        key = maxDex - 4;
        
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String input = fr.toString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(input);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(input);
        System.out.println(decrypted);
    }
    
    
}
