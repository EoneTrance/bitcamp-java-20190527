package practice.kakao.vector;

import java.util.Scanner;

public class Test02 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Solution2 s = new Solution2();
    System.out.print("number: ");
    System.out.println("result: " + s.solution(scan.nextInt()));
  }
}

class Solution2 {
  public String solution(int n) {
      String answer = "";
      
      String[] strList = {"1","2","4"};
      for (int i = 0; i < n; i++) {
        int j = i % 3;
        int o = i / 3;
        System.out.println(o);
//        answer = String.valueOf(i / 3);
        answer = strList[o];
        answer += strList[j];
        
      }
      return answer;
  }
}
