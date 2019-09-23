package com.lee.domain;

import java.io.Serializable;

public class Board implements Serializable{
  private static final long serialVersionUID = 1L;
  
  int no;
  int memberNo;
  int photoNo;
  String title;
  String contents;
  String createdDate;
  
  @Override
  public String toString() {
    return "Board [no=" + no + ", memberNo=" + memberNo + ", photoNo=" + photoNo + ", title="
        + title + ", contents=" + contents + ", createdDate=" + createdDate + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getMemberNo() {
    return memberNo;
  }
  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }
  public int getPhotoNo() {
    return photoNo;
  }
  public void setPhotoNo(int photoNo) {
    this.photoNo = photoNo;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  
  
}
