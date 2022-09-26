package com.daicent.model;

public class School {
	private int idSchool;
	private String nameSchool;

	public School() {
	}

	public School(int idSchool, String nameSchool) {

		this.idSchool = idSchool;
		this.nameSchool = nameSchool;
	}

	public int getIdSchool() {
		return idSchool;
	}

	public void setIdSchool(int idSchool) {
		this.idSchool = idSchool;
	}

	public String getNameSchool() {
		return nameSchool;
	}

	public void setNameSchool(String nameSchool) {
		this.nameSchool = nameSchool;
	}

	@Override
	public String toString() {
		return "School [idSchool=" + idSchool + ", nameSchool=" + nameSchool + "]";
	}

}
