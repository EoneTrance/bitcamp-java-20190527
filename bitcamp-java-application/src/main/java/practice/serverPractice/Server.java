package practice.serverPractice;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  ObjectInputStream in;
  ObjectOutputStream out;

  public void Service() {

    try (
        ServerSocket socket = new ServerSocket(8888);
        Socket clientSocket = socket.accept()) {
      try (
          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())){

        System.out.println("클라이언트 연결됨.");

        if (in.readObject() != null) {
          out.writeUTF("ok");
          out.flush();
          System.out.println("객체 받아옴");
        }

      } catch (Exception e) {
        System.out.println("오류");
        e.printStackTrace();
      }
    } catch (Exception e) {
      System.out.println("오류");
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {

    Server server = new Server();
    server.Service();

  }
}
