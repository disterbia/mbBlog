package com.cos.mbBlog.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int id;
	private String userProfile; 
	private String username;
	private String password;
	private String email;
	private String address; 
	private Timestamp createDate;
	
}