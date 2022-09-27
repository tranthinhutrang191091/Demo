package com.daicent.model;

public class StudentSubject {
	 private int idStudent_Subject;
	 private int idStudent;
	 private int idSubject;
	 private Float scores;
	public StudentSubject() {
		
	}
	public StudentSubject(int idStudent_Subject, int idStudent, int idSubject, Float scores) {
		
		this.idStudent_Subject = idStudent_Subject;
		this.idStudent = idStudent;
		this.idSubject = idSubject;
		this.scores = scores;
	}
	public int getIdStudent_Subject() {
		return idStudent_Subject;
	}
	public void setIdStudent_Subject(int idStudent_Subject) {
		this.idStudent_Subject = idStudent_Subject;
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public int getIdSubject() {
		return idSubject;
	}
	public void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}
	public Float getScores() {
		return scores;
	}
	public void setScores(Float scores) {
		this.scores = scores;
	}
	@Override
	public String toString() {
		return "Student_Subject [idStudent_Subject=" + idStudent_Subject + ", idStudent=" + idStudent + ", idSubject="
				+ idSubject + ", scores=" + scores + "]";
	}
	 
}
