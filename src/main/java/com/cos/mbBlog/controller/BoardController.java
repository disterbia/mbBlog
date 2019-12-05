package com.cos.mbBlog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.mbBlog.model.Board;

import com.cos.mbBlog.service.BoardService;
import com.cos.mbBlog.utils.Script;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;

	@GetMapping("")
	public String index() {

		return "redirect:/board/list/1";
	}

	@GetMapping("/list/{page}")
	public String home(Model model, @PathVariable int page) {
		model.addAttribute("page", page);
		if(page<1) page=1;
		page = (page - 1) * 3;
		List<Board> boards = service.home(page);
		model.addAttribute("boards", boards);
		return "board/list";
	}

	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable int id) {

		Board board = service.detail(id);
		model.addAttribute("board", board);

		return "board/detail";
	}

	@GetMapping("/writeForm")
	public String writeForm() {

		return "board/writeForm";
	}

	@PostMapping("/write")
	public @ResponseBody String write(Board board, HttpSession session) {
		int result = service.write(board, session);

		if (result == 1)
			return Script.alertAndHref("글쓰기 완료", "/board");
		else
			return Script.Back("글쓰기 실패");

	}

	@GetMapping("/updateForm/{id}")
	public String updateForm(Model model, @PathVariable int id) {
		Board board = service.updateForm(id);
		model.addAttribute("board", board);
		return "board/updateForm";
	}

	@PostMapping("/update")
	public @ResponseBody String update(Board board) {
		int result = service.update(board);

		if (result == 1)
			return Script.alertAndHref("수정성공", "/board");
		else
			return Script.Back("수정실패");

	}

	@GetMapping("/delete/{id}")
	public @ResponseBody String delete(@PathVariable int id) {
		int result = service.delete(id);
		if (result == 1)
			return Script.alertAndHref("삭제완료", "/board");
		else
			return Script.Back("삭제실패");

	}
}
