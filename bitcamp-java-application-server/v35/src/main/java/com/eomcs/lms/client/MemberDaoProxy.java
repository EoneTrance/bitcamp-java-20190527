package com.eomcs.lms.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.servlet.dao.MemberDao;

// 서버쪽의 MemberDao를 대행할 프록시 객체를 정의한다.
// => 클라이언트는 이 프록시 객체를 통하여
//    서버쪽의 MemberDao 객체를 사용할 것이다.
// => 보통 서비스를 제공하는 서버쪽에서 프록시 객체를 만들어
//    클라이언트 개발자에게 배포한다.
// => 이런 방식으로 프로그램을 개발할 때 이점은,
//    클라이언트 개발자가 서버와 어떻게 통신해야 하는지 알 필요가 없다는 것이다.
// => 즉, 서버쪽과 통신하는 코드를 캡슐화하여 감추고 대신 메소드를 통해 단순화 시키는 기법이다.
//    이런 설계 기법을 "프록시 패턴(proxy pattern)"이라 한다.
//
// 프록시 패턴
// => 프록시 역할을 수행할 클래스는 실제 일을 하는 클래스와 같은 규칙을 따라야 한다.
//
public class MemberDaoProxy implements MemberDao{

  String host;
  int port;

  public MemberDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public int insert(Member member) throws Exception {

    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();

      if (!in.readUTF().equals("ok")){
        throw new Exception(in.readUTF());
      }
      return 1;

    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {

    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

      out.writeUTF("/member/list");
      out.flush();

      if (!in.readUTF().equals("ok")){
        throw new Exception(in.readUTF());
      }
      return (List<Member>)in.readObject();

    }
  }

  @Override
  public Member findBy(int no) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      if (!in.readUTF().equals("ok")) {
        throw new Exception(in.readUTF());
      }
      return (Member)in.readObject();

    }
  }

  @Override
  public int update(Member member) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

      out.writeUTF("/member/update");
      out.writeObject(member);
      out.flush();

      if (!in.readUTF().equals("ok")) {
        throw new Exception(in.readUTF());
      }
      return 1;

    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

      out.writeUTF("/member/delete");
      out.writeInt(no);
      out.flush();

      if (!in.readUTF().equals("ok")){
        throw new Exception(in.readUTF());
      }
      return 1;

    }
  }
}
