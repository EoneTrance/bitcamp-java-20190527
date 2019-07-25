package practice.carPractice;

public class Test01 {
  
  public static void main(String[] args) {
    
    Sedan sedan = new Sedan("K3");
    Truck truck = new Truck("Volvo");
    Convertible convertible = new Convertible("Lamborghini");
    
    display(sedan, 1);
    System.out.println();
    display(truck, 0);
    System.out.println();
    display(convertible, 0);
    System.out.println();
  }
  
  public static void display(Car car, int lightColor) {
    car.openDoor();
    if (car.start(lightColor)) {
      System.out.println(car.getName() + " 출발");
    } else {
      System.out.println(car.getName() + " 정지");
    }
  }
  
  

}
