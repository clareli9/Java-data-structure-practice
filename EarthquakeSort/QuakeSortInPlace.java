
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth (ArrayList<QuakeEntry> quakeData, int from){
        QuakeEntry largest = quakeData.get(from);
        int idx = from;
        for (int i = from + 1; i< quakeData.size(); i++){
            if (largest.getDepth() < quakeData.get(i).getDepth()){
                largest = quakeData.get(i);
                idx = i;
            }
        }
        return idx;
    }
    
   
    
    public int getLargestMagnitude (ArrayList<QuakeEntry> quakeData, int from){
        QuakeEntry largest = quakeData.get(from);
        int idx = from;
        for (int i = from + 1; i< quakeData.size(); i++){
            if (largest.getMagnitude() < quakeData.get(i).getMagnitude()){
                largest = quakeData.get(i);
                idx = i;
            }
        }
        return idx;
    }
    
    public void sortByLargestDepth (ArrayList<QuakeEntry> in){
        for (int i = 0; i < 50; i++){
            int maxIdx = getLargestDepth(in ,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
    }
    
    public void onePassBubbleSort (ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i = 0; i < quakeData.size() - numSorted - 1; i++){
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()){
                QuakeEntry temp = quakeData.get(i);
                quakeData.set(i,quakeData.get(i+1));
                quakeData.set(i+1,temp);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort (ArrayList<QuakeEntry> in){
        int numSorted = 0;
        for (int i = 0; i < in.size() - 1; i++){
            onePassBubbleSort(in,numSorted);
            numSorted ++;
        }
    }
    
    public boolean checkInSortedOrder (ArrayList<QuakeEntry> quakes){
        for (int i = 0; i < quakes.size() - 1; i++){
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck (ArrayList<QuakeEntry> in){
        int numSorted = 0;
        int passTimes = 0;
        for (int i = 0; i < in.size() - 1; i++){
            if (checkInSortedOrder(in)){
                break;
            }
            else{
                onePassBubbleSort(in,numSorted);
                numSorted ++;
                passTimes ++;
            }
        }
        System.out.println(passTimes + " Passes");
    }
    
    public void sortByMagnitudeWithCheck (ArrayList<QuakeEntry> in){
        int passTimes = 0;
        for (int i = 0; i < in.size(); i++){
            if (checkInSortedOrder(in)){
                break;
            }
            else{
                int maxIdx = getSmallestMagnitude(in ,i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmax = in.get(maxIdx);
                in.set(i,qmax);
                in.set(maxIdx,qi);
                passTimes ++;
            }
        }
        System.out.println(passTimes + " Passes");
       
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitudeWithCheck(list);
        //sortByLargestDepth(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //for (QuakeEntry qe: list) { 
          //  System.out.println(qe);
        //} 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
