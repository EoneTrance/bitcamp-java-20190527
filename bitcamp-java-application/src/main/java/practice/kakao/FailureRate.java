package practice.kakao;

import java.util.HashMap;

public class FailureRate {

  public static void main(String[] args) {
    Solution s = new Solution();
    for (int i : s.solution(5, new int[]{1,1,1,1,1,1,1,1})) {
      System.out.println(i);
    }
  }

  static class Solution {
    public int[] solution(int n, int[] stages) {
      int[] answer = new int[n];
      HashMap<Integer,Double> failureRate = new HashMap<>();
      for (int i = 0; i < n; i++) {
        int reachedPlayer = 0;
        int challenger = 0;
        for (int j = 0; j < stages.length; j++) {
          if (i+1 <= stages[j]) reachedPlayer++;
          if (i+1 == stages[j]) challenger++;
        }
        failureRate.put((i+1), reachedPlayer == 0 ? 0 :(double)challenger/reachedPlayer);
      }
      for (int i = 0; i < n; i++) {
        double max = 0.0;
        for (int j = 1; j <= failureRate.size(); j++) {
          if (max < failureRate.get(j)) max = failureRate.get(j);
        }
        for (int j = 1; j <= failureRate.size(); j++) {
          if (max == failureRate.get(j)) {
            answer[i] = j;
            failureRate.put(j, -0.1);
            break;
          }
        }
      }
      return answer;
    }
  }
}