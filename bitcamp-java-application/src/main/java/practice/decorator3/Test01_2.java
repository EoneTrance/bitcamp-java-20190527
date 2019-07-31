package practice.decorator3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import practice.decorator3.Test01_1.Student;

public class Test01_2 {
  
  public static void main(String[] args) {
    
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
        new FileInputStream("temp/data.bin")))){
      
      Student stu = new Student();
      for (int i = 0; i < 2; i++) {
        stu = (Student)in.readObject();
        System.out.println(stu);
      }
      
    } catch (Exception e) {
      System.out.println("파일 읽는 중 오류 발생");
      e.printStackTrace();
    } finally {
      
    }
    
  }

}
