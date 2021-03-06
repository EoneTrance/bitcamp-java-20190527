package com.eomcs.util;

import java.io.InputStream;
import java.util.HashMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.handler.BoardCommand;
import com.eomcs.lms.handler.LessonCommand;
import com.eomcs.lms.handler.LoginCommand;
import com.eomcs.lms.handler.MemberCommand;
import com.eomcs.lms.handler.PhotoBoardCommand;

// 자바 객체를 자동 생성하여 관리하는 역할
// 1단계 : App 클래스에서 객체 생성 코드를 분리하기
public class ApplicationContext01 {
  
  HashMap<String, Object> objPool = new HashMap<>();
  
  public ApplicationContext01() throws Exception {
    // MyBatis 객체 준비
    InputStream inputStream =
        Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml");

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(inputStream));

    // 트랜잭션 관리자를 준비한다.
    PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);

    // DAO 구현체 생성기를 준비한다.
    MyBatisDaoFactory daoFactory = new MyBatisDaoFactory(sqlSessionFactory);

    // Command 객체가 사용할 데이터 처리 객체를 준비한다.
    BoardDao boardDao = daoFactory.createDao(BoardDao.class);
    LessonDao lessonDao = daoFactory.createDao(LessonDao.class);
    MemberDao memberDao = daoFactory.createDao(MemberDao.class);
    PhotoBoardDao photoBoardDao = daoFactory.createDao(PhotoBoardDao.class);
    PhotoFileDao photoFileDao = daoFactory.createDao(PhotoFileDao.class);

    // 클라이언트 명령을 처리할 커맨드 객체를 준비한다.
    objPool.put("/lesson/add", new LessonCommand(lessonDao));
//    objPool.put("/lesson/delete", new LessonDeleteCommand(lessonDao));
//    objPool.put("/lesson/detail", new LessonDetailCommand(lessonDao));
//    objPool.put("/lesson/list", new LessonListCommand(lessonDao));
//    objPool.put("/lesson/update", new LessonUpdateCommand(lessonDao));

    objPool.put("/member/add", new MemberCommand(memberDao));
//    objPool.put("/member/delete", new MemberDeleteCommand(memberDao));
//    objPool.put("/member/detail", new MemberDetailCommand(memberDao));
//    objPool.put("/member/list", new MemberListCommand(memberDao));
//    objPool.put("/member/update", new MemberUpdateCommand(memberDao));
//    objPool.put("/member/search", new MemberSearchCommand(memberDao));

    objPool.put("/board/add", new BoardCommand(boardDao));
//    objPool.put("/board/delete", new BoardDeleteCommand(boardDao));
//    objPool.put("/board/detail", new BoardDetailCommand(boardDao));
//    objPool.put("/board/list", new BoardListCommand(boardDao));
//    objPool.put("/board/update", new BoardUpdateCommand(boardDao));

    objPool.put("/photoboard/add", 
        new PhotoBoardCommand(txManager, photoBoardDao, photoFileDao));
//    objPool.put("/photoboard/delete", 
//        new PhotoBoardDeleteCommand(txManager, photoBoardDao, photoFileDao));
//    objPool.put("/photoboard/detail", 
//        new PhotoBoardDetailCommand(photoBoardDao));
//    objPool.put("/photoboard/list", 
//        new PhotoBoardListCommand(photoBoardDao));
//    objPool.put("/photoboard/update", 
//        new PhotoBoardUpdateCommand(txManager, photoBoardDao, photoFileDao));

    objPool.put("/auth/login", new LoginCommand(memberDao));

  }

  public Object getBean(String name) throws RuntimeException {
    Object obj = objPool.get(name);
    if (obj == null) throw new RuntimeException("해당 이름의 Bean을 찾을 수 없습니다.");
    return obj;
  }

  public void addBean(String name, Object obj) {
    if (name == null || obj == null) return;
    objPool.put(name, obj);
  }

}
