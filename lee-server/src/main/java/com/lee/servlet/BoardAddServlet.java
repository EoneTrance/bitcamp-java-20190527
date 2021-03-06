package com.lee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.lee.dao.BoardDao;
import com.lee.domain.Board;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;

  private BoardDao boardDao;

  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx =
        (ApplicationContext)getServletContext().getAttribute("iocContainer");
    boardDao = appCtx.getBean(BoardDao.class);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>게시물 목록</title>");
    out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>");
    out.println("<link rel='stylesheet' href='/css/common.css'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<div id='content'>");
    out.println("<h1>게시물 등록폼</h1>");
    out.println("<form action='/board/add' method='post'>");
    out.println("제목: <input type='text' name='title'><br>");
    out.println("회원번호: <input type='text' name='memberNo'><br>");
    out.println("사진번호: <input type='text' name='photoNo'><br>");
    out.println("내용: <input type='text' name='contents'><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</div>");
    out.println("</body></html>");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    try {
      Board board = new Board();
      board.setTitle(request.getParameter("title"));
      board.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
      board.setPhotoNo(Integer.parseInt(request.getParameter("photoNo")));
      board.setContents(request.getParameter("contents"));

      boardDao.insert(board);
      response.sendRedirect("/board/list");

    } catch (Exception e) {
      request.setAttribute("message", "데이터 저장에 실패했습니다!");
      request.setAttribute("refresh", "/board/list");
      request.setAttribute("error", e);
      response.setContentType("text/html;charset=UTF-8");
    }
  }
}
