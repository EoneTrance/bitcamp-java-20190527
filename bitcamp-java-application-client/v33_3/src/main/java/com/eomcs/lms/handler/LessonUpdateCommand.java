package com.eomcs.lms.handler;

import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonUpdateCommand implements Command {

  private LessonDao lessonDao;
  private Input input;

  public LessonUpdateCommand(Input input, LessonDao lessonDao) {
    this.input = input;
    this.lessonDao = lessonDao;

  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    try {
      Lesson lesson = lessonDao.findBy(no);

      if (lesson == null) {
        System.out.println("해당 번호의 수업 데이터가 없습니다!");
        return;
      }

      String str = input.getStringValue("수업명(" + lesson.getTitle() + ")? ");
      if (str.length() > 0) {
        lesson.setTitle(str);
      }

      str = input.getStringValue("수업내용(" + lesson.getContents() + ")? ");
      if (str.length() > 0) {
        lesson.setContents(str);
      }

      lesson.setStartDate(
          input.getDateValue("시작일(" + lesson.getStartDate() + ")? "));

      lesson.setEndDate(
          input.getDateValue("종료일(" + lesson.getEndDate() + ")? "));

      lesson.setTotalHours(
          input.getIntValue("총수업시간(" + lesson.getTotalHours() + ")? "));

      lesson.setDayHours(
          input.getIntValue("일수업시간(" + lesson.getDayHours() + ")? "));

      lessonDao.update(lesson);
      System.out.println("수업 데이터를 변경하였습니다.");

    } catch (Exception e) {
      System.out.println("수업 데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}












