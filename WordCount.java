public class WordCount implements Comparable {
   String word;
   int count;
   
   public WordCount(String wd){
      word=wd;
      count=1;
   }
   
   public boolean containsWord(String targetWd){
       return (word.equals(targetWd));
   }
   
   public void incCount(){
      count++;
   }
   
   public int getCount(){
      return count;
   }
   
   public int compareTo(Object other){
      WordCount otherCount = (WordCount)other;
      return this.count - otherCount.getCount();
   }
   
   public String toString(){
      return word+" "+count;
   }
}