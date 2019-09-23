package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@WebServlet("/member/search")
public class MemberSearchServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;

  private MemberDao memberDao;

  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx =
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    memberDao = appCtx.getBean(MemberDao.class);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    try {
      List<Member> members = memberDao.findByKeyword(
          request.getParameter("keyword"));
      request.setAttribute("members", members);
      request.setAttribute("viewUrl","/jsp/member/search.jsp");

    } catch (Exception e) {
      request.setAttribute("message", "데이터 목록을 가져오는데 실패했습니다!");
      request.setAttribute("error", e);
      request.getRequestDispatcher("/jsp/error.jsp").include(request, response);
    }
  }
}
