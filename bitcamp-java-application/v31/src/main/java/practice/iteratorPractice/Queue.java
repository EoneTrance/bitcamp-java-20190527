package practice.iteratorPractice;

public class Queue<E> extends LinkedList<E> implements Cloneable, Iterable<E>{
  
  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < size(); i++) {
      temp.offer(get(i));
    }
    
    return temp;
  }
  
  public void offer(E value) {
    add(value);
  }
  
  public E poll() {
    return remove(0);
  }
  
  public boolean empty() {
    return size() == 0;
  }
  
  @Override
  public Iterator<E> iterator(){
    return new Iterator<E>() {
      @Override
      public boolean hasNext() {
        return size() > 0;
      }
      
      @Override
      public E next() {
        return poll();
      }
    };
  }
  
}
