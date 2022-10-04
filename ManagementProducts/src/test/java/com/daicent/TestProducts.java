package com.daicent;

import com.daicent.dao.ProductsDAO;
import com.daicent.model.Category;
import com.daicent.model.CategoryDetail;
import com.daicent.model.Products;

public class TestProducts {
	public static void main(String[] args) {
		// create Products
//		Products products = new Products(1,"Trần trang", 8700d, 100, 100);
//		ProductsDAO.getInstance().insert(products);
//		Products products = new Products(1,"Trần trang", 8700d, 100, 1);
//		ProductsDAO.getInstance().insert(products);
		
		// update Products
//		Products products = new Products(100,"Trần trang100", 9700d, 1000, 100);
//		ProductsDAO.getInstance().update(products);
//		Products products = new Products(100,"Trần trang100", 9700d, 1000, 1);
//		ProductsDAO.getInstance().update(products);
//		Products products = new Products(17,"Trần trang100", 9700d, 1000, 1);
//		ProductsDAO.getInstance().update(products);
		
		// delete Products
//		Products products = new Products(100,"Trần trang100", 9700d, 1000, 1);
//		ProductsDAO.getInstance().delete(products);
//		Products products = new Products(17,"Trần trang100", 9700d, 1000, 1);
//		ProductsDAO.getInstance().delete(products);
		
		// select All Products
//		ProductsDAO.getInstance().selectAll();
		
		// search Products
//		ProductsDAO.getInstance().search("iphone");
		
		// view CategoryDetail
//		ProductsDAO.getInstance().infoAll();
		
		// select By IdCategory
//		Category category= new Category(1,"");
//		ProductsDAO.getInstance().selectByIdCategory(category);
//		Category category= new Category(2,"");
//		ProductsDAO.getInstance().selectByIdCategory(category);
//		Category category= new Category(5,"");
//		ProductsDAO.getInstance().selectByIdCategory(category);
		
		// select By Id CategoryDetail
//		CategoryDetail categoryDetail = new CategoryDetail(1,"",1);
//		ProductsDAO.getInstance().selectByIdCategoryDetail(categoryDetail);
		CategoryDetail categoryDetail = new CategoryDetail(17,"",1);
		ProductsDAO.getInstance().selectByIdCategoryDetail(categoryDetail);
		
	}
	
}
