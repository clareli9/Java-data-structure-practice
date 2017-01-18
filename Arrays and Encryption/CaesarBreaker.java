
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class CaesarBreaker {
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
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted,26 - dkey);
    }
    
    
    
    public String halfOfString(String message, int start){
        String result = "";
        for (int i = start; i < message.length(); i = i+2){
            result += message.substring(i,i+1);
        }
        return result;
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        if(maxDex < 4){
            return 26 - (4 - maxDex);
        }
        return maxDex - 4;
    }
    
    // Call from the static class
    public String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted,0);
        String secondHalf = halfOfString(encrypted,1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        System.out.println(key1 + " " + key2);
        return new CaesarCipher().encryptTwoKeys(encrypted,26-key1,26-key2);

    }
    
    public String tester(){
        FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
        String encrypted = fr.asString();
        // String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //return new CaesarCipher().encryptTwoKeys(encrypted,12,2);
        String decrypted = decryptTwoKeys(encrypted);
        //System.out.println(decrypted);
        return decrypted;
    }
}
