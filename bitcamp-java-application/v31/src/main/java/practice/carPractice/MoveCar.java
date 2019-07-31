package practice.carPractice;

public abstract class MoveCar implements Car {
  
  @Override
  public boolean start(int lightColor) {
    return lightColor == GREEN;
  }

}
