import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D6P1{
  public static int stepTrack(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line, direction = "north";
      ArrayList<StringBuilder> map = new ArrayList<StringBuilder>();
      int row = 0, column = 0, answer = 0;

      while (data.hasNextLine()){
        line = data.nextLine();
        map.add(new StringBuilder(line));
      }

      for (int i = 0; i < map.size(); i++){
        for (int j = 0; j < map.get(i).length(); j++){
          if (map.get(i).charAt(j) == '^'){
            row = i;
            column = j;
          }
        }
      }

      while (0 <= row && row < map.size() && 0 <= column && column < map.get(0).length()){
        map.get(row).setCharAt(column, 'X');
        if (direction.equals("north")){
          if (row == 0 || !(map.get(row - 1).charAt(column) == '#')){
            row--;
          }
          else {
            direction = "east";
          }
        }
        else if (direction.equals("east")){
          if (column == map.get(0).length() - 1 || !(map.get(row).charAt(column + 1) == '#')){
            column++;
          }
          else {
            direction = "south";
          }
        }
        else if (direction.equals("south")){
          if (row == map.size() - 1 || !(map.get(row + 1).charAt(column) == '#')){
            row++;
          }
          else {
            direction = "west";
          }
        }
        else if (direction.equals("west")){
          if (column == 0 || !(map.get(row).charAt(column - 1) == '#')){
            column--;
          }
          else {
            direction = "north";
          }
        }
      }

      for (int i = 0; i < map.size(); i++){
        for (int j = 0; j < map.get(i).length(); j++){
          if (map.get(i).charAt(j) == 'X'){
            answer++;
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

  public static void main(String[]args){
    System.out.println(stepTrack("inputD6.txt"));
  }
}
