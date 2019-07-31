package practice.interfacePractice;

public abstract class ResultCalculator implements Calculator {
  
private int result;
  
  String sum(int num, String symbol) {
    if (symbol.equals("+")) {
      this.result += num;
    } else if (symbol.equals("-")) {
      this.result -= num;
    } else if (symbol.equals("*")) {
      this.result *= num;
    } else if (symbol.equals("/")) {
      this.result /= num;
    } else {
      throw new NumberFormatException ("잘못된 기호: " + symbol);
    }
    return "result = " + this.result;
  }
  
}
