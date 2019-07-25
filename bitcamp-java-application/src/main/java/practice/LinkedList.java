package practice;

public class LinkedList<T> {

  Node<T> head;
  Node<T> tail;
  int size;

  public boolean add(T value) {
    Node<T> node = new Node<T>(value);
    if (head == null) {
      head = node;
    }
    if (tail != null) {
      tail.next = node;
    }
    tail = node;
    return true;
  }

  public Object get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("index 번호: " + index + "는 유효하지 않은 인덱스");
    }

    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }

    return node.value;
  }

  public T set(int index, T value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("index 번호: " + index + "는 유효하지 않은 인덱스");
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
      throw new IndexOutOfBoundsException("index 번호: " + index + "는 유효하지 않은 인덱스");
    }
    Node deleteNode = null;
    if (index == 0) {
      deleteNode = head;
    } else {
      Node node = head;
      for (int i = 0; i < index - 1; i++) {
        node = node.next;
      }
      deleteNode = node.next;
      node.next = deleteNode.next;
      if (deleteNode == tail) {
        tail = node;
      }
    }
    Object oldValue = deleteNode.value;
    deleteNode.value = null;
    deleteNode.next = null;

    size--;

    return oldValue;

  }

  public void clear() {
    if (size == 0) {
      return;
    }
    while (head != null) {
      Node deleteNode = head;
      head = head.next;
      
      deleteNode.value = null;
      deleteNode.next = null;
    }
    
    tail = null;
    size = 0;

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
