package practice.carPractice;

public class Test02 {
  
  public static void main(String[] args) {
    
    Car sedan = new Sedan("K5");
    Car truck = new Truck("MAN");
    Car convertible = new Convertible("Ferrari");
    Car bike = new Bike("Hayabusa");
    
    display(sedan, Car.GREEN, 19);
    System.out.println();
    display(truck, Car.RED, 20);
    System.out.println();
    display(convertible, Car.GREEN, 39);
    System.out.println();
    display(bike, Car.RED, 10);
    System.out.println();
  }
  
  public static void display(Car car, int lightColor, int leftFuel) {
    car.openDoor();
    if (car.start(lightColor)) {
      System.out.println(car.getName() + "출발");
    } else {
      System.out.println(car.getName() + "정지");
    }
    car.refueling(leftFuel);
  }

}
