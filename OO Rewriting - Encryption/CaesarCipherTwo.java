
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class CaesarCipherTwo {
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String shiftedAlphabet1 = "";
    private String shiftedAlphabet2 = "";
    private int mainKey1 = 0;
    private int mainKey2 = 0;
    
    public CaesarCipherTwo(int key1, int key2){
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    public String encrypt(String input, int key){
        // Make the input upperCase
        String temp = input.toUpperCase();
        StringBuilder encrypted = new StringBuilder(temp);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        String result = "";
        // Encrypt
        for(int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftAlphabet.charAt(idx);
                encrypted.setCharAt(i,newChar);
            }
        }
        
        String tempResult = encrypted.toString();
        // Check the upper or lower case
        for(int i = 0; i < encrypted.length(); i++){
            if(Character.isLowerCase(input.charAt(i))){
                result = tempResult.substring(0,i) + tempResult.substring(i,i+1).toLowerCase()+ tempResult.substring(i+1);
                tempResult = result;
            }
        }
        
        System.out.println(result);
        return result;
    }
    
    public String encryptTwoKeys(String input){
        StringBuilder temp1 = new StringBuilder("");
        StringBuilder temp2 = new StringBuilder("");
        StringBuilder result = new StringBuilder("");
        
        for(int i = 0; i < input.length(); i = i + 2){
            temp1.append(String.valueOf(input.charAt(i)));
            if(i < input.length() - 1){
               temp2.append(String.valueOf(input.charAt(i+1)));
            }
        }
        String encrypt1 = encrypt(temp1.toString(),mainKey1);
        String encrypt2 = encrypt(temp2.toString(),mainKey2);
        
        for(int i = 0; i < encrypt1.length() && i < encrypt2.length(); i++){
            result.append(String.valueOf(encrypt1.charAt(i)));
            result.append(String.valueOf(encrypt2.charAt(i)));
        }
        if(result.length() < encrypt1.length()*2){
            result.append(String.valueOf(encrypt1.charAt(encrypt1.length()-1)));
        }
        
        System.out.println(result.toString());
        return result.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cct.encryptTwoKeys(input);
    }
    
}
