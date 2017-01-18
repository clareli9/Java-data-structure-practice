
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordPlay {
    
    public boolean isVowel(char ch){
        String standard = "AEIOUaeiou";
        boolean vowelFound = false;
        for(int i = 0; i < standard.length(); i++){
            if(ch == standard.charAt(i)){
                 vowelFound = true;
                 break;
            }
        }
        return vowelFound;
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder newPhrase = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++){
            if(isVowel(phrase.charAt(i))){
                newPhrase.replace(i,i+1,String.valueOf(ch));
            }
        }
        return newPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder newPhrase = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++){
            if(phrase.charAt(i) == ch){
                if(i % 2 == 0){
                    newPhrase.replace(i,i+1,"*");
                }
                else if(i % 2 == 1){
                    newPhrase.replace(i,i+1,"+");
                }
            }
        }
        return newPhrase.toString();
    }
    
    public void tester(){
        String target = emphasize("dna ctgaaactga",'a');
        System.out.println(target);
    }
}

