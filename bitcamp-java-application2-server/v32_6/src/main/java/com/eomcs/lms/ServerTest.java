package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class ServerTest {

  static ObjectOutputStream out;
  static ObjectInputStream in;
  static Scanner keyScan;
  static int count = 0;

  public static void main(String[] args) throws Exception {
    System.out.println("[수업관리시스템 서버 애플리케이션 테스트]");

    try (Socket Socket = new Socket("192.168.0.34", 8888);
        ObjectOutputStream out = new ObjectOutputStream(Socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(Socket.getInputStream());
        ){

      System.out.println("서버와 연결되었음.");

      // 다른 메소드가 입출력 객체를 사용할 수 있도록 스태틱 변수에 저장한다.
      ServerTest.in = in;
      ServerTest.out = out;
      ServerTest.keyScan = new Scanner(System.in);

      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");

      if (!add(member)) {
        error();
      }
      System.out.println("---------------");

      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("lim@test.com");
      member.setPhoto("lim.gif");
      member.setTel("2222-2222");

      if (!add(member)) {
        error();
      }
      System.out.println("---------------");

      if (!list()) {
        error();
      }
      System.out.println("---------------");

      if (!delete(Integer.valueOf(ServerTest.input("no? ")))) {
        error();
      }
      System.out.println("---------------");

      if (!list()) {
        error();
      }
      System.out.println("---------------");
      
      if (!detail(Integer.valueOf(ServerTest.input("no? ")))) {
        error();
      }
      System.out.println("---------------");
      
      member = new Member();
      member.setNo(2);
      member.setName("홍길동2");
      member.setEmail("hong2@test.com");
      member.setPhoto("hong.gif");
      member.setTel("3333-3333");
      
      if (!update(member)) {
        error();
      }
      System.out.println("---------------");
      
      if (!list()) {
        error();
      }
      System.out.println("---------------");
      
      if (!quit()) {
        error();
      }
      System.out.println("---------------");


    } catch (RequestException e) {
      // 서버에서 요청 처리에 실패했다면
      // 서버가 보낸 이유를 받는다.
      System.out.printf("오류: %s\n", in.readUTF());

    } catch (IOException e) {
      e.printStackTrace();

    }

    System.out.println("서버와 연결 끊음.");
  }

  private static boolean add(Member m) throws IOException, RequestException {
    out.writeUTF("/member/add");
    out.writeObject(m);
    out.flush();
    System.out.print("add 요청함 => ");

    if (!in.readUTF().equals("ok")){
      return false;
    }
    System.out.println("처리 완료!");

    return true;
  }

  @SuppressWarnings("unchecked")
  private static boolean list() throws Exception {
    out.writeUTF("/member/list");
    out.flush();
    System.out.print("list 요청함 => ");

    if (!in.readUTF().equals("ok")){
      return false;
    }
    System.out.println("처리 완료!");
    List<Member> list = (List<Member>) in.readObject();

    System.out.println("---------------");
    for (Member m : list) {
      System.out.println(m);
    }
    //list = null;
    
//    for (int i = 0; i < list.size(); i++) {
//      list.remove(i);
//    }

    return true;
  }

  private static boolean detail(int no) throws Exception {
    out.writeUTF("/member/detail");
    out.writeInt(no);
    out.flush();
    System.out.print("detail 요청함 => ");

    if (!in.readUTF().equals("ok")) {
      return false;
    }
    System.out.println("처리 완료!");
    
    System.out.println(in.readObject());
    
//    @SuppressWarnings("unchecked")
//    List<Member> list = (List<Member>)in.readObject();
//    System.out.println("---------------");
//
//    if (no >= list.size()) {
//      System.out.println("유효하지 않은 인덱스 (size: " + list.size() + ")");
//    } else {
//      Member member = list.get(no);
//      System.out.println(member);
//    }

    return true;
  }
  
  private static boolean update(Member m) throws Exception {
    out.writeUTF("/member/update");
    out.writeObject(m);
    out.flush();
    System.out.print("update 요청함 => ");

    if (!in.readUTF().equals("ok")) {
      return false;
    }
    System.out.println("처리 완료!");
    
    return true;
  }

  private static boolean delete(int no) throws Exception {
    out.writeUTF("/member/delete");
    out.writeInt(no);
    out.flush();
    System.out.print("delete 요청함 => ");

    if (!in.readUTF().equals("ok")){
      return false;
    }
    System.out.println("처리 완료!");

    return true;
  }

  private static boolean quit() throws Exception {
    out.writeUTF("quit");
    out.flush();
    System.out.print("quit 요청함 => ");

    if (!in.readUTF().equals("ok")){
      return false;
    }
    System.out.println("처리 완료!");

    return true;
  }

  private static void error() throws Exception{
    System.out.printf("오류: %s\n", in.readUTF());
  }

  private static String input() {
    System.out.print("명령> ");
    return keyScan.nextLine();
  }

  private static String input(String string) {
    System.out.print("명령> " + string);
    return keyScan.nextLine();
  }

}
