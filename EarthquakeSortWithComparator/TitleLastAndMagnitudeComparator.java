
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare (QuakeEntry q1, QuakeEntry q2){
        String[] str1 = q1.getInfo().split(",");
        String[] str2 = q2.getInfo().split(",");
        if (!str1[str1.length-1].equals(str2[str2.length-1])){
            return str1[str1.length-1].compareTo(str2[str2.length-1]);
        }
        else{
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
    }
}
