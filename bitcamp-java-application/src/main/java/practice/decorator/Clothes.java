package practice.decorator;

public abstract class Clothes {
  
  protected int size;
  protected String color;
  
  public void make() {
    this.parts();
    System.out.println("size: " + size);
    System.out.println("color: " + color);
    System.out.println("옷을 만든다.");
    System.out.println();
  }
  
  public abstract void parts();
  
}
