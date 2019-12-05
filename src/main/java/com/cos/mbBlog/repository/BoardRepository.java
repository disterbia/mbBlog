package com.cos.mbBlog.repository;

import java.util.List;

import com.cos.mbBlog.model.Board;

public interface BoardRepository {
	List<Board> findAll(int page);
	void save(Board board); 
	void update(Board board); 
	Board findById(int id); 
	void delete(int id); 
}
