import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D2P2{
  public static int totalDistance(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      int answer = 0;

      while (data.hasNextLine()){
        line = data.nextLine();
        String[] toconvert = line.split(" ");
        boolean add = true, after = true;
        int remove = -1;
        int[] levels = new int[toconvert.length];
        for (int i = 0; i < toconvert.length; i++){
          levels[i] = Integer.parseInt(toconvert[i]);
        }

        for (int i = -1; i < levels.length; i++){
          if (i == -1){
            add = isValid(levels);
            if (add){
              i = levels.length;
            }
          }
          else{
            int[] newarr = new int[toconvert.length - 1];
            for (int j = 0; j < toconvert.length - 1; j++){
              if (j < i){
                newarr[j] = levels[j];
              }
              else{
                newarr[j] = levels[j + 1];
              }
            }
            add = isValid(newarr);
            if (add){
              i = levels.length;
            }
          }
        }
        if (add){
          answer++;
        }
      }

      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static boolean isValid(int[] levels){
    if (levels[0] < levels[1]){
      for (int i = 0; i < levels.length - 1; i++){
        if (levels[i + 1] > levels[i] + 3 || levels[i + 1] < levels[i] + 1){
          return false;
        }
      }
    }
    else{
      for (int i = 0; i < levels.length - 1; i++){
        if (levels[i + 1] > levels[i] - 1 || levels[i + 1] < levels[i] - 3){
          return false;
        }
      }
    }
    return true;
  }


  public static void main(String[]args){
    System.out.println(totalDistance("inputD2.txt"));
  }
}
