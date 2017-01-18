
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    public void tester(){
        String target = encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?",21,8);
        //String target = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?",15);
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
    
    public void testCaesar(int key){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message,key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder temp1 = new StringBuilder("");
        StringBuilder temp2 = new StringBuilder("");
        StringBuilder result = new StringBuilder("");
        
        for(int i = 0; i < input.length(); i = i + 2){
            temp1.append(String.valueOf(input.charAt(i)));
            if(i < input.length() - 1){
               temp2.append(String.valueOf(input.charAt(i+1)));
            }
        }
        String encrypt1 = encrypt(temp1.toString(),key1);
        String encrypt2 = encrypt(temp2.toString(),key2);
        
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
    
    
}
