package practice.decorator2;

public abstract class Decorator extends Calculator{
  
  Calculator calculator;
  
  public Decorator(Calculator calculator) {
    this.calculator = calculator;
  }

}
