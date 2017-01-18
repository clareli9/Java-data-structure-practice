
/**
 * Write a description of ClosestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class ClosestQuakes {
    public void findClosestQuakes(){
        
    }
    
    public int findShortest (ArrayList<Float> distance){
        float currentDis = 0;
        float shortestDis = 0;
        int idx = 0;
        
        for (int i = 0; i < distance.size(); i++){
            currentDis = distance.get(i);
            if (currentDis < 0){
                continue;
            }
            else{
                if (shortestDis == 0){
                    shortestDis = currentDis;
                    idx = i;
                } 
                else{
                    if (shortestDis > currentDis){
                        shortestDis = currentDis;
                        idx = i;
                    }
                }
            }
        }
        return idx;
    }
    
    public ArrayList<QuakeEntry> getClosest (ArrayList<QuakeEntry> quakeData, Location current, int howMany){
        ArrayList<QuakeEntry> target = new ArrayList<QuakeEntry>();
        ArrayList<Integer> idx = new ArrayList<Integer>();
        ArrayList<Float> currentDis = new ArrayList<Float>();
        float STANDARD = -1;
        int tempIdx = 0;
        
        for (QuakeEntry qe : quakeData){
            currentDis.add(qe.getLocation().distanceTo(current));
        }
        
        for (int i = 1; i <= currentDis.size(); i++){
            if (i <= howMany){
                tempIdx = findShortest(currentDis);
                idx.add(tempIdx);
                currentDis.set(tempIdx,STANDARD);
            }
            else{
                break;
            }
        }
        
        for (int i = 0; i < idx.size(); i++){
            target.add(quakeData.get(idx.get(i)));
        }
        return target;
    }
}
