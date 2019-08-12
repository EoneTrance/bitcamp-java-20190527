package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {
  public static void main(String[] args) throws Exception {
    System.out.println("[수업관리시스템 서버 애플리케이션 테스트]");

    try (Socket Socket = new Socket("192.168.0.79", 8888);
        ObjectOutputStream out = new ObjectOutputStream(Socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(Socket.getInputStream());
        ){
      System.out.println("서버와 연결되었음.");
      
      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");
      
      out.writeUTF("add");
      out.writeObject(member);
      out.flush();
      System.out.println("add 요청함.");

      // 서버가 보낸 데이터를 읽는다.
      String response = in.readUTF();
      System.out.println("--> " + response);
      
      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("lim@test.com");
      member.setPhoto("lim.gif");
      member.setTel("2222-2222");
      
      out.writeUTF("add");
      out.writeObject(member);
      out.flush();
      System.out.println("add 요청함.");
      
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("--> " + response);
      
      out.writeUTF("list");
      out.flush();
      System.out.println("list 요청함");
      
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("--> " + response);
      
      @SuppressWarnings("unchecked")
      List<Member> list = (List<Member>)in.readObject();
      System.out.println("---------------");
      for (Member m : list) {
        System.out.println(m);
      }
      System.out.println("---------------");
      
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("--> " + response);
      
      response = in.readUTF();
      System.out.println("--> " + response);
      
      out.writeUTF("quit");
      out.flush();
      System.out.println("quit 요청함");
      
      // 서버가 보낸 데이터를 읽는다.
      response = in.readUTF();
      System.out.println("--> " + response);
      

    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("서버와 연결 끊음.");
  }
}
