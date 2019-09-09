package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoBoardCommand2 {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardCommand2(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @RequestMapping("/photoBoard/form")
  public void form(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진게시물 등록폼</title></head>");
    out.println("<body><h1>사진게시물 등록폼</h1>");
    out.println("<form action='/photoBoard/add'>");
    out.println("제목: <input type='text' name='title'><br>");
    out.println("수업: <input type='text' name='lessonNo'><br>");
    out.println("사진1: <input type='text' name='filePath1'><br>");
    out.println("사진2: <input type='text' name='filePath2'><br>");
    out.println("사진3: <input type='text' name='filePath3'><br>");
    out.println("사진4: <input type='text' name='filePath4'><br>");
    out.println("사진5: <input type='text' name='filePath5'><br>");
    out.println("사진6: <input type='text' name='filePath6'><br>");
    out.println("<botton>등록</botton>");
    out.println("</form");
    out.println("</body></html>");
  }

  @Transactional
  @RequestMapping("photoBoard/add")
  public void add(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 게시물 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoBoard/list'>"
        + "</head>");
    out.println("<body><h1>사진 게시물 등록</h1>");
    
    try {
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(request.getParameter("title"));
      photoBoard.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));
      
      photoBoardDao.insert(photoBoard);

      int count = 0;
      for (int i = 1; i <= 6; i++) {
        String filePath = request.getParameter("filePath" + i);
        if (filePath.length() == 0) {
          continue;
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filePath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
      }
      
      if (count == 0) {
        throw new Exception("사진 파일 없음!");
      }
      
      out.println("<p>저장하였습니다.</p>");
      
    } catch (Exception e) {
      out.println("<p>사진 게시물 등록 실패!</p>");
      throw new RuntimeException(e);
      
    } finally {
      out.println("</body></html>");
    }
  }
  
  @RequestMapping("photoBoard/delete")
  public void delete(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 게시물 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoBoard/list'>"
        + "</head>");
    out.println("<body><h1>사진 게시물 삭제</h1>");
    
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      photoBoardDao.delete(no);
      
      if (photoBoardDao.findBy(no) == null) {
        out.println("<p>해당번호의 사진 게시물이 없습니다!</p>");
      } else {
        photoFileDao.deleteAll(no);
        photoBoardDao.delete(no);
        out.println("<p>사진 게시물 삭제 완료!</p>");
      }
      
    } catch (Exception e) {
      out.println("<p>사진 게시물 삭제 실패!</p>");
      throw new RuntimeException(e);
      
    } finally {
      out.println("</body></html>");
    }
  }
  
  @RequestMapping("photoBoard/detail")
  public void detail(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><h1>사진 게시물 상세</h1></head>");
    out.println("<p1>사진 게시물 상세</p1>");
    
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoBoard = photoBoardDao.findWithFilesBy(no);
      
      if (photoBoard == null) {
        out.println("<p>해당 번호의 사진 게시물이 없습니다!</p>");
      } else {
        photoBoardDao.increaseViewCount(no);
        out.println("<form action='/photoBoard/update'>");
        out.printf("번호: <input type='text' name='no' value='%d' readonly><br>",
            photoBoard.getNo());
        out.printf("제목: <input type='text' name='title'  value='%s'><br>",
            photoBoard.getTitle());
        out.printf("수업: <input type='text' name='lessonNo'  value='%d'><br>",
            photoBoard.getLessonNo());
        out.printf("조회수: <input type='text' name='viewCount' value='%d'<br>",
            photoBoard.getViewCount());
        
        List<PhotoFile> files = photoBoard.getFiles();
        for (int i = 1; i <= 6; i++) {
          if (i <= files.size()) {
            out.printf("사진%d: <input type='text' name='filePath%d' value='%s'<br>",
                i, i, files.get(i-1).getFilePath());
            
          } else {
            out.printf("사진%d: <input type='text' name='filePath%d'<br>",
                i, i);
          }
        }
      }
    } catch (Exception e) {
      
    } finally {
      
    }
  }

}
