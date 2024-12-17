import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class D5P2{
  public static int getCorrectedMiddle(String filename){
    try{
      File file = new File(filename);
      Scanner data = new Scanner(file);
      String line;
      ArrayList<Integer> before = new ArrayList<Integer>();
      ArrayList<Integer> after = new ArrayList<Integer>();
      int answer = 0;

      while (data.hasNextLine()){
        line = data.nextLine();
        if (line.contains("|")){
          before.add(Integer.parseInt(line.substring(0, line.indexOf("|"))));
          after.add(Integer.parseInt(line.substring(line.indexOf("|") + 1)));
        }
        else if (line.contains(",")){
          String[] temp = line.split(",");
          int[] nums = new int[temp.length];
          boolean initialValid = true;
          for (int i = 0; i < temp.length; i++){
            nums[i] = Integer.parseInt(temp[i]);
          }
          while (!isValid(nums, before, after)){
            initialValid = false;
            for (int i = 0; i < before.size(); i++){
              if (indexOf(nums, before.get(i)) != -1 && indexOf(nums, after.get(i)) != -1){
                if (indexOf(nums, before.get(i)) > indexOf(nums, after.get(i))){
                  int swap = nums[indexOf(nums, before.get(i))];
                  nums[indexOf(nums, before.get(i))] = after.get(i);
                  nums[indexOf(nums, after.get(i))] = swap;
                }
              }
            }
          }
          if (!initialValid){
            answer += nums[nums.length / 2];
            initialValid = true;
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

  public static int indexOf(int[] nums, Integer value){
    for (int i = 0; i < nums.length; i++){
      if (nums[i] == value){
        return i;
      }
    }
    return -1;
  }

  public static boolean isValid(int[] nums, ArrayList<Integer> before, ArrayList<Integer> after){
    for (int i = 0; i < nums.length; i++){
      for (int j = 0; j < before.size(); j++){
        if (before.get(j) == nums[i]){
          for (int k = i - 1; k >= 0; k--){
            if (nums[k] == after.get(j)){
              return false;
            }
          }
        }

        if (after.get(j) == nums[i]){
          for (int l = i + 1; l < nums.length; l++){
            if (nums[l] == before.get(j)){
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  public static void main(String[]args){
    System.out.println(getCorrectedMiddle("inputD5.txt"));
  }
}
