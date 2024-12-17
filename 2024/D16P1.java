import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class D16P1{
  public static int reindeerMaze(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line, finish = "";
      ArrayList<String> map = new ArrayList<String>();
      ArrayList<String> beenBefore = new ArrayList<String>();
      int row = 0, column = 0;

      while (data.hasNextLine()){
        line = data.nextLine();
        map.add(line);
      }
      for (int i = 0; i < map.size(); i++){
        for (int j = 0; j < map.get(0).length(); j++){
          if (map.get(i).charAt(j) == 'S'){
            row = i;
            column = j;
          }
          else if (map.get(i).charAt(j) == 'E'){
            finish = i + ", " + j;
          }
        }
      }
      System.out.println(map);
      System.out.println(row + ", " + column + ", " + finish);

      //int answer = findPath(map, beenBefore, row, column, finish, 0, 'E');

      data.close();
      return -1;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }
/*
  public static int findPath(ArrayList<String> map, ArrayList<String> beenBefore, int row, int column, String finish, int score, int direction){
    int bestScore = 0;
    if (finish.equals(row + ", " + column)){
      return score;
    }

    if (direction == 'N'){
      if (map.get(row - 1).charAt(column) != '#'){
        beenBefore.add((row - 1) + ", " + column);
        findPath(map, beenBefore, row - 1, column, finish, score + 1, 'N');
      }
    }
  }*/

  public static void main(String[]args){
    System.out.println(reindeerMaze("inputD16.txt"));
  }
}
