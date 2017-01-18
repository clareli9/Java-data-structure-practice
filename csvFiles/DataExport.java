
/**
 * Write a description of DataExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class DataExport {
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
       // countryInfo(parser,"Nauru");
       // listExportersTwoProducts(parser,"cotton","flowers");
        //numberOfExporters(parser,"cocoa");
        bigExporters(parser,"$999,999,999,999");
    }
    
    public void countryInfo(CSVParser parser, String country){
        boolean found = false;
        for(CSVRecord record : parser){
            // Look at the Country column
           
            String export = record.get("Country");
            if(export.contains(country)){
                System.out.println(country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
                found = true;
            }
            else if(!found){
                System.out.println("NOT FOUND");
            }
        
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
            
        }
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                count ++;
            }
        }
        System.out.println(count);
    }
    
    public void bigExporters(CSVParser parser, String amount){
        
        amount = amount.replace("$","");
        amount = amount.replace(",","");
        long value1 = Long.parseLong(amount);
        for(CSVRecord record : parser){
            String value_str = ((record.get("Value (dollars)")).replace("$","")).replace(",","");
            //String value_str = record.get("Value (dollars)");
            long value2 = Long.parseLong(value_str);
            if(value2 > value1){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
}
