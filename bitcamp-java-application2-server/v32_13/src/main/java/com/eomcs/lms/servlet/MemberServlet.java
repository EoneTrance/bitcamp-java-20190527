package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.servlet.dao.csv.MemberCsvDao;

public class MemberServlet implements Servlet {

  MemberCsvDao memberDao;
  ObjectInputStream in;
  ObjectOutputStream out;
  
  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    
    memberDao = new MemberCsvDao("./member.csv");
    
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
    
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if (memberDao.add(member) == 0) {
      fail("회원을 추가할 수 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private  void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(memberDao.list());
  }

  private  void detailMember() throws Exception {
    int no = in.readInt();
    Member member = memberDao.get(no);
    
    if (member == null) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
    out.writeObject(member);

  }

  private  void updateMember() throws Exception {
    Member member = (Member)in.readObject();
    
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if (memberDao.modify(member) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
    
  }

  private  void deleteMember() throws Exception {
    int no = in.readInt();

    if (memberDao.remove(no) == 0) {
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
