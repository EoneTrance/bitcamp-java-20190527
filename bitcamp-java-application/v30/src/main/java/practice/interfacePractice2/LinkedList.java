package practice.interfacePractice2;

import java.lang.reflect.Array;

public class LinkedList<E> implements List<E> {

  Node<E> head;
  Node<E> tail;
  int size;

  @Override
  public boolean add(E value) {

    Node<E> node = new Node<E>(value);

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

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    Node<E> node = head;
    for (int i = 0; i < size; i++) {
      node = node.next;
    }

    return node.value;
  }

  @Override
  public E set(int index, E value) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    Node<E> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }

    E oldValue = node.value;
    node.value = value;

    return oldValue;
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    Node<E> deleteNode = null;
    if (index == 0) {
      deleteNode = head;
      head = deleteNode.next;
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
    
    E oldValue = deleteNode.value;

    deleteNode.value = null;
    deleteNode.next = null;

    size--;

    return oldValue;
  }

  @Override
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

  @SuppressWarnings("unchecked")
  @Override
  public E[] toArray(E[] arr) {
    if (arr.length < size) {
      arr = (E[]) Array.newInstance(arr.getClass().getComponentType(), size);
    }
    
    Node<E> node = head;
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
  
  @Override
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
