import java.util.Scanner;
import java.io.*;

/**
   This class provides two services: 
   1- Read a text file line by line and provide a processLine method for subclasses to override.
   2- Provide a method to write a report String to a file.
*/
public abstract class FileAccessor{

  public void processFile(String inputFileName) throws IOException { 
    Scanner scan = new Scanner(new FileReader(inputFileName));
    while(scan.hasNext()){
      processLine(scan.nextLine());
    }
    scan.close();
  }
  
  protected abstract void processLine(String line);
  
  public void writeToFile(String report, String outputFileName) throws IOException{
		PrintWriter pw = new PrintWriter(outputFileName);
      pw.print(report);
      pw.close();
   }
}
