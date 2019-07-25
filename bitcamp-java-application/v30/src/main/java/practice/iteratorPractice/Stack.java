package practice.iteratorPractice;

public class Stack<E> extends LinkedList<E> implements Cloneable, Iterable<E> {

  @Override
  public Stack<E> clone() throws CloneNotSupportedException {
    Stack<E> temp = new Stack<>();
    for (int i = 0; i < size(); i++) {
      temp.push(get(i));
    }
    
    return temp;
  }
  
  public void push(E value) {
    add(value);
  }
  
  public E pop() {
    return remove(size() - 1);
  }
  
  public boolean empty() {
    return size() == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      @Override
      public boolean hasNext() {
        return size() > 0;
      }
      @Override
      public E next() {
        return pop();
      }
    };
  }
  
  
}
