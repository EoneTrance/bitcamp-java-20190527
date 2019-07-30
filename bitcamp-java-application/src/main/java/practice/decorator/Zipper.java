package practice.decorator;

public class Zipper extends Decorator{

  public Zipper(Clothes clothes) {
    super(clothes);
  }
  
  @Override
  public void parts() {
    this.clothes.parts();
    this.zipper();
    
  }
  
  public void zipper() {
    System.out.println("지퍼를 단다.");
  }

}
