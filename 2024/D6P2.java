import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D6P2{
  public static int loopTrack(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line, direction = "north";
      ArrayList<StringBuilder> map = new ArrayList<StringBuilder>();
      ArrayList<String> beenIn = new ArrayList<String>();
      int row = 0, column = 0, answer = 0, resetRow = 0, resetColumn = 0;
      boolean loopActive = false;

      while (data.hasNextLine()){
        line = data.nextLine();
        map.add(new StringBuilder(line));
      }

      for (int i = 0; i < map.size(); i++){
        for (int j = 0; j < map.get(i).length(); j++){
          if (map.get(i).charAt(j) == '^'){
            row = i;
            column = j;
            resetRow = i;
            resetColumn = j;
          }
        }
      }

      for (int i = 0; i < map.size(); i++){
        for (int j = 0; j < map.get(i).length(); j++){
          if (map.get(i).charAt(j) != '#' && map.get(i).charAt(j) != '^'){
            map.get(i).setCharAt(j, 'O');
          }

          while (0 <= row && row < map.size() && 0 <= column && column < map.get(0).length() && !loopActive){
            if (beenIn.contains(row + ", " + column + ", " + direction)){
              loopActive = true;
              answer++;
              System.out.println(answer);
            }
            else{
              map.get(row).setCharAt(column, 'X');
              beenIn.add(row + ", " + column + ", " + direction);
            }

            if (direction.equals("north")){
              if (row == 0 || !(map.get(row - 1).charAt(column) == '#' || map.get(row - 1).charAt(column) == 'O')){
                row--;
              }
              else {
                direction = "east";
              }
            }
            else if (direction.equals("east")){
              if (column == map.get(0).length() - 1 || !(map.get(row).charAt(column + 1) == '#' || map.get(row).charAt(column + 1) == 'O')){
                column++;
              }
              else {
                direction = "south";
              }
            }
            else if (direction.equals("south")){
              if (row == map.size() - 1 || !(map.get(row + 1).charAt(column) == '#' || map.get(row + 1).charAt(column) == 'O')){
                row++;
              }
              else {
                direction = "west";
              }
            }
            else if (direction.equals("west")){
              if (column == 0 || !(map.get(row).charAt(column - 1) == '#' || map.get(row).charAt(column - 1) == 'O')){
                column--;
              }
              else {
                direction = "north";
              }
            }
          }
          loopActive = false;
          beenIn = new ArrayList<String>();
          if (map.get(i).charAt(j) != '#' || map.get(i).charAt(j) != '^'){
            map.get(i).setCharAt(j, '.');
          }
          row = resetRow;
          column = resetColumn;
          direction = "north";
          data = new Scanner(file);
          map = new ArrayList<StringBuilder>();
          while (data.hasNextLine()){
            line = data.nextLine();
            map.add(new StringBuilder(line));
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
    System.out.println(loopTrack("inputD6.txt"));
  }
}
