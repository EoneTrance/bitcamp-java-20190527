package practice.interfacePractice2;

public interface List<T> {
  
  boolean add(T value);
  T get(int index);
  T set(int index, T value);
  T remove(int index);
  void clear();
  T[] toArray(T[] arr);
  int size();

}
