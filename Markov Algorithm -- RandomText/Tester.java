
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> target = markov.getFollows(".");
        for (String str : target){
            System.out.println(str);
        }
        System.out.println(target.size());
    }
    
    public void testGetFollowsWithFile(){
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        markov.setTraining(fr.asString());
        ArrayList<String> target = markov.getFollows("he");
        System.out.println(target.size());
    }
}
