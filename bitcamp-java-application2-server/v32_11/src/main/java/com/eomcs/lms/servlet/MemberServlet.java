package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.servlet.dao.MemberSerialDao;

public class MemberServlet implements Servlet {

  MemberSerialDao memberDao;
  ObjectInputStream in;
  ObjectOutputStream out;
  
  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    
    memberDao = new MemberSerialDao("./member.ser");
    
  }

  public void saveData() {
    memberDao.saveData();
  }
  
  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/member/add":
        addMember();
        break;
      case "/member/list":
        listMember();
        break;
      case "/member/detail":
        detailMember();
        break;
      case "/member/delete":
        deleteMember();
        break;
      case "/member/update":
        updateMember();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
    
  }
  
  private  void addMember() throws Exception {
    Member member = (Member) in.readObject();
    if (memberDao.insert(member) == 0) {
      fail("회원을 추가할 수 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private  void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(memberDao.findAll());
  }

  private  void detailMember() throws Exception {
    int no = in.readInt();
    Member member = memberDao.findBy(no);
    
    if (member == null) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
    out.writeObject(member);

  }

  private  void updateMember() throws Exception {
    Member member = (Member)in.readObject();

    if (memberDao.update(member) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
    
  }

  private  void deleteMember() throws Exception {
    int no = in.readInt();

    if (memberDao.delete(no) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }

    out.writeUTF("ok");

  }
  
  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }
  
}
