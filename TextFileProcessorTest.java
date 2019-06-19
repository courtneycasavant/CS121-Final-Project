import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;


public class TextFileProcessorTest {

   private WordCountAnalyzer wca;
   private TextFileProcessor tfp;
   private MostFrequentWordsAnalyzer mfw;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
      wca = new WordCountAnalyzer();
      tfp = new TextFileProcessor();
      mfw = new MostFrequentWordsAnalyzer();
   }


   /** Tests getReportStr() after creation **/
   @Test public void wordCountAnalyzerTest1() {
      String result = wca.getReportStr();
      Assert.assertEquals("WordCountAnalyzer getReportStr method works as expected", "Number of words of length >=4: 0", result);
   }
   
   /** Tests analyzeData() simlpy **/
   @Test public void wordCountAnalyzerTest2() {
      wca.analyzeData(new String[]{"hello", "world", "how", "are", "you"});
      String result = wca.getReportStr();
      Assert.assertEquals("WordCountAnalyzer analyzeData method works as expected", "Number of words of length >=4: 2", result);
   }
   
   /** Tests analyzeData()'s edge cases **/
   @Test public void wordCountAnalyzerTest3() {
      wca.analyzeData(new String[]{"line", "hello, world how!are you?I am really good today. Haha this is a really whacky sentance. a,fit fox.ate a;gr8 big:ham. A small cat(is very happy to)see you. !@#$%^&*()1234567890-=_+[]{}\\|:;\"'>.<,"});
      String result = wca.getReportStr();
      Assert.assertEquals("WordCountAnalyzer analyzeData method works as expected in every edge case", "Number of words of length >=4: 16", result);
   }
   
   /** Tests getLines() after creation **/
   @Test public void textFileProcessorTest1() {
      String[] result = tfp.getLines();
      Assert.assertNotNull("TextFileProcessor getLines does not return null", result);
      Assert.assertEquals("TextFileProcessor getLines returns an empty list", 0, result.length);
   }
   
   /** Tests getLines() after adding **/
   @Test public void textFileProcessorTest2() {
      tfp.processLine("Hello");
      tfp.processLine("world");
      String[] result = tfp.getLines();
      Assert.assertEquals("getLines gets the correct number of lines", 2, result.length);
   }
   
   /** Tests processLine() **/
   @Test public void textFileProcesssorTest3() {
      Set<String> set = new HashSet<String>();
      for(int i = 0; i < 25; i++){
         tfp.processLine("" + i);
         set.add("" + i);
      }
      String[] result = tfp.getLines();
      for(int i = 0; i < 25; i++){
         Assert.assertEquals("TextFileProcessor processLine adds correctly", true, set.contains(result[i]));   
      }
   }
   
   /** Tests isFull()/expand() **/
   @Test public void textFileProcesssorTest4() {
      Set<String> set = new HashSet<String>();
      for(int i = 0; i < 3200; i++){
         tfp.processLine("" + i);
         set.add("" + i);
      }
      String[] result = tfp.getLines();
      for(int i = 0; i < 3200; i++){
         Assert.assertEquals("TextFileProcessor processLine adds correctly", true, set.contains(result[i]));   
      }
   }
   
   /**Tests analyzeData(). If you're getting errors on this method which don't seem 
   *  to make any sense, it's likely due to the fact that your code isn't running analyzeLine
   *  on each line.
   **/
   @Test public void mostFrequentWordsAnalyzerTest1() {
      mfw.analyzeData(new String[]{"oneWord", "twoWord", "threeWord", "fourWord", "fiveWord", "sixWord", "sevenWord", "eightWord", "nineWord", "tenWord", "elevenWord", "twoteenWord", "threeteenWord", "fourteenWord", "fiveteenWord"});
      String result = mfw.getReportStr();
      String sep = System.getProperty("line.separator");
      String str =   "Top 15 most common words." + sep + 
                     "oneWord 1" + sep + 
                     "twoWord 1" + sep + 
                     "threeWord 1" + sep + 
                     "fourWord 1" + sep + 
                     "fiveWord 1" + sep + 
                     "sixWord 1" + sep + 
                     "sevenWord 1" + sep + 
                     "eightWord 1" + sep + 
                     "nineWord 1" + sep + 
                     "tenWord 1" + sep + 
                     "elevenWord 1" + sep + 
                     "twoteenWord 1" + sep + 
                     "threeteenWord 1" + sep + 
                     "fourteenWord 1" + sep + 
                     "fiveteenWord 1" + sep;
      Assert.assertEquals("Tests analyzeData in simple case", str, result);
   }
   
   /**Tests analyzeData(). If you're getting errors on this method which don't seem 
   *  to make any sense, it's likely due to analyzeLine splitting your words incorrctly.
   *  Make sure you are splitting on all intended characters. 
   **/
   @Test public void mostFrequentWordsAnalyzerTest2() {
      mfw.analyzeData(new String[]{"oneWord,twoWord threeWord fourWord.fiveWord;sixWord:sevenWord?eightWord!nineWord(tenWord)elevenWord twoteenWord", "threeteenWord(fourteenWord", "fiveteenWord"});
      String result = mfw.getReportStr();
      String sep = System.getProperty("line.separator");
      String str =   "Top 15 most common words." + sep + 
                     "oneWord 1" + sep + 
                     "twoWord 1" + sep + 
                     "threeWord 1" + sep + 
                     "fourWord 1" + sep + 
                     "fiveWord 1" + sep + 
                     "sixWord 1" + sep + 
                     "sevenWord 1" + sep + 
                     "eightWord 1" + sep + 
                     "nineWord 1" + sep + 
                     "tenWord 1" + sep + 
                     "elevenWord 1" + sep + 
                     "twoteenWord 1" + sep + 
                     "threeteenWord 1" + sep + 
                     "fourteenWord 1" + sep + 
                     "fiveteenWord 1" + sep;
      Assert.assertEquals("Tests analyzeData in simple case", str, result);
   }
   
   /**Tests analyzeData() on plurals. 
   **/
   @Test public void mostFrequentWordsAnalyzerTest3() {
      mfw.analyzeData(new String[]{"oneWord,oneWords,twoWord twoWords twoWords threeWord threeWords fourWord.fiveWord;sixWord:sevenWord?eightWord!nineWord(tenWord)elevenWord twoteenWord", "threeteenWord(fourteenWord", "fiveteenWord"});
      String result = mfw.getReportStr();
      String sep = System.getProperty("line.separator");
      String str =   "Top 15 most common words." + sep + 
                     "twoWord 3" + sep + 
                     "oneWord 2" + sep + 
                     "threeWord 2" + sep + 
                     "fourWord 1" + sep + 
                     "fiveWord 1" + sep + 
                     "sixWord 1" + sep + 
                     "sevenWord 1" + sep + 
                     "eightWord 1" + sep + 
                     "nineWord 1" + sep + 
                     "tenWord 1" + sep + 
                     "elevenWord 1" + sep + 
                     "twoteenWord 1" + sep + 
                     "threeteenWord 1" + sep + 
                     "fourteenWord 1" + sep + 
                     "fiveteenWord 1" + sep;
      Assert.assertEquals("Tests analyzeData in simple case", str, result);
   }
   
   /**Tests analyzeData() on huge amounts of inputs. 
   **/
   @Test public void mostFrequentWordsAnalyzerTest4() {
      ArrayList<String> list = new ArrayList<String>();
      for(int i = 0; i < 3600; i++){
         list.add("" + i + i + i + i + i);
      }
      list.add("11111");
      String[] strs = list.toArray(new String[0]);
      mfw.analyzeData(strs);
      String result = mfw.getReportStr();
      String sep = System.getProperty("line.separator");
      StringBuilder str = new StringBuilder();
      str.append("Top 15 most common words." + sep);
      str.append("11111 2" + sep);
      str.append("00000 1" + sep);
      for(int i = 2; i < 15; i++){
         str.append(("" + i + i + i + i + i) + " 1" + sep);
      }
      Assert.assertEquals("same len", str.toString().length(), result.length());
      Assert.assertEquals("Tests analyzeData in simple case", str.toString(), result);
   }
}
