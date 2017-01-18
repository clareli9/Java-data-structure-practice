import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    private int totalCount = 0;
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder target = new StringBuilder("");
        for (int i = whichSlice; i < message.length(); i = i + totalSlices){
            target.append(message.charAt(i));
        }
        return target.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++){
            key[i] = cc.getKey(sliceString(encrypted,i,klength));
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String text = fr.asString();
        //int[] key = tryKeyLength(text,4,'e');
        //VigenereCipher vc = new VigenereCipher(key);
        HashMap<String,HashSet<String>> map = new HashMap<String,HashSet<String>>();
        breakForAllLanguages(text,map);
       
        
        
        //System.out.println(vc.decrypt(text));
        //HashSet<String> dic = readDictionary();
        //System.out.println(breakForLanguage(text,dic));
        /*
        for (int i = 0; i < 50; i++){
            System.out.print(breakForLanguage(text,dic).charAt(i));
        }
        
        System.out.println(countWords(breakForLanguage(text,dic),dic));
        */
    }
    
    public HashSet<String> readDictionary(String name){
        HashSet<String> set = new HashSet<String>();
        FileResource fr = new FileResource("dictionaries/" + name);
        //String text = fr.asString();
        for (String line : fr.lines()){
            line.toLowerCase();
            set.add(line);
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        String[] target = message.split("\\W");
        int count = 0;
        for (String s : target){
            if (dictionary.contains(s)){
                count ++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char common){
        int most = 0;
        String decrypted = "";
        int target = 0;
        for (int i = 1; i <= 100; i++){
            int[] key = tryKeyLength(encrypted,i,common);
            VigenereCipher vc = new VigenereCipher(key);
            decrypted = vc.decrypt(encrypted);
            if (most <= countWords(decrypted,dictionary)){
                most = countWords(decrypted,dictionary);
                target = i;
            }
        }
        // Global variable
        totalCount = most;
        int []realKey = tryKeyLength(encrypted,target,common);
        VigenereCipher realVc = new VigenereCipher(realKey);
        //System.out.println(target);
        return realVc.decrypt(encrypted);
    } 
    
    public char mostCommonCharIn(HashSet<String> dictionary) throws NullPointerException{
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        char target = 'a';
        int most = 0;
        //String s = "";
        //Iterator iter = dictionary.iterator();
        for (String s : dictionary){
            //s = iter.next();
            for (int i = 0; i < s.length(); i++){
                if (!map.containsKey(s.charAt(i))){
                    map.put(s.charAt(i),1);
                }
                else{
                    map.put(s.charAt(i),map.get(s.charAt(i))+1);
                }
            }
        }
        
        for (char c : map.keySet()){
            if (most < map.get(c)){
                most = map.get(c);
                target = c;
            }
        }
        return target;
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String,HashSet<String>> languages){
        String []lanList = {"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        int most = 0;
        String decrypted = "";
        String targetLan = "";
        for (String lan : lanList){
            HashSet<String> set = new HashSet<String>();
            set = readDictionary(lan);
            languages.put(lan,set);
        }
        
        for (String lan : lanList){
            decrypted = breakForLanguage(encrypted,languages.get(lan),mostCommonCharIn(languages.get(lan)));
            if (most < totalCount){
                most = totalCount;
                targetLan = lan;
            }
        }
        
        System.out.println(targetLan);
        decrypted = breakForLanguage(encrypted,languages.get(targetLan),mostCommonCharIn(languages.get(targetLan)));
        for (int i = 0; i < 100; i++){
            System.out.print(decrypted.charAt(i));

        }
   }
}
