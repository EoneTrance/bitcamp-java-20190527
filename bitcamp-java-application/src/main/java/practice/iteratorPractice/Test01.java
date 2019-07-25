package practice.iteratorPractice;

public class Test01 {
  
  public static void main(String[] args) throws CloneNotSupportedException {
    Queue<String> que = new Queue<>();
    que.offer("aaa");
    que.offer("bbb");
    que.offer("ccc");
    que.offer("ddd");
    que.offer("eee");
    que.offer("fff");
    que.offer("ggg");
    Queue<String> queClone = que.clone();
    
    Stack<String> stk = new Stack<>();
    stk.push("aaa");
    stk.push("bbb");
    stk.push("ccc");
    stk.push("ddd");
    stk.push("eee");
    stk.push("fff");
    stk.push("ggg");
    Stack<String> stkClone = stk.clone();
    
    LinkedList<String> list = new LinkedList<>();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");
    list.add("fff");
    list.add("ggg");
    
    while (queClone.iterator().hasNext()) {
      System.out.println(queClone.iterator().next());
    }
    System.out.println("=================");
    
    while (stkClone.iterator().hasNext()) {
      System.out.println(stkClone.iterator().next());
    }
    System.out.println("=================");
    
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
    
    
  }
  

}
