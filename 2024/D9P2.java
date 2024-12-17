import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D9P2{
  public static long discChecksumAdvanced(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line = data.nextLine();
      ArrayList<String> idMap = new ArrayList<String>();
      long answer = 0;
      int countID = 0;

      for (int i = 0; i < line.length(); i++){
        if (i % 2 == 0){
          for (int j = 0; j < line.charAt(i) - '0'; j++){
            idMap.add(Integer.toString(countID));
          }
          countID++;
        }
        else{
          for (int j = 0; j < line.charAt(i) - '0'; j++){
            idMap.add(".");
          }
        }
      }

      for (int i = idMap.size() - 1; i > 0; i--){
        if (idMap.get(i) != "."){
          int startNum = i;
          while (idMap.get(startNum).equals(idMap.get(i))){
            startNum--;
            if (startNum == -1){
              break;
            }
          }
          int lengthNum = i - startNum;
          int startFree = freeSpaceCheck(idMap, lengthNum, startNum);
          if (startFree != -1){
            for (int j = startFree; j <= startFree + lengthNum - 1; j++){
              idMap.set(j, idMap.get(i));
            }
            for (int j = startNum + 1; j <= startNum + lengthNum; j++){
              idMap.set(j, ".");
            }
          }
          i = startNum + 1;
        }
      }

      for (int i = 0; i < idMap.size(); i++){
        if (!idMap.get(i).equals(".")){
          answer += i * Integer.parseInt(idMap.get(i));
        }
      }

      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static int freeSpaceCheck(ArrayList<String> idMap, int length, int start){
    for (int i = 0; i < start; i++){
      if (idMap.get(i).equals(".")){
        int end = i;
        while (idMap.get(end).equals(".")){
          end++;
        }
        int lengthFree = end - i;
        if (lengthFree >= length){
          return i;
        }
        i = end;
      }
    }
    return -1;
  }

  public static void main(String[]args){
    System.out.println(discChecksumAdvanced("inputD9.txt"));
  }
}
