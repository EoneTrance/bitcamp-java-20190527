package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import com.eomcs.lms.context.ServletContextListener;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp2 {
  
  ArrayList<ServletContextListener> listeners = new ArrayList<>();
  HashMap<String,Object> servletContext = new HashMap<>();
  int port;
  
  public ServerApp2(int port) {
    this.port = port;
  }
  
  public void execute() {
    System.out.println("[서버 관리 어플리케이션]");
    try (ServerSocket socket = new ServerSocket(this.port)) {
      System.out.println("서버 시작");
      
      for (ServletContextListener listener : listeners) {
        listener.contextInitialized(servletContext);
      }
      
      while (true) {
        try (Socket clientSocket = socket.accept();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())){
          System.out.println("클라이언트 접속됨");
          
          String command = in.readUTF();
          System.out.println(command + "요청 처리중...");
          
          Servlet servlet = null;
          if (command.equals("serverstop")) {
            break;
            
          } else if ((servlet = findServlet(command)) != null) {
            servlet.service(command, in, out);
            
          } else {
            out.writeUTF("fail");
            out.writeUTF("지원하지 않는 명령어");
          }
          out.flush();
          System.out.println("클라이언트에게 응답 완료");
          
        }
        System.out.println("클라이언트와 연결 끊음");
        
      }
      
      for (ServletContextListener listener : listeners) {
        listener.contextDestroyed(servletContext);
      }
      
    } catch (Exception e) {
      
    }
  }

  private void addServletContextListener(ServletContextListener listener) {
    listeners.add(listener);
  }
  
  private Servlet findServlet(String command) {
    Set<String> keys = servletContext.keySet();
    for (String key : keys) {
      if (command.startsWith(key)) {
        return (Servlet) servletContext.get(key);
      }
    }
    return null;
  }
  
  public static void main(String[] args) {
    ServerApp2 server = new ServerApp2(8888);
    server.addServletContextListener(new AppInitListener());
    
    server.execute();
  }
  
  
}
