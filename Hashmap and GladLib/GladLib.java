import edu.duke.*;
import java.util.*;

public class GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	// The structure to store the word used
	private ArrayList<String> wordUsedList;
	private Random myRandom;
	private int wordReplaced = 0;
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random(); 
	}
	
	private void initializeFromSource(String source) {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");	
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
		wordUsedList = new ArrayList<String>();
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
	    while(true){
		   if (label.equals("country")) {
			  String temp = randomFrom(countryList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   if (label.equals("color")){
			  String temp = randomFrom(colorList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   if (label.equals("noun")){
			  String temp = randomFrom(nounList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   if (label.equals("name")){
			  String temp = randomFrom(nameList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   if (label.equals("adjective")){
			  String temp = randomFrom(adjectiveList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   if (label.equals("animal")){
			  String temp = randomFrom(animalList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   if (label.equals("timeframe")){
			  String temp = randomFrom(timeList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   if (label.equals("number")){
			  return ""+myRandom.nextInt(50)+5;
		   }
		   if (label.equals("verb")){
		      String temp = randomFrom(verbList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   if (label.equals("fruit")){
		      String temp = randomFrom(fruitList);
			  if(wordUsedList.contains(temp)){
			      continue;
			  }
			  else{
			      return temp;
			  }
		   }
		   return "**UNKNOWN**";
        }
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		wordReplaced ++;
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
		System.out.println(wordReplaced);
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    wordUsedList.clear();
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate.txt");
		printOut(story, 60);
	}
	


}
