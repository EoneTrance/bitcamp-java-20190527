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
    out.println("<link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>테스트</h1>");
    out.println("<p>개가튼거</p>");
    out.println("<button type='button' class='btn btn-primary btn-lg'>버튼</button>");
    out.println("<button type='button' class='btn btn-info btn-lg'>버튼</button>");
    out.println("<button type='button' class='btn btn-warning btn-lg'>버튼</button>");
    out.println("<button type='button' class='btn btn-success btn-lg'>버튼</button>");
    out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script>");
    out.println("<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'></script>");
    out.println("</body>");
    out.println("</html>");
  }
}
