
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int num;
    private HashMap<String,ArrayList<String>> map;
    
	public EfficientMarkovModel(int _num) {
		myRandom = new Random();
		num = _num;
		map = new HashMap<String,ArrayList<String>>();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public void buildMap (String key){
	    
	}
	
	public ArrayList<String> getFollows(String key){
	    ArrayList<String> follows = new ArrayList<String>();
	    int mapCovered = 1;
	    if (map.size() != 0){
	    for (String s : map.keySet()){
	        if (s.equals(key)){
	            follows = map.get(s);
	            break;
	        }
	        mapCovered ++;
	    }
	   }
	    if (mapCovered == map.size() || map.size() == 0){
	        int pos = 0;
	        while (pos < myText.length()){
	            int start = myText.indexOf(key,pos);
	            if (start == -1 || start + key.length() >= myText.length()){
	                break;
	            }
	            follows.add(myText.substring(start+key.length(),start+key.length()+1));
	            pos = start + key.length();
	        }
	        map.put(key,follows);
	    }
	    
	    return follows;
	}
	
	public void printHashMapInfo (){
	    ArrayList<String> test = new ArrayList<String>();
	    ArrayList<String> keyWithMaxValue = new ArrayList<String>();
	    
	    for (String s : map.keySet()){
	        if (test.size() < map.get(s).size()){
	            test = map.get(s);
	        }
	    }
	    for (String s : map.keySet()){
	        if (test.size() == map.get(s).size()){
	            keyWithMaxValue.add(s);
	        }
	    }
	    System.out.println(map.size());
	    System.out.println(test.size());
	    for (String s : keyWithMaxValue){
	        System.out.println(s);
	    }
	}
	   
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-num);
		String key = myText.substring(index,index+num);
		sb.append(key);
		
		for (int k=0; k < numChars - num + 1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0){
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}
		
		return sb.toString();
	}
	
	public String toString(){
	    return String.format("thisi is the EfficientMarkovModel of order " + num);
	}
}
