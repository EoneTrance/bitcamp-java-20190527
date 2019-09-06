// 빈 생성과 꺼내기
package ch29.practice;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test_test {
  public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/practice/application-context_test.xml");
    
    System.out.println("---------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------");
    System.out.println(iocContainer.getBean("c1"));
    Method[] methods = iocContainer.getBean("c1").getClass().getDeclaredMethods();
    int methodsCount = 0;
    for (Method m : methods) {
      System.out.println("Method[" + methodsCount++ + "] " + m.getName());
      Type[] types = m.getParameterTypes();
      int parametersCount = 0;
      for (Type t : types) {
        System.out.printf("==> Parameter[%d] %s\n",parametersCount++,t);
      }
      System.out.println();
    }
    
    
  }
}






