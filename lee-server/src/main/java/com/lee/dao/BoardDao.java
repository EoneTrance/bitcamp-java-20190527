package com.lee.dao;

import java.util.List;
import com.lee.domain.Board;

public interface BoardDao {
  int insert(Board board) throws Exception;
  List<Board> findAll() throws Exception;
  Board findBy(int no) throws Exception;
  int update(Board board) throws Exception;
  int delete(int no) throws Exception;
}
