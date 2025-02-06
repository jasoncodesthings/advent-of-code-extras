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
      System.out.println(intCode);

      data.close();
      return -1;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static void main(String[]args){
    System.out.println(programAlarm("inputD2.txt"));
  }
}
