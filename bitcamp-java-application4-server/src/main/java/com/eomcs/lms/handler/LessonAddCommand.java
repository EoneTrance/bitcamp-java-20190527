package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Date;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonAddCommand implements Command {

  private LessonDao lessonDao;

  public LessonAddCommand(Input input, LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    // 수업 데이터를 저장할 메모리를 Lesson 설계도에 따라 만든다.
    Lesson lesson = new Lesson();

    // 사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
    try {
      out.println("제목? ");
      lesson.setTitle(in.readLine());
      out.println("내용? ");
      lesson.setContents(in.readLine());
      out.println("시작일? ");
      lesson.setStartDate(Date.valueOf(in.readLine()));
      out.println("종료일? ");
      lesson.setEndDate(Date.valueOf(in.readLine()));
      out.println("총수업시간? ");
      lesson.setTotalHours(Integer.valueOf(in.readLine()));
      out.println("일수업시간? ");
      lesson.setDayHours(Integer.valueOf(in.readLine()));
      
      lessonDao.insert(lesson);
      out.println("저장하였습니다.");
      
    } catch (Exception e) {
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}












