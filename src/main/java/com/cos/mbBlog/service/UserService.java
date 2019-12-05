package com.cos.mbBlog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.multipart.MultipartFile;

import com.cos.mbBlog.model.User;
import com.cos.mbBlog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository uRepo;

	public int login(User user, HttpSession session) {
		User u = uRepo.findByUsernameAndPassword(user);
		if (u != null) {
			session.setAttribute("user", u);
			return 1;
		} else
			return -1;
	}

	public int join(User user) {
		try {
			uRepo.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/board";
	}

	@Value("${file.path}")
	private String resourcePath;

	public int imageUpload(MultipartFile file, HttpSession session) {

		UUID uuid = UUID.randomUUID();
		String uuidFileName = uuid + "_" + file.getOriginalFilename();

		Path filePath = Paths.get(resourcePath + uuidFileName);
		System.out.println("filePath : " + filePath);
		try {
			Files.write(filePath, file.getBytes());
			User user = (User) session.getAttribute("user");
			user.setUserProfile("/userProfile/" + uuidFileName);
			uRepo.updateUserProFile(user);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return -1;

	}
}
