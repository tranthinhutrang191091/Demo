package com.daicent.model;

public class Student {
	private int idStudent;
	private String nameSudent;
	private int idClass;
	public Student() {
	}
	public Student(int idStudent, String nameSudent, int idClass) {
		
		this.idStudent = idStudent;
		this.nameSudent = nameSudent;
		this.idClass = idClass;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public String getNameSudent() {
		return nameSudent;
	}
	public void setNameSudent(String nameSudent) {
		this.nameSudent = nameSudent;
	}
	public int getIdClass() {
		return idClass;
	}
	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}
	@Override
	public String toString() {
		return "Student [idStudent=" + idStudent + ", nameSudent=" + nameSudent + ", idClass=" + idClass + "]";
	}
	
}
