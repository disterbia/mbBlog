package com.cos.mbBlog.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cos.mbBlog.model.User;

import com.cos.mbBlog.service.UserService;
import com.cos.mbBlog.utils.Script;

@Controller
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService service;

	@GetMapping("/loginForm")
	public String loginForm() {

		return "user/loginForm";
	}

	@PostMapping("login")
	public @ResponseBody String login(User user, HttpSession session) {
		int result = service.login(user, session);
		if(result==1) return Script.href("/board");
		else return Script.Back("로그인 실패");
	}

	@GetMapping("/joinForm")
	public String joinForm() {

		return "user/joinForm";
	}

	@PostMapping("/join")
	public @ResponseBody String join(User user) {
		int result=service.join(user);
		if(result==1) return Script.alertAndHref("회원가입 완료", "/board/login");
		else return Script.Back("회원가입 실패");
		
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/board";
	}

	@GetMapping("/profileUpdateForm")
	public String profileUpdateForm() {
		return "user/profileUpdateForm";
	}

	@PostMapping("/profileUpdate")
	public @ResponseBody String imageUpload(@RequestParam("file") MultipartFile file, HttpSession session) {

		int result=service.imageUpload(file, session);
		
		if(result==1) return Script.href("/board");
		else return Script.Back("이미지업로드 실패");
		
	}

}
