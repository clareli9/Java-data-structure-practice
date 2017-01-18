
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    
    public PhraseFilter (String _where, String _phrase){
        where = _where;
        phrase = _phrase;
    }
    
    public boolean satisfies (QuakeEntry qe){
        if (where.equals("start")){
            return qe.getInfo().startsWith(phrase);
        }
        else if (where.equals("end")){
            return qe.getInfo().endsWith(phrase);
        }
        else if (where.equals("any")){
            return qe.getInfo().contains(phrase);
        }
        else{
            return false;
        }
        
    }
    
    public void getName (){
        System.out.println("Phrase");
    }
}
