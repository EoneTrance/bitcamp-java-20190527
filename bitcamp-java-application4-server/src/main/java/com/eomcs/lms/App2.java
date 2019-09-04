package com.eomcs.lms;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.util.RequestMapping;
import com.eomcs.util.RequestMappingHandlerMapping;

public class App2 {
  
  private static final int CONTINUE = 1;
  private static final int STOP = 0;
  int state;
  
  ExecutorService executorService;
  
  ApplicationContext appCtx;
  RequestMappingHandlerMapping requestMappingHandlerMapping;
  
  public App2() {
    state = CONTINUE;
    
    appCtx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    String[] beanNames = appCtx.getBeanDefinitionNames();
    
    System.out.println("생성된 객체들 -------------");
    for (String beanName : beanNames) {
      System.out.printf("%s(%s)", beanName.getClass().getSimpleName(), beanName);
    }
    System.out.println("-----------------------");
    
    requestMappingHandlerMapping = createRequestMappingHandlerMapping();
  }

  private RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
    RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
    
    Map<String,Object> component = appCtx.getBeansWithAnnotation(Component.class);
    
    Collection<?> objList = component.values();
    
    objList.forEach(obj -> {
      Method[] methods = obj.getClass().getMethods();
      for (Method m : methods) {
        RequestMapping anno = m.getAnnotation(RequestMapping.class);
        if (anno == null) {
          continue;
        }
        mapping.addRequestHandler(anno.value(), obj, m);
      }
    });
    
    return mapping;
  }

}
