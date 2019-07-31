package practice.decorator3;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test01_1 {

  static class Student implements Serializable {

    private static final long serialVersionUID = 1234L;
    
    int no;
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    double avg;

    public void calSum() {
      this.sum = kor + eng + math;
    }

    public void calAvg() {
      this.avg = sum / 3.0;
    }

    @Override
    public String toString() {
      return "Student [no=" + no + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math="
          + math + ", sum=" + sum + ", avg=" + avg + "]";
    }

  }

  public static void main(String[] args) {

    File file = new File("temp/data.bin");
    try {file.createNewFile();} catch (Exception e) {}
    
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream(file)))) {

      Student stu1 = new Student();

      stu1.no = 1;
      stu1.name = "홍길동";
      stu1.kor = 90;
      stu1.eng = 87;
      stu1.math = 94;
      stu1.calSum();
      stu1.calAvg();
      
      Student stu2 = new Student();

      stu2.no = 2;
      stu2.name = "임꺽정";
      stu2.kor = 80;
      stu2.eng = 83;
      stu2.math = 98;
      stu2.calSum();
      stu2.calAvg();

      out.writeObject(stu1);
      out.writeObject(stu2);
      
      System.out.println("출력 완료.");
      
    } catch (Exception e) {
      System.out.println("파일 쓰는 중 오류 발생");
      e.printStackTrace();
    } finally {
      
    }

  }

}
