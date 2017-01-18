
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer test = new LogAnalyzer();
        test.readFile();
        //test.printAll();
        //test.printAllHigherThanNum(400);
        
        
        System.out.println(test.countUniqueIPs());
        System.out.println(test.uniqueIPVisitsOnDay("Sep 27").size());
        System.out.println(test.countUniqueIPsInRange(200,299));
        
        HashMap<String,Integer> map = test.countVisitsPerIP();
        HashMap<String,ArrayList<String>> map2 = test.iPsForDays();
        //System.out.println(test.mostNumberVisitsByIP(map));
        ArrayList<String> list = test.iPsMostVisits(map);
        /*
        for (String s : list){
            System.out.println(s);
        }*/
        System.out.println(test.mostNumberVisitsByIP(map));
        System.out.println(list);
        
        System.out.println(test.dayWithMostIPVisits(map2));
        System.out.println(test.iPsWithMostVisitsOnDay(map2,"Sep 29"));
        //System.out.println(test.iPsWithMostVisitsOnDay(map2,"Sep 30"));
        
    }
}
