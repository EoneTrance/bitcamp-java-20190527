// 파일에 출력하기 - Stream API를 사용하여 데이터 출력하기
package ch22.i.character_stream;

import java.io.FileWriter;

public class Test01_2 {
  public static void main(String[] args) {
    // 1 ~ 100 중에서 짝수만 파일에 출력해 보자!

    // 1) 값을 출력할 파일 출력 객체를 준비한다.
    try (FileWriter out = new FileWriter("temp/data.txt")){

      // 2) '가'에서 시작하여 다음 문자를 100자 출력한다.
      for (int i = 0, ch = '가'; i < 100; i++, ch++) {
        out.write((char)(ch));
      }
      System.out.println("출력 완료!");

    } catch (Exception e) {
      System.out.println("파일 출력 예외 발생!");
      e.printStackTrace();
    }

  }
}








