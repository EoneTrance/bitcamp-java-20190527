package design_pattern.observer.after3;

public class LightOffCarObserver extends AbstractCarObserver {
  @Override
  public void carStopped() {
    System.out.println("전조등을 끈다.");
    
  }
}
