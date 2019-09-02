package com.eomcs.lms;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.util.MyBatisDaoFactory;
import com.eomcs.util.PlatformTransactionManager;
import com.eomcs.util.SqlSessionFactoryProxy;

// Spring IoC Container에게 알려줄 설정 정보를 애노테이션을 이용하여
// 이 클래스에 저장해 둔다.

// com.eomcs.lms 패키지에서 @Component가 붙은 클래스를 찾아 인스턴스를 자동으로 생성하게 한다.
@ComponentScan("com.eomcs.lms")
public class AppConfig {
  
  @Bean // Spring IoC 컨테이너에게 이 메소드를 호출하여 리턴 값을 보관하라고 표시한다.
  private SqlSessionFactory sqlSessionFactory() throws Exception {
    System.out.println("AppConfig.sqlSessionFactory() 호출됨!");
    // MyBatis 객체를 준비한다.
    InputStream inputStream =
        Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml");

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(inputStream));
    
    return sqlSessionFactory;
  }

  // @Bean을 붙인(@Bean으로 표시한) 메소드는 Spring IoC 컨테이너가 호출한다.
  // 이때 Spring IoC 컨테이너가 갖고 있는 값을 받고 싶다면,
  // 파라미터로 그 타입의 변수를 선언하면 된다.
  @Bean
  private PlatformTransactionManager transactionManager(
      SqlSessionFactory sqlSessionFactory) throws Exception {
    System.out.println("AppConfig.transactionManager() 호출됨!");
    // 트랜잭션 관리자를 준비한다.
    PlatformTransactionManager txManager = 
        new PlatformTransactionManager(sqlSessionFactory);
    
    return txManager;
  }
  
  @Bean
  private MyBatisDaoFactory daoFactory(
      SqlSessionFactory sqlSessionFactory) throws Exception {
    System.out.println("AppConfig.daoFactory() 호출됨!");
    // DAO 구현체 생성기를 준비한다.
    MyBatisDaoFactory daoFactory =
        new MyBatisDaoFactory(sqlSessionFactory);
    
    return daoFactory;
  }
  
  @Bean
  private BoardDao boardDao(MyBatisDaoFactory myBatisDaoFactory) {
    System.out.println("AppConfig.boradDao() 호출됨!");
    return myBatisDaoFactory.createDao(BoardDao.class);
  }
  
  @Bean
  private MemberDao memberDao(MyBatisDaoFactory myBatisDaoFactory) {
    System.out.println("AppConfig.memberDao() 호출됨!");
    return myBatisDaoFactory.createDao(MemberDao.class);
  }
  
  @Bean
  private LessonDao lessonDao(MyBatisDaoFactory myBatisDaoFactory) {
    System.out.println("AppConfig.lessonDao() 호출됨!");
    return myBatisDaoFactory.createDao(LessonDao.class);
  }
  
  @Bean
  private PhotoBoardDao photoBoardDao(MyBatisDaoFactory myBatisDaoFactory) {
    System.out.println("AppConfig.photoBoradDao() 호출됨!");
    return myBatisDaoFactory.createDao(PhotoBoardDao.class);
  }
  
  @Bean
  private PhotoFileDao photoFileDao(MyBatisDaoFactory myBatisDaoFactory) {
    System.out.println("AppConfig.photoFileDao() 호출됨!");
    return myBatisDaoFactory.createDao(PhotoFileDao.class);
  }
}
