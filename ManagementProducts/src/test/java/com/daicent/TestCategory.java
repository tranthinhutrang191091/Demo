package com.daicent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.daicent.dao.CategoryDAO;
import com.daicent.model.Category;

public class TestCategory {
	public static void main(String[] args) {
		// insert Category
//		Category category = new Category(4,"Nhà Sách Online");
//		CategoryDAO.getInstance().insert(category);
		
		// upadte Category
//		Category category = new Category(4,"Nhà Sách Online");
//		CategoryDAO.getInstance().update(category);
//		Category category = new Category(9,"Nhà Sách Online 1");
//		CategoryDAO.getInstance().update(category);
//		Category category = new Category(6,"Mẹ & bé");
//		CategoryDAO.getInstance().update(category);
//		Category category = new Category(1,"Điện Thoại & Phụ Kiện");
//		CategoryDAO.getInstance().update(category);
//		Category category = new Category(2,"Máy Tính & Laptop");
//		CategoryDAO.getInstance().update(category);
		
		// delete Category
//		Category category = new Category(10,"Nhà Sách Online");
//		CategoryDAO.getInstance().delete(category);
//		Category category = new Category(8,"Nhà Sách Online");
//		CategoryDAO.getInstance().delete(category);
		
		// List Category
//		ArrayList<Category> list= CategoryDAO.getInstance().selectAll();
//		 CategoryDAO.getInstance().show(list);
		
		// Search Category
//		CategoryDAO.getInstance().search("Điện thoại");
//		CategoryDAO.getInstance().search("Không");
		
		// seclect by name Category
//		Category category = new Category(10,"dgssfh");
		 Category category = new Category(10,"Đồng Hồ");
		if(CategoryDAO.getInstance().selectByName(category).getNameCategory()==null) {
			System.out.println("No found!");
		}else {
			System.out.println( CategoryDAO.getInstance().selectByName(category));
		}
	}
}
