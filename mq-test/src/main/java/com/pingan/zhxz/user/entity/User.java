package com.pingan.zhxz.user.entity;


import java.io.Serializable;

public class User implements Serializable{
	private Integer userId;
    private String userName;
    private String sex;
    private String phone;


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User{" + "username='" + userName + '\'' +", sex='" + sex + '\'' + '}';
	}
}
