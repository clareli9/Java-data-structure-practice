
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    
    public void tester(){
       // FileResource fr = new FileResource();
        //getNames(fr);
        //totalBirths(fr);
        //int rank = getRank(fr,"Frank","M");
        //String name = getName(fr,450,"M");
        //whatIsNameInYear(fr,"Owen",2014,"M");
        yearOfHighestRank("Genevieve","F");
       // getAverageRank("Robert","M");
        //getTotalBirthsRankedHigher(fr,"Emily","F");
        
        
    }
    
    public void getNames(FileResource fr){
        int totalNames = 0;
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        
        for(CSVRecord rec : fr.getCSVParser(false)){
            totalNames ++;
            if(rec.get(1).equals("M")){
                totalBoyNames ++;
            }
            else{
                totalGirlNames ++;
            }
        }
        
        System.out.println(totalNames + " M: " + totalBoyNames + " F: " + totalGirlNames);
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        //int boyBorn = 0;
        //int girlBorn = 0;
        int numBorn = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")){
               // boyBorn = Integer.parseInt(rec.get(2));
                totalBoys += numBorn;
            }
            else{
               // girlBorn = Integer.parseInt(rec.get(2));
                totalGirls += numBorn;
            }
            //numBorn = Integer.parseInt(rec.get(2));
            //totalBirths += numBorn;
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girls = " + totalGirls);
    }
    
    public int getRank(FileResource fr,String name,String gender){
        int rank = -1;
        int count = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if(!(rec.get(1).equals(gender))){
                rank = -1;
            }
            else{
                count ++;
                if(rec.get(0).equals(name)){
                    rank = count;
                    break;
                }
            }
        }
        //System.out.println(rank);
        return rank;
    }
    
    public String getName(FileResource fr,int rank,String gender){
        int count = 1;
        String name = "";
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                if (count == rank){
                   //  System.out.println(rec.get(0));
                   // return rec.get(0);
                    name = rec.get(0);
                    break;
                }
                else{
                    count ++;
                }
            }
        }
        if (count > rank){
            System.out.println("NO NAME");
            return "NO NAME";
        }
        else{
            System.out.println(name);
            return name;
        }

    }
    
    // Fixed !
    public void yearOfHighestRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int currentRank = 0;
        int highestRank = 0;
        String highestYear = "";

        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            currentRank = getRank(fr,name,gender);
            if(currentRank != -1){
            if(highestRank == 0){
                highestRank = currentRank;
            }
            else{
                if(highestRank > currentRank){
                    highestRank = currentRank;
                    highestYear = f.getName();
                }
            }
        }
    }
        
        System.out.println(highestRank);
    }
    
    public void whatIsNameInYear(FileResource fr,String name,int newYear,String gender){
        int rank = getRank(fr,name,gender);
        //String frame = "yob" + newYear + ".csv";
        FileResource newFr = new FileResource();
        String newName = getName(newFr,rank,gender);
        System.out.println(name + " would be " + newName + " if born in " + newYear);
    }
    
    public void getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        double rank = 0;
        double count = 0;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            if(getRank(fr,name,gender) != -1){
               
               rank += (double)(getRank(fr,name,gender));
               count ++;
            }
        }
        
        if(count == 0){
            count = 1;
        }
        
        System.out.println(rank/count);
        //return (rank/count);
    }
    
    public void getTotalBirthsRankedHigher(FileResource fr,String name,String gender){
        int rank = getRank(fr,name,gender);
        int count = 1;
        int num = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rank == -1){
                break;
            }
            else{
                if(count < rank){
                    num += Integer.parseInt(rec.get(2));
                    count ++;
                }
            }
        }
        
        System.out.println(num);
    }
    
}
