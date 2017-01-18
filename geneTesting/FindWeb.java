
/**
 * Write a description of FindWeb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class FindWeb {
    public void printLine(){
        URLResource res = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String line : res.lines()){
            if(line.indexOf("youtube.com") != -1){
            int start = line.indexOf("\"");
            int end = line.lastIndexOf("\"");
            String target = line.substring(start+1,end);
            System.out.println(target);
        }
        }
    }
}
