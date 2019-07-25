package practice.carPractice;

public class Bike extends MoveCar{

  String name;
  
  public Bike(String name) {
    this.name = name;
  }
  
  @Override
  public void openDoor() {
    System.out.println("오토바이 커버를 벗기다.");
  }
  
  @Override
  public void refueling(int leftFuel) {
    if (leftFuel < 20) {
      System.out.println("기름이 얼마 남지 않았습니다.");
      System.out.println("주유소로 이동");
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

}
