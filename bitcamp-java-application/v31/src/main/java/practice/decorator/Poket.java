package practice.decorator;

public class Poket extends Decorator {

  public Poket(Clothes clothes) {
    super(clothes);
  }

  @Override
  public void parts() {
    this.clothes.parts();
    this.poket();
    
  }
  
  public void poket() {
    System.out.println("주머니를 단다.");
  }

}
