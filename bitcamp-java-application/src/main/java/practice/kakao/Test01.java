package practice.kakao;

import java.util.ArrayList;
import java.util.HashMap;

public class Test01 {

  public static void main(String[] args) {
    Solution solution = new Solution();

    String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234",
        "Enter uid1234 Prodo","Change uid4567 Ryan"};

    String[] history = solution.solution(record);
    for (String str : history) {
      System.out.println(str);
    }
  }

}

class Solution {
  public String[] solution(String[] record) {
    String[] answer = {};
    HashMap<String, String> idList = new HashMap<>();
    ArrayList<String> list = new ArrayList<>();

    for (String str : record) {
      if (!str.startsWith("Leave")) {
        String uid = str.substring(str.indexOf(" ") + 1, str.lastIndexOf(" "));
        String nick = str.substring(str.lastIndexOf(" ") + 1);
        idList.put(uid, nick);
      }
    }

    for (String str : record) {
      if (str.startsWith("Enter")) {
        String tempUid = str.substring(str.indexOf(" ")  + 1, str.lastIndexOf(" "));
        list.add(idList.get(tempUid) + "님이 들어왔습니다."); 
      } else if (str.startsWith("Leave")) {
        String tempUid = str.substring(str.indexOf(" ")  + 1);
        list.add(idList.get(tempUid) + "님이 나갔습니다.");
      }
    }
    answer = list.toArray(new String[]{});

    return answer;
  }
}
