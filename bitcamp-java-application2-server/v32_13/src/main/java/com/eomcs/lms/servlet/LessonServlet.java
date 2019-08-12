package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.servlet.dao.csv.LessonCsvDao;

public class LessonServlet implements Servlet {

  LessonCsvDao lessonDao;
  ObjectInputStream in;
  ObjectOutputStream out;

  public LessonServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    
    lessonDao = new LessonCsvDao("./lesson.csv");
    
  }

  public void saveData() {
    lessonDao.saveData();
  }
  
  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/lesson/add":
        addLesson();
        break;
      case "/lesson/list":
        listLesson();
        break;
      case "/lesson/detail":
        detailLesson();
        break;
      case "/lesson/delete":
        deleteLesson();
        break;
      case "/lesson/update":
        updateLesson();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }
  
  private  void addLesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();
    
    if (lessonDao.add(lesson) == 0) {
      fail("수업을 추가할 수 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
  }

  private  void listLesson() throws Exception {
    out.writeUTF("ok");
    out.reset();
    out.writeObject(lessonDao.list());
  }

  private  void detailLesson() throws Exception {
    int no = in.readInt();

    Lesson lesson = lessonDao.get(no);
    if (lesson == null) {
      fail("해당 번호의 수업이 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
    out.writeObject(lesson);

  }

  private  void updateLesson() throws Exception {
    Lesson lesson = (Lesson)in.readObject();

    if (lessonDao.set(lesson) == 0) {
      fail("해당 번호의 수업이 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
    
  }

  private  void deleteLesson() throws Exception {
    int no = in.readInt();

    if (lessonDao.remove(no) == 0) {
      fail("해당 번호의 수업이 없습니다.");
      return;
    }

    out.writeUTF("ok");

  }
  
  private  void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
