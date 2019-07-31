package practice.executePractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.util.Input;

public class App {
  
  static Scanner keyScan;
  
  static ArrayList<Lesson> lessonList = new ArrayList<>();

  public static void main(String[] args) {
    
    loadLessonData();
    
    keyScan = new Scanner(System.in);
    
    Input input = new Input(keyScan);
    
    Queue<String> commandQueue = new LinkedList<>();
    Deque<String> commandStack = new ArrayDeque<>();
    
    HashMap<String, Command> commandMap = new HashMap<>();
    
    commandMap.put("/lesson/add", new LessonAddCommand(input, lessonList));
    commandMap.put("/lesson/list", new LessonListCommand(input, lessonList));
    commandMap.put("/lesson/detail", new LessonDetailCommand(input, lessonList));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(input, lessonList));
    commandMap.put("/lesson/update", new LessonUpdateCommand(input, lessonList));
    
    while (true) {
      String command = prompt();
      
      if (command.length() == 0) {
        continue;
      }
      
      Command excutor = commandMap.get(command);
      
      if (command.equals("quit")) {
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandQueue);
      } else if (command.equals("history2")) {
        printCommandHistory(commandStack);
      } else if (excutor != null) {
        excutor.execute();
      } else {
        System.out.println("지원하지 않는 명령어");
      }
      
    }
    
    saveLessonData();
  }

  private static void saveLessonData() {
    File file = new File("./lesson.ser");
    FileOutputStream out = null;
    ObjectOutputStream out2 = null;
    
    try {
      out = new FileOutputStream(file);
      out2 = new ObjectOutputStream(out);
      
      out2.writeObject(lessonList);
    } catch (FileNotFoundException e) {
      System.out.println("파일을 생성할 수 없습니다!");
    } catch (IOException e) {
      System.out.println("파일에 데이터를 쓰는중 오류 발생!");
    } finally {
      try {out2.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
    }
    
  }
  
  @SuppressWarnings("unchecked")
  private static void loadLessonData() {
    File file = new File("./Lesson.ser");
    FileInputStream in = null;
    ObjectInputStream in2 = null;
    
    try {
      in = new FileInputStream(file);
      in2 = new ObjectInputStream(in);
      
      lessonList = (ArrayList<Lesson>) in2.readObject();
      
    } catch (FileNotFoundException e) {
      System.out.println("해당 파일을 찾을 수 없습니다!");
    } catch (Exception e) {
      System.out.println("파일을 읽는중 오류 발생!");
      e.printStackTrace();
    } finally {
      try {in2.close();} catch (Exception e) {}
      try {in.close();} catch (Exception e) {}
    }
  }

  private static void printCommandHistory(Iterable<String> list) {
    Iterator<String> iterator = list.iterator();
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if (++count %5 == 0) {
        System.out.println(":");
        if (keyScan.nextLine().equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  private static String prompt() {
    System.out.println("명령> ");
    return keyScan.nextLine();
  }
  
}
