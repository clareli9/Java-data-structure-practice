
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> criteria;
    
    public MatchAllFilter (){
        criteria = new ArrayList<Filter>();
    }
    
    public void addFilter (Filter f){
        criteria.add(f);
    }
    
    public boolean satisfies (QuakeEntry qe){
        for (Filter f : criteria){
            if (!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
  
}
