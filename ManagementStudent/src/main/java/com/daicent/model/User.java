package com.daicent.model;

public class User {
	private int idUser;
	private String userName;
	private String password;
	private int idStudent;
	public User() {
	}
	public User(int idUser, String userName, String password, int idStudent) {
		
		this.idUser = idUser;
		this.userName = userName;
		this.password = password;
		this.idStudent = idStudent;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", userName=" + userName + ", password=" + password + ", idStudent="
				+ idStudent + "]";
	}
	
}
