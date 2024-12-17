import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D14P1{
  public static int quadrant(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      int quad1 = 0, quad2 = 0, quad3 = 0, quad4 = 0;

      while (data.hasNextLine()){
        line = data.nextLine();
        int x = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.indexOf(",")));
        int y = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" ")));
        int addx = Integer.parseInt(line.substring(line.lastIndexOf("=") + 1, line.lastIndexOf(",")));
        int addy = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1));
        for (int i = 0; i < 100; i++){
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
        }

        if (x < 50){
          if (y < 51){
            quad2++;
          }
          else if (y > 51){
            quad3++;
          }
        }
        else if (x > 50){
          if (y < 51){
            quad1++;
          }
          else if (y > 51){
            quad4++;
          }
        }
      }

      data.close();
      return quad1 * quad2 * quad3 * quad4;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static void main(String[]args){
    System.out.println(quadrant("inputD14.txt"));
  }
}
