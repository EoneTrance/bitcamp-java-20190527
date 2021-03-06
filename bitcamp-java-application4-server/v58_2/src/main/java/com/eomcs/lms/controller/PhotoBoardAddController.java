package com.eomcs.lms.controller;

import java.util.Collection;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@Component("/photoboard/add")
public class PhotoBoardAddController implements PageController {

  String uploadDir;
  @Resource
  private PlatformTransactionManager txManager;
  @Resource
  private PhotoBoardDao photoBoardDao;
  @Resource
  private PhotoFileDao photoFileDao;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) 
      throws Exception {
    uploadDir = request.getServletContext().getRealPath("/upload/photoboard");
    if (request.getMethod().equalsIgnoreCase("GET")) {
      return "/jsp/photoboard/form.jsp";
    }

    // 트랜잭션 동작을 정의한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    // 정의된 트랜잭션 동작에 따라 작업을 수행할 트랜잭션 객체를 준비한다. 
    TransactionStatus status = txManager.getTransaction(def);
    request.setAttribute("txManager", txManager);
    request.setAttribute("status", status);

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(request.getParameter("title"));
    photoBoard.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    photoBoardDao.insert(photoBoard);

    int count = 0;
    Collection<Part> parts = request.getParts();
    for (Part part : parts) {
      if (!part.getName().equals("filePath") || part.getSize() == 0) {
        continue;
      }
      // 클라이언트가 보낸 파일을 디스크에 저장한다.
      String filename = UUID.randomUUID().toString();
      part.write(uploadDir + "/" + filename);

      // 저장한 파일명을 DB에 입력한다.
      PhotoFile photoFile = new PhotoFile();
      photoFile.setFilePath(filename);
      photoFile.setBoardNo(photoBoard.getNo());
      photoFileDao.insert(photoFile);
      count++;
    }

    if (count == 0) {
      throw new Exception("사진 파일 없음!");
    }

    txManager.commit(status);

    return "redirect:list";
  }
}
