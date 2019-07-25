package practice.carPractice;

public class Sedan extends MoveCar {
  
  String name;
  
  public Sedan(String name) {
    this.name = name;
  }
  
  @Override
  public void openDoor() {
    System.out.println("세단 문을 열다.");
  }
  
  @Override
  public String getName() {
    return this.name;
  }
  
}
