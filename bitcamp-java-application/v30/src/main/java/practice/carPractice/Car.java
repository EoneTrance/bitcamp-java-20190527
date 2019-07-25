package practice.carPractice;

public interface Car {
  
  int RED = 0;
  int GREEN = 1;
  
  boolean start(int lightColor);
  void openDoor();
  default void refueling(int leftFuel) {}
  
  String getName();
}
