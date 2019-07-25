package practice.ramda;

public class Test01 {
  
  static interface Calculator {
    int sum (int a, int b);
  }
  
  public static void main(String[] args) {
    Calculator cal = (a, b) -> {
      int sum = a + b;
      return sum;
    };
    
    System.out.println(cal.sum(20, 30));
  }

}
