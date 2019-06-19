public class TextFileProcessor extends FileAccessor{
  
   private String[] linesArr;
   private int curIndex;
   private static final int DEFAULT_SIZE = 25;
  
   public TextFileProcessor(){
      linesArr = new String[DEFAULT_SIZE];
      curIndex = 0;
   }
   
   public void processLine(String line){
      if (isFull() == true){
         expand();
      }
      if (linesArr[curIndex] == null){
         linesArr[curIndex] = line;
      } else {
         for (String i : linesArr){
            if (i == null){
               i = line;
               break;
            }
         }
      }
      curIndex++;
   }
   
   public String[] getLines(){
      return getLinesCopy();
   }
   
   private boolean isFull(){
      if (curIndex >= linesArr.length - 1){
         return true;
      } else {
         return false;
      }   
   }
   
   private void expand(){
      String[] expandedArr = new String[2 * linesArr.length];
      for (int i = 0; i < linesArr.length; i++){
         expandedArr[i] = linesArr[i];
      }
      linesArr = expandedArr;
   }
   
   private String[] getLinesCopy(){
      int count = 0;
      for (int i = 0; i < linesArr.length; i++){
         if (linesArr[i] != null){
            count++;
         }
      }
      
      int n = 0;
      String[] linesList = new String[count];
      for (int i = 0; i < linesArr.length; i++){
         if (linesArr[i] != null){
            linesList[n] = linesArr[i];
            n++;
         }
      }
      return linesList;
   }
}