package practice.decorator2;

public class Test01 {
    
  public static void main(String[] args) {
    
    InsertNum in = new InsertNum();
    
    CalPlus calPlus = new CalPlus(in);
    
    calPlus.cal(10);
    calPlus.cal(15);
    calPlus.cal(7);
    
    CalMinus calMinus = new CalMinus(in);
    
    calMinus.cal(20);
    
  }
}
