package practice.interfacePractice;

public class Operator extends ResultCalculator {
  
  @Override
  public void plus(int num) {
    System.out.println(sum(num, "+"));
  }
  
  @Override
  public void minus(int num) {
    System.out.println(sum(num, "-"));
  }
  
  @Override
  public void multiplication(int num) {
    System.out.println(sum(num, "*"));
  }
  
  @Override
  public void division(int num) {
    System.out.println(sum(num, "/"));
  }
  
}
