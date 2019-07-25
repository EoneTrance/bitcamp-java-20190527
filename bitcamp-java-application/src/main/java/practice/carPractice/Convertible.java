package practice.carPractice;

public class Convertible extends MoveCar{
  
  String name;
  
  public Convertible(String name) {
    this.name = name;
  }
  
  @Override
  public void openDoor() {
    System.out.println("컨버터블 문을 열다.");
  }
  
  @Override
  public String getName() {
    return this.name;
  }

}
