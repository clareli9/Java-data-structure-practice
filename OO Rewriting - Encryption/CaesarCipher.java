
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        // Make the input upperCase
        String temp = input.toUpperCase();
        StringBuilder encrypted = new StringBuilder(temp);
        
        String result = "";
        // Encrypt
        for(int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
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
        
        //System.out.println(result);
        return result;
   
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
}
