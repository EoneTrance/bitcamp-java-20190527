package ch29.practice;

import java.sql.Date;

public class Student_test {
  private String name;
  private String email;
  private String password;
  private String tel;
  private int age;
  private boolean working;
  private Date date;
  
  @Override
  public String toString() {
    return "Student_test [name=" + name + ", email=" + email + ", password=" + password + ", tel="
        + tel + ", age=" + age + ", working=" + working + ", date=" + date + "]";
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public boolean isWorking() {
    return working;
  }
  public void setWorking(boolean working) {
    this.working = working;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
