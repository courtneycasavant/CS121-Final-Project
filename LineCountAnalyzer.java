public class LineCountAnalyzer implements TextAnalyzer {

   private int lineCount=0;
   
   /* Count lines of length>0. */
   public void analyzeData(String[] textData){
      for(String str : textData)
         if(str.length()>0)
            lineCount++;
   }
   
   /* Return the line count as a String. */  
   public String getReportStr(){
      return "Number of non-blank lines: "+lineCount;
   }

}