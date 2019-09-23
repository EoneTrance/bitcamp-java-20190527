<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav id='header' class='navbar navbar-expand-lg navbar-dark bg-primary'>
  <a class='navbar-brand' href='#'>
  <img src='/images/logo.png' class='d-inline-block align-top'>
  수업관리시스템</a>
  <div class='collapse navbar-collapse' id='navbarNav'>
    <ul class='navbar-nav'>
      <li class='nav-item active'>
        <a class='nav-link' href='/board/list'>게시판</a>
      </li>
      <li class='nav-item active'>
        <a class='nav-link' href='/lesson/list'>수업관리</a>
      </li>
      <li class='nav-item active'>
        <a class='nav-link' href='/member/list'>회원관리</a>
        </li>
      <li class='nav-item active'>
        <a class='nav-link' href='/photoboard/list'>사진게시판</a>
      </li>
    </ul>
  </div>
  
  <div>
<%
Member loginUser = (Member) session.getAttribute("loginUser");
if (loginUser == null) {
%>
  <a href='/auth/login' class='btn btn-outline-light bnt-sm'>로그인</a>
<%
} else {
%>
  <a id='userid' href='/member/detail?no=<%=loginUser.getNo()%>'
  style='color:black;font-size:x-large;'><%=loginUser.getName()%></a>
  <a href='/auth/logout' class='btn btn-outline-dark bnt-sm'>로그아웃</a>
<%}%>
  </div>
</nav>