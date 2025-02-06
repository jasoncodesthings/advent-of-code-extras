import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class D2P1{
  public static int programAlarm(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line = data.nextLine();
      String[] convert = line.split(",");
      ArrayList<Integer> intCode = new ArrayList<Integer>();
      for (String i : convert){
        intCode.add(Integer.parseInt(i));
      }
      int i = 0;
      intCode.set(1, 12);
      intCode.set(2, 2);

      while (intCode.get(i) != 99){
        if (intCode.get(i) == 1 || intCode.get(i) == 2){
          int first = intCode.get(intCode.get(i + 1)), second = intCode.get(intCode.get(i + 2)), change = intCode.get(i + 3);
          if (intCode.get(i) == 1){
            intCode.set(change, first + second);
          }
          else{
            intCode.set(change, first * second);
          }
          i += 3;
        }
        i++;
      }

      data.close();
      //System.out.println(intCode);
      return intCode.get(0);
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static void main(String[]args){
    System.out.println(programAlarm("inputD2.txt"));
  }
}
