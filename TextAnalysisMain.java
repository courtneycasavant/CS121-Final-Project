import java.util.Scanner;
import java.io.IOException;

public class TextAnalysisMain{

   public static void main(String[] args) throws IOException{
  
      TextFileProcessor tfproc = new TextFileProcessor();
      TextAnalyzer[] analyzers = getAnalyzers();
      String inputFileName, outputFileName;
      String[] textData = null;
      String reportStr;
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter a text file name to analyze:");
      inputFileName = scan.nextLine();
      try{
         tfproc.processFile(inputFileName);
         textData = tfproc.getLines();
      }
      catch(IOException ioex){
          System.out.println("Error accessing file: "+inputFileName);
          System.out.println(ioex);
      }
      catch(Exception ex){
          System.out.println(ex);
      }
      if(textData!=null) {
      for(int i=0;i<analyzers.length;i++) {
         analyzers[i].analyzeData(textData);
      }
      System.out.println("Analyzed text: " + inputFileName);
      reportStr = getReportStr(analyzers);
      System.out.println(reportStr);
      System.out.println("Enter a file to write report, N to skip. ");
      outputFileName = scan.nextLine();
      if(!outputFileName.equalsIgnoreCase("N"))
         try {
            tfproc.writeToFile(reportStr, outputFileName);
         }
         catch(IOException ioex){
             System.out.println("Error accessing file: "+outputFileName);
             System.out.println(ioex);
         }
      }
   }
   
   /* Creates an array of the text analyzers to be applied to the text file under analysis.
      Add any new analyzers here by incresing the numAnalyzers and adding an instance
      of the new analyzer class to the analyzers array.
   */
   private static TextAnalyzer[] getAnalyzers(){
      int numAnalyzers = 3;
      TextAnalyzer[] analyzers = new TextAnalyzer[numAnalyzers];
      analyzers[0] = new WordPercentagesAnalyzer();
      analyzers[1] = new WordCountAnalyzer();
      analyzers[2] = new LineCountAnalyzer();
      analyzers[3] = new MostFrequentWordsAnalyzer();
      return analyzers;
   }
   
   /* This method collects the reports from all of the analyzers and produces 
      a String for either printing or writing to a file.
    */
   private static String getReportStr(TextAnalyzer[] analyzers){
      StringBuilder sb = new StringBuilder();
      for(TextAnalyzer a: analyzers) {
         sb.append(a.getReportStr()).append(System.getProperty("line.separator"));
      }
      return sb.toString();
   }
          
          
          

}