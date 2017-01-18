
/**
 * Write a description of StoreGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class StoreGene {
    public String load(){
        FileResource page = new FileResource("GRch38dnapart.fa");
        String source = page.asString();
        
        return source;
    }
    
    public int findStartIndex(String dna, int index){
        int start = dna.indexOf("atg",index);
        return start;
    }
    
    public int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("TGA",index);
        if(stop1 == -1 || (stop1 - index)%3 != 0){
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("TAA",index);
        if(stop2 == -1 || (stop2 - index)%3 != 0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("TAG",index);
        if(stop3 == -1 || (stop3 - index)%3 != 0){
            stop3 = dna.length();
        }
        
        return Math.min(stop1, Math.min(stop2, stop3));
        
    }
    public StorageResource res = new StorageResource();
    public void testStorageFinder(String dna){
        int num = 0;
        //boolean cont = true;
        int start = 0;
        int num1 = 0;
        //int stop = 0;
       // ArrayList list = new ArrayList();
        while(true){
            int tag = dna.indexOf("ATG",start);
           // start = findStartIndex(dna,start);
            if(tag == -1){
                break;
            }
            int end = findStopIndex(dna, tag + 3);
            
            //stop = findStopIndex(dna,start);
            if(end != dna.length()){
               // if(cgRatio(dna.substring(tag,end+3))>0.35){
                   // num1 ++;
                //}
                System.out.println(dna.substring(tag,end+3));
                res.add(dna.substring(tag,end+3));
                start = end + 3;
                num ++;
            }
            else{
                start = start + 3;
            }
        }
        System.out.println(num);
        //System.out.println(num1);
    }
    
    public double cgRatio(String dna){
        double count = 0;
        //int count_g = 0;
        int length = dna.length();
        double l = (double) length;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                count ++;
            }
        }
        
        return count/l;
            
    }
    public int findCodon(String dna){
        int count = 0;
        int start = 0;
        while(true){
            int tag = dna.indexOf("CTG",start);
            if(tag == -1){
                break;
            }
            count ++;
            start = tag + 3;
        }
        return count;
    }
    public int findLogest(StorageResource sr){
        int pre = 0;
        for(String gene:sr.data()){
            if(gene.length() >= pre){
                pre = gene.length();
            }
        }
        return pre;
    }
    public void realTesting(){
      String sr = load();
      //String sr = "abcdsad";
      //String sr = "ATGAGTCTCAATAAATTTAAGAAAATTGAAATTGTATCACGCACTCTCTCACATCACAATGGAATAAAACTGAAAATCAACTCCAAAAGGAATCTTCGAAACCATGCAAATACATGGAAATTAAATAACCTGCTCCTGAATGAGCATTGGGTGAAAAACGAAATCAAGATGGAAATGTAAAAAATTTCTTCGAACTGGATGACACAACCTATCAAGACCTCTGGGATACAGCAAAGGCAGTGCTAAGAGGAAAGTTTATAG";
        testStorageFinder(sr);
        //System.out.println(cgRatio(sr));
       // System.out.println(findCodon(sr));
       // System.out.println(sr.length());
       System.out.println(findLogest(res));
    }
}
