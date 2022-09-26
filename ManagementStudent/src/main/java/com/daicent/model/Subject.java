package com.daicent.model;

public class Subject {
	private int idSubjcet;
	private String nameSubject;
	public Subject() {
		
	}
	public Subject(int idSubjcet, String nameSubject) {
		
		this.idSubjcet = idSubjcet;
		this.nameSubject = nameSubject;
	}
	public int getIdSubjcet() {
		return idSubjcet;
	}
	public void setIdSubjcet(int idSubjcet) {
		this.idSubjcet = idSubjcet;
	}
	public String getNameSubject() {
		return nameSubject;
	}
	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}
	@Override
	public String toString() {
		return "Subject [idSubjcet=" + idSubjcet + ", nameSubject=" + nameSubject + "]";
	}
	
}
