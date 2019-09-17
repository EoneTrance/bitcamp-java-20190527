package com.eomcs.lms.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

//@Component
public class PhotoBoardCommand2 {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardCommand2(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @RequestMapping("/photoboard/form")
  public void form(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진게시물 등록폼</title></head>");
    out.println("<body><h1>사진게시물 등록폼</h1>");
    out.println("<form action='/photoboard/add'>");
    out.println("제목: <input type='text' name='title'><br>");
    out.println("수업: <input type='text' name='lessonNo'><br>");
    out.println("사진1: <input type='text' name='filePath1'><br>");
    out.println("사진2: <input type='text' name='filePath2'><br>");
    out.println("사진3: <input type='text' name='filePath3'><br>");
    out.println("사진4: <input type='text' name='filePath4'><br>");
    out.println("사진5: <input type='text' name='filePath5'><br>");
    out.println("사진6: <input type='text' name='filePath6'><br>");
    out.println("<button>등록</button>");
    out.println("</form");
    out.println("</body></html>");
  }

  @Transactional
  @RequestMapping("/photoboard/add")
  public void add(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 게시물 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'>"
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
  
  @Transactional
  @RequestMapping("/photoboard/delete")
  public void delete(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 게시물 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'>"
        + "</head>");
    out.println("<body><h1>사진 게시물 삭제</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

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

  @RequestMapping("/photoboard/detail")
  public void detail(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 게시물 상세</title></head>");
    out.println("<body><h1>사진 게시물 상세</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      PhotoBoard photoBoard = photoBoardDao.findWithFilesBy(no);

      if (photoBoard == null) {
        out.println("<p>해당 번호의 사진 게시물이 없습니다!</p>");
        
      } else {
        photoBoardDao.increaseViewCount(no);
        out.println("<form action='/photoboard/update'>");
        out.printf("번호: <input type='text' name='no' value='%d' readonly><br>\n",
            photoBoard.getNo());
        out.printf("제목: <input type='text' name='title' value='%s'><br>\n",
            photoBoard.getTitle());
        out.printf("수업: <input type='text' name='lessonNo'  value='%d'><br>\n",
            photoBoard.getLessonNo());
        out.printf("조회수: %d<br>\n",
            photoBoard.getViewCount());

        List<PhotoFile> files = photoBoard.getFiles();
        for (int i = 1; i <= 6; i++) {
          if (i <= files.size()) {
            out.printf("사진%d: <input type='text' name='filePath%d' value='%s'><br>\n",
                i, i, files.get(i-1).getFilePath());

          } else {
            out.printf("사진%d: <input type='text' name='filePath%d'><br>\n",
                i, i);
          }
        }
        out.println("<button>변경</button>");
        out.printf("<a href='/photoboard/delete?no=%d'>삭제</a>\n", photoBoard.getNo());
        out.println("</form>");
      }
    } catch (Exception e) {
      out.println("<p>사진 게시물 조회 실패!</p>");
      throw new RuntimeException(e);

    } finally {
      out.println("</body></html>");
    }
  }

  @RequestMapping("/photoboard/list")
  public void list(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진게시물 목록</title>"
        + "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>"
        + "</head>");
    out.println("<body><h1>사진 게시물 목록</h1>");
    out.println("<a href='/photoboard/form'>새 사진 게시물</a><br>");
    try {
      out.println("<table class='table table-hover'>");
      out.println("<tr>"
          + "<th>번호</th>"
          + "<th>제목</th>"
          + "<th>등록일</th>"
          + "<th>조회수</th>"
          + "<th>수업</th>"
          + "</tr>");
      List<PhotoBoard> list = photoBoardDao.findAll();
      for (PhotoBoard photoBoard : list) {
        out.printf("<tr>"
            + "<td>%d</td>"
            + "<td><a href='/photoboard/detail?no=%d'>%s</a></td>"
            + "<td>%s</td>"
            + "<td>%d</td>"
            + "<td>%d</td>"
            + "</tr>\n", 
            photoBoard.getNo(), 
            photoBoard.getNo(), 
            photoBoard.getTitle(), 
            photoBoard.getCreatedDate(), 
            photoBoard.getViewCount(), 
            photoBoard.getLessonNo());
      }
      out.println("<table>");

    } catch (Exception e) {
      out.println("<p>사진 게시물 목록 조회 실패!</p>");
      throw new RuntimeException(e);
    } finally {
      out.println("</body></html>");
    }
  }

  @Transactional
  @RequestMapping("/photoboard/update")
  public void update(ServletRequest request, ServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>사진 게시물 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=/photoboard/list'>"
        + "</head>");
    out.println("<body><h1>사진 게시물 변경</h1>");
    try {
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setNo(Integer.parseInt(request.getParameter("no")));
      photoBoard.setTitle(request.getParameter("title"));
      
      photoBoardDao.update(photoBoard);
      photoFileDao.deleteAll(photoBoard.getNo());
      
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
        out.println("<p>최소 한 개의 사진 파일을 등록해야 합니다.</p>");
        throw new Exception("사진 파일이 없습니다!");
      }
      out.println("<p>수정하였습니다!</p>");
      
    } catch (Exception e) {
      out.println("<p>사진 게시물 변경 실패!</p>");
      throw new RuntimeException(e);
      
    } finally {
      out.println("</body></html>");
    }
  }
}
