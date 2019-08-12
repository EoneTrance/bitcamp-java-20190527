package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.servlet.dao.serial.BoardSerialDao;

public class BoardServlet implements Servlet{

  BoardSerialDao boardDao;
  ObjectInputStream in;
  ObjectOutputStream out;

  public BoardServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    
    boardDao = new BoardSerialDao("./board.ser");

  }

  public void saveData() {
    boardDao.saveData();
  }

  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/board/add":
        addBoard();
        break;
      case "/board/list":
        listBoard();
        break;
      case "/board/detail":
        detailBoard();
        break;
      case "/board/delete":
        deleteBoard();
        break;
      case "/board/update":
        updateBoard();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }

  }

  private void addBoard() throws Exception {
    Board board = (Board) in.readObject();
    
    // 서버쪽에서 게시글 생성일을 설정해야 한다.
    // => 클라이언트에서 보내 온 날짜는 조작된 날짜일 수 있기 때문이다.
    board.setCreatedDate(new Date(System.currentTimeMillis()));
    if (boardDao.insert(board) == 0) {
      fail("게시물을 추가할 수 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
  }

  private void listBoard() throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(boardDao.findAll());
  }

  private void detailBoard() throws Exception {
    int no = in.readInt();
    Board board = boardDao.findBy(no);
    
    if (board == null) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
    out.writeObject(board);

  }

  private void updateBoard() throws Exception {
    Board board = (Board)in.readObject();
    
    // 서버쪽에서 게시글 변경일을 설정해야 한다.
    // => 클라이언트에서 보내 온 날짜는 조작된 날짜일 수 있기 때문이다.
    board.setCreatedDate(new Date(System.currentTimeMillis()));
    
    if (boardDao.update(board) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");

  }

  private void deleteBoard() throws Exception {
    int no = in.readInt();
    
    if (boardDao.delete(no) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    
    out.writeUTF("ok");

  }

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
