import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D11P2{
  public static long stoneCountAdvanced(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line = data.nextLine();
      String[] convert = line.split(" ");
      ArrayList<String> stones = new ArrayList<String>();

      for (String i : convert){
        stones.add(i);
      }

      for (int i = 0; i < 75; i++){
        for (int j = 0; j < stones.size(); j++){
          if (stones.get(j).equals("0")){
            stones.set(j, "1");
          }
          else if (stones.get(j).length() % 2 == 0){
            stones.add(j, stones.get(j).substring(0, stones.get(j).length() / 2));
            stones.set(j, Long.toString(Long.parseLong(stones.get(j))));
            stones.add(j + 1, stones.get(j + 1).substring(stones.get(j + 1).length() / 2));
            stones.set(j + 1, Long.toString(Long.parseLong(stones.get(j + 1))));
            stones.remove(j + 2);
            j++;
          }
          else{
            stones.set(j, Long.toString(Long.parseLong(stones.get(j)) * 2024));
          }
        }
        System.out.println(i);
      }

      data.close();
      return stones.size();
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }
  public static void main(String[]args){
    System.out.println(stoneCountAdvanced("inputD11.txt"));
  }
}
