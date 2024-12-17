import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D2P1{
  public static int totalDistance(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      int answer = 0;

      while (data.hasNextLine()){
        line = data.nextLine();
        String[] toconvert = line.split(" ");
        boolean isValid = true;
        int[] levels = new int[toconvert.length];
        for (int i = 0; i < toconvert.length; i++){
          levels[i] = Integer.parseInt(toconvert[i]);
        }

        if (levels[0] < levels[1]){
          for (int i = 0; i < levels.length - 1; i++){
            if (levels[i + 1] > levels[i] + 3 || levels[i + 1] < levels[i] + 1){
              isValid = false;
            }
          }
        }
        else{
          for (int i = 0; i < levels.length - 1; i++){
            if (levels[i + 1] > levels[i] - 1 || levels[i + 1] < levels[i] - 3){
              isValid = false;
            }
          }
        }
        if (isValid){
          answer++;
        }
        isValid = true;
      }

      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }


  public static void main(String[]args){
    System.out.println(totalDistance("inputD2.txt"));
  }
}
