package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardAddCommand implements Command {
  
  private BoardDao boardDao;
  private Input input;
  
  public BoardAddCommand(Input input, BoardDao boardDao) {
    this.input = new Input();
    this.boardDao = boardDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    
    try {
      Board board = new Board();
      //input.getStringValue("내용?", in, out);
      //out.println("내용? ");
      board.setContents(input.getStringValue("내용?", in, out));
      boardDao.insert(board);
      out.println("저장하였습니다.");
      
    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
      
    }
  }

}
