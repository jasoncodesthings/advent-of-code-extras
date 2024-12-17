import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class D13P2{
  public static long tokenSaverAdvanced(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String lineA, lineB, lineP, skip;
      double xA, yA, xB, yB, xP, yP;
      long answer = 0;

      while (data.hasNextLine()){
        lineA = data.nextLine();
        lineB = data.nextLine();
        lineP = data.nextLine();
        if (data.hasNextLine()){
          skip = data.nextLine();
        }
        xA = Double.parseDouble(lineA.substring(lineA.indexOf("+") + 1, lineA.indexOf(",")));
        yA = Double.parseDouble(lineA.substring(lineA.lastIndexOf("+") + 1));
        xB = Double.parseDouble(lineB.substring(lineB.indexOf("+") + 1, lineB.indexOf(",")));
        yB = Double.parseDouble(lineB.substring(lineB.lastIndexOf("+") + 1));
        xP = Double.parseDouble(lineP.substring(lineP.indexOf("=") + 1, lineP.indexOf(","))) + 10000000000000.0;
        yP = Double.parseDouble(lineP.substring(lineP.lastIndexOf("=") + 1)) + 10000000000000.0;

        /*
        Let n = number of times to press button A and let h = number of times to press button B;
        xAn + xBh = xP;
        yAn + yBh = yP;
        n = (xP - xBh) / xA;
        yA((xP - xBh) / xA) + yBh = yP;
        (yAxP - yAxBh) / xA + yBh = yP;
        h = (yAxP - yPxA) / (yAxB - yBxA);
        n = (xP - xB((yAxP - yPxA) / (yAxB - yBxA))) / xA;
        */

        double pressA = (xP - xB * ((yA*xP - yP*xA) / (yA*xB - yB*xA))) / xA;
        double pressB = (yA*xP - yP*xA) / (yA*xB - yB*xA);
        if (closeEnough(pressA, Math.round(pressA)) && closeEnough(pressB, Math.round(pressB))){
          answer += (pressA * 3) + pressB;
        }
      }

      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static boolean closeEnough(double x, double y){
    return (x == 0.0 && y == 0.0) || (Math.abs(x - y) / y < 0.00000000000001);
  }

  public static void main(String[]args){
    System.out.println(tokenSaverAdvanced("inputD13.txt"));
  }
}
