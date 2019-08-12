package practice.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {
  String name = "";
  Scanner scan = new Scanner(System.in); 
  ExecutorService executorService = Executors.newCachedThreadPool();

  class Output implements Runnable {

    @Override
    public void run() {
      try (DatagramSocket socket = new DatagramSocket(8888)) {

        byte[] buf = new byte[1024];

        DatagramPacket emptyPacket = new DatagramPacket(buf, buf.length);

        while (true) {
          socket.receive(emptyPacket);

          String message = new String(
              emptyPacket.getData(), 0, emptyPacket.getLength(), "UTF-8");
          System.out.println(message);

        }

      } catch (Exception e) {
        System.out.println("서버 오류");
        e.printStackTrace();
      }

    }

  }

  class Input implements Runnable {

    @Override
    public void run() {
      while (name.equals("") || name.length() > 10) {
        System.out.println("아이디를 입력하세요 (최대10자)");
        name = scan.nextLine();
      }
      
      try (DatagramSocket socket = new DatagramSocket()) {
        while (true) {
          System.out.print("chat> ");
          String chat = name + "> " + scan.nextLine();

          byte[] bytes = chat.getBytes("UTF-8");

          DatagramPacket packet = new DatagramPacket(
              bytes, bytes.length, InetAddress.getByName("192.168.0.53"), 8888);

          socket.send(packet);
        }

      } catch (Exception e) {
        e.printStackTrace();
      }

    }

  }

  public void executor() {
    System.out.println("메뉴 선택");
    while (true) {
      System.out.println("1.입력창   2.출력창");
      String choice = scan.nextLine();
      if (choice.equals("1")) {
        executorService.submit(new Input());
        break;
      } else if (choice.equals("2")) {
        executorService.submit(new Output());
        break;
      } else {
        System.out.println("잘못된 번호");
      }
    }
  }

  public static void main(String[] args) {
    ChatClient cc = new ChatClient();
    cc.executor();
  }
}
