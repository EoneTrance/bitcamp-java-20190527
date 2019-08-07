package practice.serverPractice;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import com.eomcs.lms.domain.Board;

public class Client {

  public static void main(String[] args) {

    try (
        Socket socket = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버 연결됨.");

      Board board = new Board();

      board.setNo(1);
      board.setContents("java");
      board.setCreatedDate(new Date(System.currentTimeMillis()));

      out.writeObject(board);
      out.flush();

      if (in.readUTF().equals("ok")) {
        System.out.println("성공");
      }

    } catch (Exception e) {
      System.out.println("오");
      e.printStackTrace();
    }


  }

}
