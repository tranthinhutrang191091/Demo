package com.daicent;

import java.util.ArrayList;

import com.daicent.dao.CategoryDetailDAO;
import com.daicent.model.Category;
import com.daicent.model.CategoryDetail;

public class TestCategoryDetail {
	public static void main(String[] args) {
		// create CategoryDetail
//		CategoryDetail categoryDetail= new CategoryDetail(1,"Pin dự phòng",2);
//		CategoryDetailDAO.getInstance().insert(categoryDetail);
//		CategoryDetail categoryDetail= new CategoryDetail(1,"Pin dự phòng",100);
//		CategoryDetailDAO.getInstance().insert(categoryDetail);
		
		// create CategoryDetail
//		CategoryDetail categoryDetail= new CategoryDetail(21,"Pin dự phòng",100);
//		CategoryDetailDAO.getInstance().update(categoryDetail);
//		CategoryDetail categoryDetail= new CategoryDetail(21,"Pin dự phòng 100",1);
//		CategoryDetailDAO.getInstance().update(categoryDetail);
		
		// delete CategoryDetail
//		CategoryDetail categoryDetail= new CategoryDetail(100,"Pin dự phòng 100",1);
//		CategoryDetailDAO.getInstance().delete(categoryDetail);
//		CategoryDetail categoryDetail= new CategoryDetail(21,"Pin dự phòng 100",1);
//		CategoryDetailDAO.getInstance().delete(categoryDetail);
		
		// list CategoryDetail
//		ArrayList<CategoryDetail> list = CategoryDetailDAO.getInstance().selectAll();
		
		// Search CategoryDetail by name CategoryDetail
//		CategoryDetail categoryDetail= new CategoryDetail(21,"Pin dự phòng",1);
//		if(CategoryDetailDAO.getInstance().selectByName(categoryDetail).getNameCategoryDetail()==null) {
//			System.out.println("Not fond!");
//		}else {
//			System.out.println(CategoryDetailDAO.getInstance().selectByName(categoryDetail));
//		}
		
		// view CategoryDetail
//		CategoryDetailDAO.getInstance().infoAll();
		
		// select CategoryDetail By IdCategory
//		Category category= new Category(1,"");
//		CategoryDetailDAO.getInstance().selectByIdCategory(category);
		
		//map
		CategoryDetailDAO.getInstance().mapCategory();
		
	}
}
