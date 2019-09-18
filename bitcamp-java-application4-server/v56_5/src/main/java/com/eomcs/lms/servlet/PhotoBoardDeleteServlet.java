package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;

@WebServlet("/photoboard/delete")
public class PhotoBoardDeleteServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;
  // 이 클래스에서 로그를 출력할 일이 있다면 다음과 같이 로거를 만들어 사용하라!
  /*
  private static final Logger logger = 
      LogManager.getLogger(PhotoBoardCommand.class);
   */

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx =
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    photoBoardDao = appCtx.getBean(PhotoBoardDao.class);
    photoFileDao = appCtx.getBean(PhotoFileDao.class);
  }

  @Transactional
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    try {
      int no = Integer.parseInt(request.getParameter("no"));

      if (photoBoardDao.findBy(no) == null) {
        throw new Exception("해당 데이터가 없습니다.");
      } 
      photoFileDao.deleteAll(no);
      photoBoardDao.delete(no);
      response.sendRedirect("/photoboard/list");

    } catch (Exception e) {
      request.setAttribute("message", "데이터 삭제에 실패했습니다!");
      request.setAttribute("refresh", "/photoboard/list");
      request.setAttribute("error", e);
      request.getRequestDispatcher("/error").forward(request, response);;
      response.setContentType("text/html;charset=UTF-8");
    }
  }
}
