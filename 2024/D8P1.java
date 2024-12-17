import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D8P1{
  public static int antinodeCount(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      ArrayList<StringBuilder> map = new ArrayList<StringBuilder>();
      ArrayList<String> answer = new ArrayList<String>();

      while (data.hasNextLine()){
        line = data.nextLine();
        map.add(new StringBuilder(line));
      }

      for (int i = 0; i < map.size(); i++){
        for (int j = 0; j < map.get(0).length(); j++){
          if (map.get(i).charAt(j) != '.'){
            possibleNodes(answer, map, map.get(i).charAt(j));
          }
        }
      }

      data.close();
      return answer.size();
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static int possibleNodes(ArrayList<String> answer, ArrayList<StringBuilder> map, char frequency){
    ArrayList<String> coords = new ArrayList<String>();
    for (int i = 0; i < map.size(); i++){
      for (int j = 0; j < map.get(0).length(); j++){
        if (map.get(i).charAt(j) == frequency){
          coords.add(i + "|" + j);
        }
      }
    }

    for (int i = 0; i < coords.size(); i++){
      for (int j = 0; j < coords.size(); j++){
        if (i != j){
          int xi = Integer.parseInt(coords.get(i).substring(coords.get(i).indexOf("|") + 1));
          int yi = Integer.parseInt(coords.get(i).substring(0, coords.get(i).indexOf("|")));
          int xj = Integer.parseInt(coords.get(j).substring(coords.get(j).indexOf("|") + 1));
          int yj = Integer.parseInt(coords.get(j).substring(0, coords.get(j).indexOf("|")));
          int rise = yj - yi;
          int run = xj - xi;
          if (!(answer.contains((-1 * rise + yi) + "|" + (-1 * run + xi))) && boundsCheck(map, -1 * rise + yi, -1 * run + xi)){
            answer.add((-1 * rise + yi) + "|" + (-1 * run + xi));
          }
          if (!(answer.contains((rise + yj) + "|" + (run + xj))) && boundsCheck(map, rise + yj, run + xj)){
            answer.add((rise + yj) + "|" + (run + xj));
          }
        }
      }
    }

    for (String replace : coords){
      map.get(Integer.parseInt(replace.substring(0, replace.indexOf("|")))).setCharAt(Integer.parseInt(replace.substring(replace.indexOf("|") + 1)), '.');
    }
    return -1;
  }

  public static boolean boundsCheck(ArrayList<StringBuilder> map, int x, int y){
    return 0 <= x && x < map.size() && 0 <= y && y < map.get(0).length();
  }

  public static void main(String[]args){
    System.out.println(antinodeCount("inputD8.txt"));
  }
}
