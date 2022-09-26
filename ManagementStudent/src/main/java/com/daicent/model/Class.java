package com.daicent.model;

public class Class {
	private int idClass;
	private String nameClass;
	private int idUnit;
	public Class() {
	}
	public Class(int idClass, String nameClass, int idUnit) {
		
		this.idClass = idClass;
		this.nameClass = nameClass;
		this.idUnit = idUnit;
	}
	public int getIdClass() {
		return idClass;
	}
	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}
	public String getNameClass() {
		return nameClass;
	}
	public void setNameClass(String nameClass) {
		this.nameClass = nameClass;
	}
	public int getIdUnit() {
		return idUnit;
	}
	public void setIdUnit(int idUnit) {
		this.idUnit = idUnit;
	}
	@Override
	public String toString() {
		return "Class [idClass=" + idClass + ", nameClass=" + nameClass + ", idUnit=" + idUnit + "]";
	}
	
}
