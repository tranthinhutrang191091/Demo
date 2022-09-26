package com.daicent.model;

public class Unit {
	private int idUnit;
	private String nameUnit;
	private int idSchool;
	public Unit() {
	}
	public Unit(int idUnit, String nameUnit, int idschool) {
		
		this.idUnit = idUnit;
		this.nameUnit = nameUnit;
		this.idSchool = idschool;
	}
	public int getIdUnit() {
		return idUnit;
	}
	public void setIdUnit(int idUnit) {
		this.idUnit = idUnit;
	}
	public String getNameUnit() {
		return nameUnit;
	}
	public void setNameUnit(String nameUnit) {
		this.nameUnit = nameUnit;
	}
	public int getIdSchool() {
		return idSchool;
	}
	public void setIdSchool(int idschool) {
		this.idSchool = idschool;
	}
	@Override
	public String toString() {
		return "Unit [idUnit=" + idUnit + ", nameUnit=" + nameUnit + ", idschool=" + idSchool + "]";
	}
	
	
}
