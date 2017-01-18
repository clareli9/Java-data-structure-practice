
/**
 * Write a description of FindTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class FindTemp {
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //CSVRecord record = lowestHumidityInFile(parser);
        //testFileWithColdestTemperature();
       // double avgTemp = averageTemperatureInFile(parser);
       // System.out.println(avgTemp);
        averageTemperatureWithHighHumidityInFile(parser, 80);
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        // For each row in CSV File
        for (CSVRecord currentRow : parser){
            if (lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                if(currentTemp != -9999 && currentTemp < lowestTemp){
                    lowestSoFar = currentRow;
                }
            }
        }
       // System.out.println("Time: " + lowestSoFar.get("TimeEST") + " Temp: " + lowestSoFar.get("TemperatureF"));
        return lowestSoFar;
    }
    
    public CSVRecord fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource (f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
                if (currentTemp != -9999 && currentTemp < lowestTemp){
                    lowestSoFar = currentRow;
                }
            }
            
        }
        return lowestSoFar;
    }
    
    public void testFileWithColdestTemperature(){
        CSVRecord lowestSoFar = fileWithColdestTemperature();
        System.out.println("Coldest day was " + lowestSoFar.get("DateUTC") + " And the temp is " + lowestSoFar.get("TemperatureF"));    
    }
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        double currentHum = 0;
        double lowestHum = 0;
        // For each row in CSV File
        for (CSVRecord currentRow : parser){
            if(lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                if(currentRow.get("Humidity").equals("N/A")){
                    break;
                }
                else{
                    currentHum = Double.parseDouble(currentRow.get("Humidity"));
                }
                if(lowestSoFar.get("Humidity").equals("N/A")){
                    break;
                }
                else{
                    lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                }
                
                if(currentHum < lowestHum){
                    lowestSoFar = currentRow;
                }
                
            }
        }
        return lowestSoFar;      
    }
    
    public CSVRecord fileWithLowestHumidity(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        double currentHum = 0;
        double lowestHum = 0;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource (f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                if(currentRow.get("Humidity").equals("N/A")){
                    break;
                }
                else{
                    currentHum = Double.parseDouble(currentRow.get("Humidity"));
                }
                if(lowestSoFar.get("Humidity").equals("N/A")){
                    break;
                }
                else{
                    lowestHum = Double.parseDouble(lowestSoFar.get("Humidity"));
                }
                
                if(currentHum < lowestHum){
                    lowestSoFar = currentRow;
                }
              
            }
            
        }
        return lowestSoFar;
    }
    
    public void testFileWithLowestHumidity(){
        CSVRecord lowestSoFar = fileWithLowestHumidity();
        System.out.println(lowestSoFar.get("Humidity") + " " + lowestSoFar.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser){
        double sumOfTemp = 0;
        double count = 0;
        for(CSVRecord currentRow : parser){
            sumOfTemp += Double.parseDouble(currentRow.get("TemperatureF"));
            count = count + 1;
        }
        
        return (sumOfTemp/count);
    }
    
    public void averageTemperatureWithHighHumidityInFile(CSVParser parser, double value){
        double currentHum = 0;
        double sumOfTemp = 0;
        double count = 0;
        for(CSVRecord currentRow : parser){
            if ((currentRow.get("Humidity")).equals("N/A")){
                currentHum = 0;
            }
            else{
                currentHum = Double.parseDouble(currentRow.get("Humidity"));
            }
            if (currentHum > value){
               sumOfTemp += Double.parseDouble(currentRow.get("TemperatureF"));
               count = count + 1;
            }
            
        }
        if (count == 0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " + sumOfTemp/count);
        }
    }
}
