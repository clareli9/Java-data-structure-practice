
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
	private Random myRandom;
	private int num;
	public MarkovModel(int _num) {
		myRandom = new Random();
		num = _num;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
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
	
	public ArrayList<String> getFollows(String key){
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while (pos < myText.length()){
	        int start = myText.indexOf(key,pos);
	        if (start == -1 || start + key.length() >= myText.length()){
	            break;
	        }
	        follows.add(myText.substring(start+key.length(),start+key.length()+1));
	        pos = start + key.length();
	    }
	    
	    return follows;
	}
}
