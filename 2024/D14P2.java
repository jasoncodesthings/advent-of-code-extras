import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class D14P2{
  public static String quadrant(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      ArrayList<StringBuilder> map = new ArrayList<StringBuilder>();
      StringBuilder add = new StringBuilder();
      for (int i = 0; i < 101; i++){
        add.append('.');
      }
      for (int i = 0; i < 103; i++){
        map.add(new StringBuilder(add.toString()));
      }
      ArrayList<String> info = new ArrayList<String>();

      while (data.hasNextLine()){
        line = data.nextLine();
        int x = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.indexOf(",")));
        int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" ")));
        int addx = Integer.parseInt(line.substring(line.lastIndexOf("=") + 1, line.lastIndexOf(",")));
        int addy = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1));
        info.add(x + "|" + y + "," + addx + " " + addy);

        map.get(y).setCharAt(x, '#');
      }/*
      for (int i = 0; i < 103; i++){
        System.out.println(map.get(i));
      }*/

      for (int i = 0; i < 30000; i++){
        for (int j = 0; j < info.size(); j++){
          int x = Integer.parseInt(info.get(j).substring(0, info.get(j).indexOf("|")));
          int y = Integer.parseInt(info.get(j).substring(info.get(j).indexOf("|") + 1, info.get(j).indexOf(",")));
          int addx = Integer.parseInt(info.get(j).substring(info.get(j).indexOf(",") + 1, info.get(j).indexOf(" ")));
          int addy = Integer.parseInt(info.get(j).substring(info.get(j).indexOf(" ") + 1));
          map.get(y).setCharAt(x, '.');
          for (int l = 0; l < info.size(); l++){
            if (l != j && Integer.parseInt(info.get(l).substring(0, info.get(l).indexOf("|"))) == x && Integer.parseInt(info.get(l).substring(info.get(l).indexOf("|") + 1, info.get(l).indexOf(","))) == y){
              map.get(y).setCharAt(x, '#');
            }
          }
          x += addx;
          if (x < 0){
            x += 101;
          }
          else if (x > 100){
            x -= 101;
          }
          y += addy;
          if (y < 0){
            y += 103;
          }
          else if (y > 102){
            y -= 103;
          }
          map.get(y).setCharAt(x, '#');
          info.set(j, x + "|" + y + "," + addx + " " + addy);
        }
        /*
        for (int k = 0; k < 103; k++){
          System.out.println(map.get(k));
        }
        System.out.println(i + "\n\n");
        */
        for (int k = 0; k < map.get(0).length(); k++){
          int botCount = 0;
          for (int m = 0; m < map.size(); m++){
            if (map.get(m).charAt(k) == '#'){
              botCount++;
            }
          }
          if (botCount >= 30){
            for (int n = 0; n < 103; n++){
              System.out.println(map.get(n));
            }
            System.out.println((i + 1) + " is looking sus.");
            break;
          }
        }
      }


      data.close();
      return "Now look for the first christmas tree and find the number directly below it!";
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return "";
    }
  }

  public static void main(String[]args){
    System.out.println(quadrant("inputD14.txt"));
  }
}
