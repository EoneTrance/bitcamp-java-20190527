package practice.iteratorPractice;

public class LinkedList<E> {

  private Node<E> head;
  private Node<E> tail;
  private int size;

  public boolean add(E value) {

    Node<E> node = new Node<>(value);

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

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    Node<E> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }

    return node.value;
  }

  public E set(int index, E value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    Node<E> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }

    E oldVal = node.value;
    node.value = value;

    return oldVal;
  }

  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    Node<E> deleteNode = null;
    if (index == 0) {
      deleteNode = head;
      head = head.next;
    } else {
      Node<E> node = head;
      for (int i = 0; i < index - 1; i++) {
        node = node.next;
      }
      deleteNode = node.next;
      node.next = deleteNode.next;
      if (deleteNode == tail) {
        tail = node;
      }
    }
    E oldVal = deleteNode.value;
    deleteNode.value = null;
    deleteNode.next = null;

    size--;
    
    return oldVal;

  }
  
  public void clear() {
    if (size == 0) {
      return;
    }
    while (head != null) {
      Node<E> deleteNode = head;
      head = head.next;
      
      deleteNode.value = null;
      deleteNode.next = null;
    }
    
    tail = null;
    size = 0;
  }
  
  public int size() {
    return size;
  }

  static class Node<E> {
    E value;
    Node<E> next;

    public Node() {

    }

    public Node(E value) {
      this.value = value;
    }
  }

}
