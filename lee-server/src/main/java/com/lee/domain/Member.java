package com.lee.domain;

public class Member {
  int no;
  String id;
  String name;
  String password;
  String email;
  String registeredDate;
  
  @Override
  public String toString() {
    return "Member [no=" + no + ", id=" + id + ", name=" + name + ", password=" + password
        + ", email=" + email + ", registeredDate=" + registeredDate + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(String registeredDate) {
    this.registeredDate = registeredDate;
  }
  
  
}
