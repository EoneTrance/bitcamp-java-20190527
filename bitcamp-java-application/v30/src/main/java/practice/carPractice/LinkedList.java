package practice.carPractice;

import java.lang.reflect.Array;

public class LinkedList<T> {
  
  Node<T> head;
  Node<T> tail;
  int size;
  
  public boolean add(T value) {
    Node<T> node = new Node<>(value);
    
    if (head == null) {
      head = node;
    }
    
    if (tail != null) {
      tail.next = node;
    }
    
    tail = node;
    size++;
    
    return true;
  }
  
  public Object get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException ("유효하지 않은 인덱스: " + index);
    }
    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    
    return node.value;
  }
  
  public Object set(int index, T value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException ("유효하지 않은 인덱스: " + index);
    }
    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    T oldValue = node.value;
    node.value = value;
    
    return oldValue;
  }
  
  public Object remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException ("유효하지 않은 인덱스: " + index);
    }
    Node<T> deleteNode = null;
    if (index == 0) {
      deleteNode = head;
      head = deleteNode.next;
    } else {
      Node<T> node = head;
      for (int i = 0; i < index - 1; i++) {
        node = node.next;
      }
      deleteNode = node.next;
      node.next = deleteNode.next;
      
      if (deleteNode == tail) {
        tail = node;
      }
    }
    
    T oldValue = deleteNode.value;
    deleteNode.value = null;
    deleteNode.next = null;
    size--;
    
    return oldValue;
    
  }
  
  public void clear() {
    if (size() == 0) {
      return;
    }
    while (head != null) {
      Node<T> deleteNode = head;
      head = head.next;
      deleteNode.value = null;
      deleteNode.next = null;
    }
    
    tail = null;
    size = 0;
  }
  
  public Object[] toArray() {
    Object[] arr = new Object[size];
    Node<T> node = head;
    int i = 0;
    while (node != null) {
      arr[i++] = node.value;
      node = node.next;
    }
    
    return arr;
  }
  
  @SuppressWarnings("unchecked")
  public T[] toArray(T[] arr) {
    if (arr.length < size) {
      arr = (T[]) Array.newInstance(arr.getClass().getComponentType(), size);
    }
    Node<T> node = head;
    int i = 0;
    while (node != null) {
      arr[i++] = node.value;
      node = node.next;
    }
    if (arr.length > size) {
      arr[size] = null;
    }
    
    return arr;
  }
  
  public int size() {
    return size;
  }
  
  static class Node<T> {
    T value;
    Node<T> next;
    
    public Node() {
      
    }
    
    public Node(T value) {
      this.value = value;
    }
  }

}
