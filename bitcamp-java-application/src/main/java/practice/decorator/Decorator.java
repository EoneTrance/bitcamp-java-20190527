package practice.decorator;

public abstract class Decorator extends Clothes{
  
  Clothes clothes;
  
  public Decorator(Clothes clothes) {
    this.clothes = clothes;
  }
  
}
