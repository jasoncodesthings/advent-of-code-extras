import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D7P2{
  public static long calibrationCountAdvanced(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      ArrayList<String> legit = new ArrayList<String>();
      long answer = 0;

      while (data.hasNextLine()){
        line = data.nextLine();
        long given = Long.parseLong(line.substring(0, line.indexOf(": ")));
        String[] convert = line.substring(line.indexOf(" ") + 1).split(" ");
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (String convNum : convert){
          nums.add(Integer.parseInt(convNum));
        }
        ArrayList<String> actualList = new ArrayList<String>();
        possibleResults(actualList, nums, 0, 0);
        if (actualList.contains(Long.toString(given))){
          legit.add(Long.toString(given));
        }
      }

      for (int i = 0; i < legit.size(); i++){
        answer += Long.parseLong(legit.get(i));
      }
      data.close();
      return answer;
    } catch (FileNotFoundException ex) {
      System.out.println("File not found");
      return -1;
    }
  }

  public static void possibleResults(ArrayList<String> actualList, ArrayList<Integer> nums, int i, long current){
    if (i == nums.size()){
      actualList.add(Long.toString(current));
    }
    else{
      int value = nums.get(i);
      possibleResults(actualList, nums, i + 1, current + value);
      if (current != 0){
        possibleResults(actualList, nums, i + 1, current * value);
      }
      else{
        possibleResults(actualList, nums, i + 1, value);
      }
      possibleResults(actualList, nums, i + 1, Long.parseLong("" + current + value));
    }
  }

  public static void main(String[]args){
    System.out.println(calibrationCountAdvanced("inputD7.txt"));
  }
}
