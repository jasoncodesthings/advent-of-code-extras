import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class D13P1{
  public static int tokenSaver(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String lineA, lineB, lineP, skip;
      int xA, yA, xB, yB, xP, yP, answer = 0;

      while (data.hasNextLine()){
        lineA = data.nextLine();
        lineB = data.nextLine();
        lineP = data.nextLine();
        if (data.hasNextLine()){
          skip = data.nextLine();
        }
        xA = Integer.parseInt(lineA.substring(lineA.indexOf("+") + 1, lineA.indexOf(",")));
        yA = Integer.parseInt(lineA.substring(lineA.lastIndexOf("+") + 1));
        xB = Integer.parseInt(lineB.substring(lineB.indexOf("+") + 1, lineB.indexOf(",")));
        yB = Integer.parseInt(lineB.substring(lineB.lastIndexOf("+") + 1));
        xP = Integer.parseInt(lineP.substring(lineP.indexOf("=") + 1, lineP.indexOf(",")));
        yP = Integer.parseInt(lineP.substring(lineP.lastIndexOf("=") + 1));
        ArrayList<Integer> possible = new ArrayList<Integer>();
        for (int i = 0; xA * i <= xP && yA * i <= yP; i++){
          if ((xP - (xA * i)) % xB == 0 && (yP - (yA * i)) % yB == 0 && (xP - (xA * i)) / xB == (yP - (yA * i)) / yB){
            possible.add((3 * i) + ((xP - (i * xA)) / xB));
          }
        }
        if (possible.size() != 0){
          answer += Collections.min(possible);
        }
      }

      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }
  
  public static void main(String[]args){
    System.out.println(tokenSaver("inputD13.txt"));
  }
}
