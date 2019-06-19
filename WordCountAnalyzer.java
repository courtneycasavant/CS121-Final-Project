public class WordCountAnalyzer implements TextAnalyzer {

private int numWords = 0;

   public void analyzeData(String[] textData){
      for (String i : textData){
         String words[] = i.split("[,.;:?!() ]");
            for (String j : words){
               if (j.length() >= 4){
                  numWords++;
               }
            }
       }
   }
   
   public String getReportStr(){
      return "Number of words of length >=4: " + numWords;
   }
}
