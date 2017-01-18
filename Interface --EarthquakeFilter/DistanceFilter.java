
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location loc;
    private double disMax;
    public DistanceFilter (Location _loc, double _disMax){
        loc = _loc;
        disMax = _disMax;
    }
    
    public boolean satisfies (QuakeEntry qe){
        return qe.getLocation().distanceTo(loc) < disMax;
    }
    
    public void getName (){
        System.out.println("Distance");
    }
}
