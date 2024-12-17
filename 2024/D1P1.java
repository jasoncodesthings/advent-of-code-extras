import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class D1P1{
  public static int totalDistance(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      int answer = 0;
      ArrayList<Integer> first = new ArrayList<Integer>();
      ArrayList<Integer> last = new ArrayList<Integer>();

      while (data.hasNextLine()){
        line = data.nextLine();
        first.add(Integer.parseInt(line.substring(0, line.indexOf(" "))));
        last.add(Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1)));
      }
      Collections.sort(first);
      Collections.sort(last);
      for (int i = 0; i < first.size(); i++){
        answer += Math.abs(first.get(i) - last.get(i));
      }

      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }


  public static void main(String[]args){
    System.out.println(totalDistance("inputD1.txt"));
  }
}
