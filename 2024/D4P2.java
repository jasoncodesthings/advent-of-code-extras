import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D4P2{
  public static int numMASMAS(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line, total = data.nextLine();
      while (data.hasNextLine()){
        line = data.nextLine();
        total += ", " + line;
      }
      String[] arr = total.split(", ");
      int answer = 0;

      for (int i = 1; i < arr.length - 1; i++){
        for (int j = 1; j < arr[i].length() - 1; j++){
          if (arr[i].charAt(j) == 'A'){
            answer += isMASMAS(arr[i - 1].charAt(j - 1), arr[i - 1].charAt(j + 1), arr[i + 1].charAt(j - 1), arr[i + 1].charAt(j + 1));
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

  public static int isMASMAS(char char1, char char2, char char3, char char4){
    if (char1 == 'M' && char2 == 'S' && char3 == 'M' && char4 == 'S' ||
    char1 == 'M' && char2 == 'M' && char3 == 'S' && char4 == 'S' ||
    char1 == 'S' && char2 == 'M' && char3 == 'S' && char4 == 'M' ||
    char1 == 'S' && char2 == 'S' && char3 == 'M' && char4 == 'M'){
      return 1;
    }
    else{
      return 0;
    }
  }

  public static void main(String[]args){
    System.out.println(numMASMAS("inputD4.txt"));
  }
}
