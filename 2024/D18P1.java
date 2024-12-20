import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D18P1{
  public static int dodgeBytes(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      ArrayList<StringBuilder> map = new ArrayList<StringBuilder>();

      for (int i = 0; i <= 6; i++){
        map.add(new StringBuilder("......."));
      }

      for (int i = 0; i < 12; i++){
        line = data.nextLine();
        int row = Integer.parseInt(line.substring(line.indexOf(",") + 1)), column = Integer.parseInt(line.substring(0, line.indexOf(",")));
        map.get(row).setCharAt(column, '#');
      }
      System.out.println(map);

      data.close();
      return -1;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }
  public static void main(String[]args){
    System.out.println(dodgeBytes("inputD18.txt"));
  }
}
