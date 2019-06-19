public interface TextAnalyzer {

   /* Processes the array of lines from the input file. */
   public void analyzeData(String[] textData);
   
   /* Return the String representation of the analysis. */
   public String getReportStr();
}