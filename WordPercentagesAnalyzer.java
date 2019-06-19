import java.util.*;
import java.io.*;

public class WordPercentagesAnalyzer implements TextAnalyzer {

   private int[] wordCounts = new int[16];
   private double[] wordPercentages = new double[16];
   private int totalWordCt = 0;
   
   /* Processes the array of lines from the input file. */
   public void analyzeData(String[] textData){
      for(String line : textData)
         analyzeLine(line);
      calculateWordPercentages();
   }
   
   /* Tokenizes the line of text. Each token is a "word". Only words of
      length 1 to 15 are counted. Words of length > 15 are counted with words 
      of length 15. */
   private void analyzeLine(String line){
      String token; int len;
      String[] tokens = line.split("[,.;:?!() ]");
      for(int i=0;i<tokens.length;i++){ 
         token = tokens[i];
         len = token.length();
         if (len >= 15) 
            wordCounts[15]++; 
         else {
            if (len > 0)
               totalWordCt++;
            wordCounts[len]++;
         }
      }
   }
  
  /* Computes the sum of all freqs * their index (i.e. their length) 
     divided by the totalWordCt 
  */
  private double getAvgWordLength(){
    int sum = 0;
    for(int i=0; i<wordCounts.length;i++) 
       sum += (wordCounts[i]*i);
    return sum/(double)totalWordCt;
  }
  
  /* Computes the frequency of a word length divided by the totalWordCt 
  */
  private void calculateWordPercentages(){
      double curPC = 0.0;
      for(int i=0; i<wordCounts.length;i++){
         curPC=wordCounts[i]/(double)totalWordCt;
         wordPercentages[i] = curPC*100;
      }
  }
  
  /* Assemble the report of frequencies by word length. */
  public String getReportStr(){
     StringBuilder sb = new StringBuilder();
     sb.append("Percentage of word occurrences.").append(System.getProperty("line.separator"));
     for(int i = 1; i < wordPercentages.length; i++){
           sb.append("words of length " + i + ": "+ String.format("%.2f", wordPercentages[i]));
           sb.append(System.getProperty("line.separator"));
     }
     return sb.toString();
  }

}
