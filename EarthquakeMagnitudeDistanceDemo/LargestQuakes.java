
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> target = getLargest(list,50);
        /*
        for (Double d : target){
            System.out.println(d);
        }*/
        
        for (QuakeEntry qe : target){
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest (ArrayList<QuakeEntry> data){
        int idx = 0;
        double largestMag = 0;
        double currentMag = 0;
        for (int i = 0; i < data.size(); i++){
            currentMag = data.get(i).getMagnitude();
            if (largestMag == 0){
                largestMag = currentMag;
                idx = i;
            }
            else{
                if(largestMag < currentMag){
                    largestMag = currentMag;
                    idx = i;
                }
            }
        }
        return idx;
    }
    /*
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> target = new ArrayList<QuakeEntry>();
        
    }
    */
    // May have the reference problem
    
    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> target = new ArrayList<QuakeEntry>();
        //ArrayList<QuakeEntry> backup = new ArrayList<QuakeEntry>();
        ArrayList<Integer> idx = new ArrayList<Integer>();
        ArrayList<Double> mag = new ArrayList<Double>();
        
        int tempIdx = 0;
        
        for (int i = 1; i <= howMany; i++){
            if (i <= quakeData.size()){
                tempIdx = indexOfLargest(quakeData);
                //target.add(quakeData.get(tempIdx));
                idx.add(tempIdx);
                mag.add(quakeData.get(tempIdx).getMagnitude());
                quakeData.get(tempIdx).setMagnitude(-1); 
            }
            else{
                break;
            }
        }
        
        for (int i = 0; i < idx.size(); i++){
            quakeData.get(idx.get(i)).setMagnitude(mag.get(i));
            target.add(quakeData.get(idx.get(i)));
        }
        return target;
    }
    
   
    
}
