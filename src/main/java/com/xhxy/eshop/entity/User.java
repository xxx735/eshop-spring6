package com.xhxy.eshop.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *  实体类:用户
 *  
 */
public class User {
	private Integer id;
	private String username;	// 用户名
	private String password;	// 密码
	private String email;		// 邮箱
	private String phone;		// 手机号码
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;      // 生日
	private String avatar;		// 头像文件的路径
	
	// getter和setter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}
