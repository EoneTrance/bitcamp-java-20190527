package com.lee;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test01")
@SuppressWarnings("serial")
public class Test01 extends GenericServlet{
  
  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html><head><title>테스트</title>");
    out.println("<style>");
    out.println("p{"
        + "color:white;"
        + "}");
    out.println("body{"
        + "background-color:black;"
        + "}");
    out.println("h1{"
        + "color:grey;"
        + "}");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>테스트</h1>");
    out.println("<p>개가튼거</p>");
    out.println("</body>");
    out.println("</html>");
    
  }
}
