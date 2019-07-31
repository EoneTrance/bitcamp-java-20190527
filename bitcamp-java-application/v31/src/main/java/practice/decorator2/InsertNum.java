package practice.decorator2;

public class InsertNum extends Calculator {

  @Override
  public void cal(int value) {
    this.sum = value;
  }

}
