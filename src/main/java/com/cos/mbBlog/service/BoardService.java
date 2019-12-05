package com.cos.mbBlog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.mbBlog.model.Board;
import com.cos.mbBlog.model.User;
import com.cos.mbBlog.repository.BoardRepository;
import com.cos.mbBlog.utils.Utils;

@Service
public class BoardService {

	@Autowired
	private BoardRepository bRepo;

	public List<Board> home(int page) {
		List<Board> boards = bRepo.findAll(page);
		Utils.setPreviewImg(boards);
		Utils.setPreviewContent(boards);

		return boards;
	}

	public Board detail(int id) {

		Board board = bRepo.findById(id);

		return board;
	}

	public int write(Board board, HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			board.setUserId(user.getId());
			board.setUserProfile(user.getUserProfile());
			board.setUsername(user.getUsername());
			Utils.setPreviewYoutube(board);
			bRepo.save(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public Board updateForm(int id) {
		Board board = bRepo.findById(id);

		return board;
	}

	public int update(Board board) {
		try {
			bRepo.update(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int delete(int id) {
		try {
			bRepo.delete(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}
}
