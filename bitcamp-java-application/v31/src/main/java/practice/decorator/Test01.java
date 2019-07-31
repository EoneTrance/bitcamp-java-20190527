package practice.decorator;

public class Test01 {
  
  public static void main(String[] args) {
    
    Pants pants = new Pants();
    
    Poket poketPants = new Poket(pants);
    
    poketPants.color = "RED";
    poketPants.size = 30;
    
    poketPants.make();
    
    Shirt shirt = new Shirt();
    
    Zipper zipperPants = new Zipper(pants);
    
    zipperPants.color = "Black";
    zipperPants.size = 28;
    
    zipperPants.make();
    
    Zipper zipperShirt = new Zipper(new Shirt());
    
    zipperShirt.color = "White";
    zipperShirt.size = 95;
    
    zipperShirt.make();
    
  }

}
