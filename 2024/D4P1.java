import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D4P1{
  public static int numXMAS(String filename){
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

      for (int i = 0; i < arr.length; i++){
        for (int j = 0; j < arr[i].length(); j++){
          if (arr[i].charAt(j) == 'X'){
            if (j <= arr[i].length() - 4){
              if (i >= 3){
                answer += isXMAS(arr[i].charAt(j), arr[i - 1].charAt(j + 1), arr[i - 2].charAt(j + 2), arr[i - 3].charAt(j + 3));
              }
              if (i <= arr.length - 4){
                answer += isXMAS(arr[i].charAt(j), arr[i + 1].charAt(j + 1), arr[i + 2].charAt(j + 2), arr[i + 3].charAt(j + 3));
              }
              answer += isXMAS(arr[i].charAt(j), arr[i].charAt(j + 1), arr[i].charAt(j + 2), arr[i].charAt(j + 3));
            }

            if (j >= 3){
              if (i >= 3){
                answer += isXMAS(arr[i].charAt(j), arr[i - 1].charAt(j - 1), arr[i - 2].charAt(j - 2), arr[i - 3].charAt(j - 3));
              }
              if (i <= arr.length - 4){
                answer += isXMAS(arr[i].charAt(j), arr[i + 1].charAt(j - 1), arr[i + 2].charAt(j - 2), arr[i + 3].charAt(j - 3));
              }
              answer += isXMAS(arr[i].charAt(j), arr[i].charAt(j - 1), arr[i].charAt(j - 2), arr[i].charAt(j - 3));
            }

            if (i >= 3){
              answer += isXMAS(arr[i].charAt(j), arr[i - 1].charAt(j), arr[i - 2].charAt(j), arr[i - 3].charAt(j));
            }
            if (i <= arr.length - 4){
              answer += isXMAS(arr[i].charAt(j), arr[i + 1].charAt(j), arr[i + 2].charAt(j), arr[i + 3].charAt(j));
            }
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

  public static int isXMAS(char char1, char char2, char char3, char char4){
    if (char1 == 'X' && char2 == 'M' && char3 == 'A' && char4 == 'S'){
      return 1;
    }
    else{
      return 0;
    }
  }

  public static void main(String[]args){
    System.out.println(numXMAS("inputD4.txt"));
  }
}
