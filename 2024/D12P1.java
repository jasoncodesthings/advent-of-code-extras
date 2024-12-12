import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D12P1{
  public static int trailheadCountAdvanced(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      ArrayList<String> map = new ArrayList<String>();
      int answer = 0;

      while (data.hasNextLine()){
        line = data.nextLine();
        map.add(line);
      }
      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }
  public static void main(String[]args){
    System.out.println(trailheadCountAdvanced("inputD10.txt"));
  }
}
