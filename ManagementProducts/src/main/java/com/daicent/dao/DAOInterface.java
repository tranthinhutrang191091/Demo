package com.daicent.dao;

import java.util.ArrayList;


public interface DAOInterface<T> {
	// Data Access Object 
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
	public ArrayList<T> selectAll();
	public T selectById(T t);
	public T selectByName(T t);
	public ArrayList<T> search(String value);
	public void show(ArrayList<T> list);
}
