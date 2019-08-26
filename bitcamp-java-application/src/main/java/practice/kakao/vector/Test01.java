package practice.kakao.vector;

import java.util.ArrayList;

public class Test01 {
  public static void main(String[] args) {
    Solution s = new Solution();
    int[] heights = {6,9,5,7,4};
    int[] result = s.solution(heights);
    for (int i : result) {
      System.out.println(i);
    }
  }
}

//class Solution {
//  public int[] solution(int[] heights) {
//    int[] answer = new int[heights.length];
//    ArrayList<Integer> top = new ArrayList<>();
//    
//    for (int i = heights.length - 1; i >= 0; i--) {
//      for (int j = 0; j < i; j++) {
//        //System.out.println("j=" + heights[j]);
//        //System.out.println("i-j=" + (i-j));
//        if (heights[i] < heights[i-j]) {
//          System.out.println("i-j=" + heights[i-j]);
//          //System.out.println("i=" +j+ heights[i]);
//          //System.out.println("j=" +j+ heights[j]);
//          top.add(j+1);
//        }
//      }
//    }
//    //System.out.println(top.size());
//    for (int i = 0; i < top.size(); i++) {
//      answer[i] = top.remove(top.size() - 1);
//      
//    }
//
//    return answer;
//  }
//}

//class Solution {
//  public int[] solution(int[] heights) {
//    int[] answer = new int[heights.length];
//    ArrayList<Integer> top = new ArrayList<>();
//    for (int t : heights) {
//      top.add(t);
//    }
//    for (int i = heights.length - 1, j = 0; i >= 0; i--, j++) {
//      while (true) {
//        int temp = top.remove(top.size()-1);
//        if (heights[i] < temp) {
//          System.out.println(heights[i]);
//          answer[j] = temp;
//          break;
//        }
//      }
//      
//        //System.out.println(temp);
//        //System.out.println(heights[i]);
//    }
////    for (int i = 0; i < top.size(); i++) {
////      answer[i] = top.remove(top.size() - 1);
////      
////    }
//
//    return answer;
//  }

class Solution {
  public int[] solution(int[] heights) {
    int[] answer = new int[heights.length];
    ArrayList<Integer> top = new ArrayList<>();
    for (int t : heights) {
      top.add(t);
    }
    
    for (int i = 0; i < heights.length; i++) {
      int temp = top.remove(top.size() - 1);
      for (int j = 0; j < top.size(); j++) {
       // int temp2 = top2.remove(top2.size() - 1);
        int temp2 = top.get(j);
        if (temp2 > temp) {
          answer[i] = j+1;
        }
      }
    }
    return answer;
  }
}

