package practice.decorator;

public class Shirt extends Clothes {

  @Override
  public void parts() {
    System.out.println("셔츠를 만든다.");
  }

}
