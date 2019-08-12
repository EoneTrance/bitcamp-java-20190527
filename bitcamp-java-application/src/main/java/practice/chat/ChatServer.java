package practice.chat;

import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
  
  ExecutorService executorService = Executors.newCachedThreadPool();

  public void execute() {
    try (DatagramSocket socket = new DatagramSocket(8888)) {
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

  public static void main(String[] args) {
    ChatServer cs = new ChatServer();
    cs.execute();
  }

}
