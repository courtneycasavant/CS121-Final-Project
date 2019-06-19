import java.util.*;

public class MostFrequentWordsAnalyzer implements TextAnalyzer {

   private static final int DEFAULT_SIZE = 100;
   private int curIndex;
   private WordCount[] wordCounts;
   private int topN = 15;
   
   public MostFrequentWordsAnalyzer(){
      wordCounts = new WordCount[DEFAULT_SIZE];
      curIndex = 0;   
   }
   
   public void analyzeData(String[] textData){
      for(String line : textData)
         analyzeLine(line);
   }
   
   private void analyzeLine(String line){
      String token; int len;
      String[] tokens = line.split("[,.;:?!() ]");
      for(int i=0;i<tokens.length;i++){ 
         token = tokens[i];
         len = token.length();
         if (len > 4) {
            String curWord = stopWord(token); // remove an 's' if last character 
            WordCount wc = getWordCount(curWord);
            if(wc != null){ //increment count of this word if it exists on the wordCounts array
               wc.incCount();
            }
            else{// create a new WordCount object and add to the wordCounts array
               WordCount newWC = new WordCount(curWord);
               addToArray(newWC);
            }
         }
      }
   }
  
   private WordCount getWordCount(String targetWord){
     WordCount tarWord = null;
     WordCount[] wordsOnly = getArrayWordsOnly();
       for (int i = 0; i < wordsOnly.length; i++){
          if (wordsOnly[i].containsWord(targetWord)){
            tarWord = wordsOnly[i];
          }
       }
     return tarWord;
   }
  
   private void addToArray(WordCount wc){ 
      if (isFull() == true){
         expand();
      }
      if (wordCounts[curIndex] == null){
         wordCounts[curIndex] = wc;
      } else {
         for (WordCount i : wordCounts){
            if (i == null){
               i = wc;
               break;
            }
         }
      }
      curIndex++;
   }
   
   private String stopWord(String word){  
      String newWord = word;
      if (word.charAt(word.length() - 1) == 's' || word.charAt(word.length() - 1) == 'S'){
         newWord = word.substring(0, word.length() - 1);
      }
      return newWord;
   }
  
   private void expand(){
      WordCount[] expandedList = new WordCount[2 * wordCounts.length];
      for (int i = 0; i < wordCounts.length; i++){
         expandedList[i] = wordCounts[i];
      }
      wordCounts = expandedList;
   }
  
   private boolean isFull(){
      if (curIndex == wordCounts.length) {
         return true;
      } else {
         return false; 
      }
   }
   
   private WordCount[] getArrayWordsOnly(){
      int index = 0;
      for (WordCount i : wordCounts){
         if (i != null){
            index++;
         }
      }
      int n = 0;
      WordCount[] wordsList = new WordCount[index];
      for (WordCount j : wordCounts){
         if (j != null){
            wordsList[n] = j;
            n++;
         }
      }
      return wordsList;
   }
   
   public String getReportStr(){
     StringBuilder sb = new StringBuilder();
     sb.append("Top "+topN+" most common words.").append(System.getProperty("line.separator"));
     WordCount[] wordsOnly = getArrayWordsOnly();
     Arrays.sort(wordsOnly, Collections.reverseOrder()); 
     for(int i = 0; i < topN; i++){
        sb.append(wordsOnly[i].toString());
        sb.append(System.getProperty("line.separator"));
     }
     return sb.toString();
   }
}
