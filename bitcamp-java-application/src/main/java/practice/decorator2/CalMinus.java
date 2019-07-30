package practice.decorator2;

public class CalMinus extends Decorator{

  public CalMinus(Calculator calculator) {
    super(calculator);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void cal(int value) {
    this.calculator.cal(this.calculator.sum -= value);
    
  }

}
