package practice.decorator2;

public class CalPlus extends Decorator {

  public CalPlus(Calculator calculator) {
    super(calculator);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void cal(int value) {
    this.calculator.sum += value;
    this.calculator.cal(value);
  }

}
