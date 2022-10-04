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
import com.daicent.model.CategoryDetail;
import com.daicent.model.Products;
import com.mysql.cj.jdbc.CallableStatement;

public class ProductsDAO implements DAOInterface<Products> {

	public static ProductsDAO getInstance() {
		return new ProductsDAO();
	}

	@Override
	public int insert(Products t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			CategoryDetail categoryDetail = CategoryDetailDAO.getInstance()
					.selectById(new CategoryDetail(t.getIdCategoryDetail(), "", 0));
			if (categoryDetail.getNameCategoryDetail() != null) {
				String mysql = "INSERT INTO management_products.products (nameProducts, price, amount, idCategoryDetail) VALUES (?, ?, ?, ?);";
				// Bước 2: tạo đối tượng PreparedStatement
				PreparedStatement preStatemnt = connection.prepareStatement(mysql);
				preStatemnt.setString(1, t.getNameProducts());
				preStatemnt.setDouble(2, t.getPrice());
				preStatemnt.setInt(3, t.getAmount());
				preStatemnt.setInt(4, t.getIdCategoryDetail());
				// Bước 3: thực thi câu lệnh sql
				result = preStatemnt.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println("There is " + result + " line changed! Add Products name: " + t.getNameProducts()
							+ " successfully!");
				} else {
					System.out.println("Add CategoryDetail name: " + t.getNameProducts() + " failed!");
				}

			} else {
				System.out.println("Add CategoryDetail id CategoryDetail: " + t.getIdCategoryDetail()
						+ " failed! CategoryDetail does not exist!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public int update(Products t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			CategoryDetail categoryDetail = CategoryDetailDAO.getInstance()
					.selectById(new CategoryDetail(t.getIdCategoryDetail(), "", 0));
			if (categoryDetail.getNameCategoryDetail() != null) {
				Products products = selectById(t);
				if (products.getNameProducts() != null) {
					String mysql = "UPDATE management_products.products SET nameProducts = ?, price = ?, amount = ?, idCategoryDetail = ? WHERE (idProducts = ?);";
					// Bước 2: tạo đối tượng PreparedStatement
					PreparedStatement preStatemnt = connection.prepareStatement(mysql);
					preStatemnt.setString(1, t.getNameProducts());
					preStatemnt.setDouble(2, t.getPrice());
					preStatemnt.setInt(3, t.getAmount());
					preStatemnt.setInt(4, t.getIdCategoryDetail());
					preStatemnt.setInt(5, t.getIdProducts());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("There is " + result + " line changed! Update Products name: "
								+ t.getNameProducts() + " successfully!");
					} else {
						System.out.println("Update Products name: " + t.getNameProducts() + " failed!");
					}
				} else {
					System.out.println(
							"Update Products name: " + t.getNameProducts() + " failed! Products does not exist!");
				}
			} else {
				System.out.println("Update CategoryDetail id : " + t.getIdCategoryDetail()
						+ " failed! CategoryDetail does not exist!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public int delete(Products t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Products products = selectById(t);
			if (products.getNameProducts() != null) {
				String mysql = "DELETE FROM management_products.products WHERE (idProducts = ?);";
				// Bước 2: tạo đối tượng PreparedStatement
				PreparedStatement preStatemnt = connection.prepareStatement(mysql);
				preStatemnt.setInt(1, t.getIdProducts());
				// Bước 3: thực thi câu lệnh sql
				result = preStatemnt.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println("There is " + result + " line changed! Delete Products id: "
							+ t.getNameProducts() + " successfully!");
				} else {
					System.out.println("Update Products name: " + t.getNameProducts() + " failed!");
				}
			} else {
				System.out
						.println("Delete Products name: " + t.getNameProducts() + " failed! Products does not exist!");
			}

			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public ArrayList<Products> selectAll() {
		List<Products> listProducts = new ArrayList<Products>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM products";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idProducts = resultSet.getInt("idProducts");
				String nameProducts = resultSet.getString("nameProducts");
				Double price = resultSet.getDouble("price");
				int amount = resultSet.getInt("amount");
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				Products products = new Products(idProducts, nameProducts, idProducts, amount, idCategoryDetail);
				listProducts.add(products);
				count++;
			}
			if (count > 0) {
				System.out.println("There are " + count + " Products!");
				show((ArrayList<Products>) listProducts);
			} else {
				System.out.println("No CategoryDetail!!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return (ArrayList<Products>) listProducts;
	}

	@Override
	public Products selectById(Products t) {
		Products products = new Products();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.products WHERE (idProducts = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setInt(1, t.getIdProducts());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idProducts = resultSet.getInt("idProducts");
				String nameProducts = resultSet.getString("nameProducts");
				Double price = resultSet.getDouble("price");
				int amount = resultSet.getInt("amount");
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				products = new Products(idProducts, nameProducts, idProducts, amount, idCategoryDetail);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return products;
	}

	@Override
	public Products selectByName(Products t) {
		Products products = new Products();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.products WHERE (nameProducts = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setString(1, t.getNameProducts());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idProducts = resultSet.getInt("idProducts");
				String nameProducts = resultSet.getString("nameProducts");
				Double price = resultSet.getDouble("price");
				int amount = resultSet.getInt("amount");
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				products = new Products(idProducts, nameProducts, idProducts, amount, idCategoryDetail);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return products;
	}

	@Override
	public ArrayList<Products> search(String value) {
		List<Products> listProducts = new ArrayList<Products>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "select * from products where nameProducts like concat('%',?, '%');";
			CallableStatement callStatement = (CallableStatement) connection.prepareCall(mysql);
			callStatement.setString(1, value);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = callStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idProducts = resultSet.getInt("idProducts");
				String nameProducts = resultSet.getString("nameProducts");
				Double price = resultSet.getDouble("price");
				int amount = resultSet.getInt("amount");
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				Products products = new Products(idProducts, nameProducts, idProducts, amount, idCategoryDetail);
				listProducts.add(products);
				count++;
			}
			if (count > 0) {
				System.out.println("Found " + count + " Products string: " + value + "!");
				show((ArrayList<Products>) listProducts);
			} else {
				System.out.println(value + ": Not found Products !");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return (ArrayList<Products>) listProducts;
	}

	@Override
	public void show(ArrayList<Products> list) {
		for (Products products : list) {
			System.out.println(products.toString());
		}
	}

	public void infoAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.viewproducts;";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idProducts = resultSet.getInt("idProducts");
				String nameProducts = resultSet.getString("nameProducts");
				String price = resultSet.getString("price");
				int amount = resultSet.getInt("amount");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				String result = "ProductsInfomation [ID=" + idProducts + ", Name=" + nameProducts + ", Price=" + price
						+ ", Amount=" + amount + ", Category Detail=" + nameCategoryDetail + "]";
				System.out.println(result);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(ProductsDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public ArrayList<Products> selectByIdCategoryDetail(CategoryDetail t) {
		List<Products> listProducts = new ArrayList<Products>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "call management_products.listProductsByCategoryDetail(?);";
			CallableStatement callStatement = (CallableStatement) connection.prepareCall(mysql);
			callStatement.setInt(1, t.getIdCategoryDetail());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = callStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idProducts = resultSet.getInt("idProducts");
				String nameProducts = resultSet.getString("nameProducts");
				Double price = resultSet.getDouble("price");
				int amount = resultSet.getInt("amount");
				int idCategoryDetail= resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				Products products= new Products(idProducts, nameProducts, price, amount, idCategoryDetail);
				listProducts.add(products);
				String result = "ProductsInfomation [ID=" + idProducts + ", Name=" + nameProducts + ", Price=" + price
						+ ", Amount=" + amount + ", Category Detail=" + nameCategoryDetail  +"]";
				System.out.println(result);
				count++;
			}
			System.out.println("Count " + count + " rows!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<Products>) listProducts;
	}

	public ArrayList<Products> selectByIdCategory(Category t) {
		List<Products> listProducts = new ArrayList<Products>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "call management_products.listProductsByCategory(?);";
			CallableStatement callStatement = (CallableStatement) connection.prepareCall(mysql);
			callStatement.setInt(1, t.getIdCategory());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = callStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idProducts = resultSet.getInt("idProducts");
				String nameProducts = resultSet.getString("nameProducts");
				Double price = resultSet.getDouble("price");
				int amount = resultSet.getInt("amount");
				int idCategoryDetail= resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				String nameCategory=resultSet.getString("nameCategory");
				Products products= new Products(idProducts, nameProducts, price, amount, idCategoryDetail);
				listProducts.add(products);
				String result = "ProductsInfomation [ID=" + idProducts + ", Name=" + nameProducts + ", Price=" + price
						+ ", Amount=" + amount + ", Category Detail=" + nameCategoryDetail + ", Category =" + nameCategory +"]";
				System.out.println(result);
				count++;
			}
			System.out.println("Count " + count + " rows!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<Products>) listProducts;
	}

}
