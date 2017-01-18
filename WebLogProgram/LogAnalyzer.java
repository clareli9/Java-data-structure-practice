
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private ArrayList<String> uniqueIDs;
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
         uniqueIDs = new ArrayList<String>();
         
         
     }
        
     public void readFile() {
         // complete method
         FileResource fr = new FileResource();
         WebLogParser parser = new WebLogParser();
         for (String line : fr.lines()){
             
             LogEntry le = parser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         for (LogEntry le : records){
             if (!uniqueIDs.contains(le.getIpAddress())){
                 uniqueIDs.add(le.getIpAddress());
             }
         }
         return uniqueIDs.size();
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry le : records){
             if (le.getStatusCode() > num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         String[] date = someday.split(" ");
         for (LogEntry le : records){
             String[] accessTime = le.getAccessTime().toString().split(" ");
             if (date[0].equals(accessTime[1]) && date[1].equals(accessTime[2])
             && !uniqueIPs.contains(le.getIpAddress())){
                 uniqueIPs.add(le.getIpAddress());
             }
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for (LogEntry le : records){
             if (le.getStatusCode() >= low && le.getStatusCode() <= high
             && !uniqueIps.contains(le.getIpAddress())){
                 uniqueIps.add(le.getIpAddress());
             }
         }
         return uniqueIps.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (LogEntry le : records){
             if (!counts.containsKey(le.getIpAddress())){
                 counts.put(le.getIpAddress(),1);
             }
             else{
                 counts.put(le.getIpAddress(),counts.get(le.getIpAddress())+1);
             }
         }
         return counts;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> commonIps = new ArrayList<String>();
         int highest = 0;
         for (String s : counts.keySet()){
             if (highest < counts.get(s)){
                 highest = counts.get(s);
             }
         }
         for (String s : counts.keySet()){
             if (counts.get(s) == highest){
                 commonIps.add(s);
             }
         }
         return commonIps;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int highest = 0;
         for (String s : counts.keySet()){
             if (highest < counts.get(s)){
                 highest = counts.get(s);
             }
         }
         return highest;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> counts = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records){
             String target = le.getAccessTime().toString().substring(4,10);
             
             if(!counts.containsKey(target)){
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(le.getIpAddress());
                 counts.put(target,ips);
             }
             else{
                 counts.get(target).add(le.getIpAddress());
                 counts.put(target,counts.get(target));
             }
         }
         return counts;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> counts){
         int highest = 0;
         String target = "";
         for (String s : counts.keySet()){
             if (highest < counts.get(s).size()){
                 highest = counts.get(s).size();
                 target = s;
             }
         }
         return target;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> counts,String date){
         ArrayList<String> target = new ArrayList<String>();
         ArrayList<String> result = new ArrayList<String>();
         HashMap<String,Integer> targetMap = new HashMap<String,Integer>();
         int highest = 0;
         for (String s : counts.keySet()){
             if (s.equals(date)){
                 target = counts.get(s);
                 break;
             }
         }
         
         for (String s : target){
             if (!targetMap.containsKey(s)){
                 targetMap.put(s,1);
             }
             else{
                 targetMap.put(s,targetMap.get(s)+1);
             }
         }
         
         for (String s : targetMap.keySet()){
             if (highest < targetMap.get(s)){
                 highest = targetMap.get(s);
             }
         }
           
         for (String s : targetMap.keySet()){
             if (highest == targetMap.get(s)){
                 result.add(s);
             }
         }
         
         return result;
     }
}
