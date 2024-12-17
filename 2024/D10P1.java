import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D10P1{
  public static int trailheadCount(String filename){
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

      for (int i = 0; i < map.size(); i++){
        for (int j = 0; j < map.get(0).length(); j++){
          if (map.get(i).charAt(j) == '0'){
            ArrayList<String> beenIn = new ArrayList<String>();
            answer += score(map, beenIn, 0, i, j);
          }
        }
      }

      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static int score(ArrayList<String> map, ArrayList<String> beenIn, int index, int row, int column){
    int add = 0;
    if (index == 9){
      if (!beenIn.contains(row + "|" + column)){
        beenIn.add(row + "|" + column);
        return 1;
      }
    }

    if (row != 0 && Character.getNumericValue(map.get(row - 1).charAt(column)) == index + 1){
      add += score(map, beenIn, index + 1, row - 1, column);
    }
    if (row != map.size() - 1 && Character.getNumericValue(map.get(row + 1).charAt(column)) == index + 1){
      add += score(map, beenIn, index + 1, row + 1, column);
    }
    if (column != 0 && Character.getNumericValue(map.get(row).charAt(column - 1)) == index + 1){
      add += score(map, beenIn, index + 1, row, column - 1);
    }
    if (column != map.get(0).length() - 1 && Character.getNumericValue(map.get(row).charAt(column + 1)) == index + 1){
      add += score(map, beenIn, index + 1, row, column + 1);
    }

    return add;
  }

  public static void main(String[]args){
    System.out.println(trailheadCount("inputD10.txt"));
  }
}
