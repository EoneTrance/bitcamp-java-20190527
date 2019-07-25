package practice.carPractice;

public class Truck extends MoveCar{
  
  String name;
  
  public Truck(String name) {
    this.name = name;
  }
  
  @Override
  public void openDoor() {
    System.out.println("트럭 문을 열다.");
  }
  
  @Override
  public String getName() {
    return this.name;
  }

}
