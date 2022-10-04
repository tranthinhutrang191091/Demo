package com.daicent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.daicent.database.JDBCUtil;
import com.daicent.model.Category;
import com.mysql.cj.jdbc.CallableStatement;

public class CategoryDAO implements DAOInterface<Category> {
	public static CategoryDAO getInstance() {
		return new CategoryDAO();
	}

	@Override
	public int insert(Category t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Category category = selectByName(t);
			if (category.getNameCategory() == null) {
				String mysql = "INSERT INTO management_products.category (nameCategory) VALUES (?);";
				// Bước 2: tạo đối tượng PreparedStatement
				PreparedStatement preStatemnt = connection.prepareStatement(mysql);
				preStatemnt.setString(1, t.getNameCategory());
				// Bước 3: thực thi câu lệnh sql
				result = preStatemnt.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println("There is " + result + " line changed! Add Category name: " + t.getNameCategory()
							+ " successfully!");
				} else {
					System.out.println("Add Category name: " + t.getNameCategory() + " failed!");
				}
			} else {
				System.out.println("Add Category name: " + t.getNameCategory() + " failed! Category already exists!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public int update(Category t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Category category = selectById(t);
			if (category.getNameCategory() != null) {
				Category category1 = selectByName(t);
				if (category1.getNameCategory() == null) {
					String mysql = "UPDATE management_products.category SET nameCategory = ? WHERE (idCategory = ?);";
					// Bước 2: tạo đối tượng PreparedStatement
					PreparedStatement preStatemnt = connection.prepareStatement(mysql);
					preStatemnt.setString(1, t.getNameCategory());
					preStatemnt.setInt(2, t.getIdCategory());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("There is " + result + " line changed! Update Category name: "
								+ t.getNameCategory() + " successfully!");
					} else {
						System.out.println("Update Category name:" + t.getNameCategory() + " failed!");
					}
				} else {
					System.out.println(
							"Update Category name: " + t.getNameCategory() + " failed! Category already exists!");
				}
			} else {
				System.out.println("Update Category id: " + t.getIdCategory() + " failed! Category does not exist!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public int delete(Category t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Category category = selectById(t);
			if (category.getNameCategory() != null) {
				String mysql = "DELETE FROM management_products.category WHERE (idCategory = ?);";
				// Bước 2: tạo đối tượng PreparedStatement
				PreparedStatement preStatemnt = connection.prepareStatement(mysql);
				preStatemnt.setInt(1, t.getIdCategory());
				// Bước 3: thực thi câu lệnh sql
				result = preStatemnt.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println("There is " + result + " line changed! Delete Category name: "
							+ t.getIdCategory() + " successfully!");
				} else {
					System.out.println("Delete Category id: " + t.getIdCategory() + " failed!");
				}
			} else {
				System.out.println("Delete Category id: " + t.getIdCategory() + " failed! Category does not exist!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public ArrayList<Category> selectAll() {
		List<Category> listCategory = new ArrayList<Category>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT idCategory as 'ID', nameCategory as 'CategoryName' FROM management_products.category;";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idCategory = resultSet.getInt("ID");
				String nameCategory = resultSet.getString("CategoryName");
				Category category = new Category(idCategory, nameCategory);
				listCategory.add(category);
				count++;
			}
			if (count > 0) {
				System.out.println("There are " + count + " Category!");
				show((ArrayList<Category>) listCategory);
			} else {
				System.out.println("No Category!!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return (ArrayList<Category>) listCategory;
	}

	@Override
	public Category selectById(Category t) {
		Category category = new Category();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.category WHERE (idCategory = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setInt(1, t.getIdCategory());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idCategory = resultSet.getInt("idCategory");
				String nameCategory = resultSet.getString("nameCategory");
				category = new Category(idCategory, nameCategory);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return category;
	}

	@Override
	public Category selectByName(Category t) {
		Category category = new Category();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.category WHERE (nameCategory = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setString(1, t.getNameCategory());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idCategory = resultSet.getInt("idCategory");
				String nameCategory = resultSet.getString("nameCategory");
				category = new Category(idCategory, nameCategory);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return category;
	}

	@Override
	public ArrayList<Category> search(String value) {
		List<Category> listCategory = new ArrayList<Category>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql ="select * from category where nameCategory like concat('%',?, '%');";
			CallableStatement callStatement = (CallableStatement) connection.prepareCall(mysql);
			callStatement.setString(1, value);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = callStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idCategory = resultSet.getInt("idCategory");
				String nameCategory = resultSet.getString("nameCategory");
				Category category = new Category(idCategory, nameCategory);
				listCategory.add(category);
				count++;
			}
			if (count > 0) {
				System.out.println("Found " + count + " Category string: " + value + "!");
				show((ArrayList<Category>) listCategory);
			} else {
				System.out.println(value+": Not found Category !");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return (ArrayList<Category>) listCategory;
	}

	@Override
	public void show(ArrayList<Category> list) {
		for (Category category : list) {
			System.out.println(category.toString());
		}
	}

}
