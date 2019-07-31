package design_pattern.observer.after;

public class Test03 {

  public static void main(String[] args) {
    Car car = new Car();
    
    car.addObserver(new SafeBeltCarObserver());
    
    // 예) 2월 29일 - 자동차 시동을 걸 때 엔진 오일 유무를 검사하는 기능을 추가 
    car.addObserver(new EngineOilCarObserver());
    
    car.start();
    car.run();
    car.stop();
    
  }

}








