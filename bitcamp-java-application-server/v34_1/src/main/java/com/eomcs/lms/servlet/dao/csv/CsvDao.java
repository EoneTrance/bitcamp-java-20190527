package com.eomcs.lms.servlet.dao.csv;

import java.util.List;

public interface CsvDao<T> {
  
  int add(T obj);
  
  public List<T> list();
  
  public T get(int no);
  
  public int set(T obj);
  
  public int remove(int no);

}
