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
import com.mysql.cj.jdbc.CallableStatement;

public class CategoryDetailDAO implements DAOInterface<CategoryDetail> {

	public static CategoryDetailDAO getInstance() {
		return new CategoryDetailDAO();
	}

	@Override
	public int insert(CategoryDetail t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Category category = CategoryDAO.getInstance().selectById(new Category(t.getIdCategory(), ""));
			if (category.getNameCategory() != null) {
				CategoryDetail categoryDetail = selectByIdCategoryAndName(t);
				if (categoryDetail.getNameCategoryDetail() == null) {
					String mysql = "INSERT INTO management_products.categorydetail (nameCategoryDetail, idCategory) VALUES (?, ?);";
					// Bước 2: tạo đối tượng PreparedStatement
					PreparedStatement preStatemnt = connection.prepareStatement(mysql);
					preStatemnt.setString(1, t.getNameCategoryDetail());
					preStatemnt.setInt(2, t.getIdCategory());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("There is " + result + " line changed! Add CategoryDetail name: "
								+ t.getNameCategoryDetail() + " successfully!");
					} else {
						System.out.println("Add CategoryDetail name: " + t.getNameCategoryDetail() + " failed!");
					}
				} else {
					System.out.println("Add CategoryDetail name: " + t.getNameCategoryDetail()
							+ " failed! CategoryDetail already exists!");
				}
			} else {
				System.out.println(
						"Add CategoryDetail id Category: " + t.getIdCategory() + " failed! Category does not exist!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public int update(CategoryDetail t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Category category = CategoryDAO.getInstance().selectById(new Category(t.getIdCategory(), ""));
			if (category.getNameCategory() != null) {
				CategoryDetail categoryDetail = selectById(t);
				if (categoryDetail.getNameCategoryDetail() != null) {
					String mysql = "UPDATE management_products.categorydetail SET nameCategoryDetail = ?, idCategory = ? WHERE (idCategoryDetail = ?);";
					// Bước 2: tạo đối tượng PreparedStatement
					PreparedStatement preStatemnt = connection.prepareStatement(mysql);
					preStatemnt.setString(1, t.getNameCategoryDetail());
					preStatemnt.setInt(2, t.getIdCategory());
					preStatemnt.setInt(3, t.getIdCategoryDetail());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("There is " + result + " line changed! Update CategoryDetail name: "
								+ t.getNameCategoryDetail() + " successfully!");
					} else {
						System.out.println("Update CategoryDetail name: " + t.getNameCategoryDetail() + " failed!");
					}
				} else {
					System.out.println("Update CategoryDetail name: " + t.getNameCategoryDetail()
							+ " failed! CategoryDetail does not exist!");
				}
			} else {
				System.out.println("Update CategoryDetail id Category: " + t.getIdCategory()
						+ " failed! Category does not exist!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public int delete(CategoryDetail t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			CategoryDetail categoryDetail = selectById(t);
			if (categoryDetail.getNameCategoryDetail() != null) {
				String mysql = "DELETE FROM management_products.categorydetail WHERE (idCategoryDetail = ?);";
				// Bước 2: tạo đối tượng PreparedStatement
				PreparedStatement preStatemnt = connection.prepareStatement(mysql);
				preStatemnt.setInt(1, t.getIdCategoryDetail());
				// Bước 3: thực thi câu lệnh sql
				result = preStatemnt.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println("There is " + result + " line changed! Delete CategoryDetail id: "
							+ t.getIdCategoryDetail() + " successfully!");
				} else {
					System.out.println("Update CategoryDetail id: " + t.getIdCategoryDetail() + " failed!");
				}
			} else {
				System.out.println("Delete CategoryDetail name: " + t.getIdCategoryDetail()
						+ " failed! CategoryDetail does not exist!");
			}

			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return result;
	}

	@Override
	public ArrayList<CategoryDetail> selectAll() {
		List<CategoryDetail> lisCategoryDetails = new ArrayList<CategoryDetail>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT idCategoryDetail, nameCategoryDetail,idCategory " + "FROM categorydetail";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				int idCategory = resultSet.getInt("idCategory");
				CategoryDetail categoryDetail = new CategoryDetail(idCategoryDetail, nameCategoryDetail, idCategory);
				lisCategoryDetails.add(categoryDetail);
				count++;
			}
			if (count > 0) {
				System.out.println("There are " + count + " CategoryDetail!");
				show((ArrayList<CategoryDetail>) lisCategoryDetails);
			} else {
				System.out.println("No CategoryDetail!!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return (ArrayList<CategoryDetail>) lisCategoryDetails;
	}

	@Override
	public CategoryDetail selectById(CategoryDetail t) {
		CategoryDetail categoryDetail = new CategoryDetail();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.categorydetail WHERE (`idCategoryDetail` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setInt(1, t.getIdCategoryDetail());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				int idCategory = resultSet.getInt("idCategory");
				categoryDetail = new CategoryDetail(idCategoryDetail, nameCategoryDetail, idCategory);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return categoryDetail;
	}

	@Override
	public CategoryDetail selectByName(CategoryDetail t) {
		CategoryDetail categoryDetail = new CategoryDetail();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.categorydetail WHERE (`nameCategoryDetail` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setString(1, t.getNameCategoryDetail());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				int idCategory = resultSet.getInt("idCategory");
				categoryDetail = new CategoryDetail(idCategoryDetail, nameCategoryDetail, idCategory);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return categoryDetail;
	}

	@Override
	public ArrayList<CategoryDetail> search(String value) {
		List<CategoryDetail> listCategoryDetails = new ArrayList<CategoryDetail>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "select * from categorydetail where nameCategoryDetail like concat('%',?, '%');";
			CallableStatement callStatement = (CallableStatement) connection.prepareCall(mysql);
			callStatement.setString(1, value);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = callStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				int idCategory = resultSet.getInt("idCategory");
				CategoryDetail categoryDetail = new CategoryDetail(idCategoryDetail, nameCategoryDetail, idCategory);
				listCategoryDetails.add(categoryDetail);
				count++;
			}
			if (count > 0) {
				System.out.println("Found " + count + " CategoryDetail string: " + value + "!");
				show((ArrayList<CategoryDetail>) listCategoryDetails);
			} else {
				System.out.println(value + ": Not found CategoryDetail !");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return (ArrayList<CategoryDetail>) listCategoryDetails;
	}

	@Override
	public void show(ArrayList<CategoryDetail> list) {
		for (CategoryDetail categoryDetail : list) {
			System.out.println(categoryDetail.toString());
		}
	}

	public CategoryDetail selectByIdCategoryAndName(CategoryDetail t) {
		CategoryDetail categoryDetail = new CategoryDetail();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "select * from  categorydetail  where idCategory= ? and nameCategoryDetail=?;";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setInt(1, t.getIdCategory());
			preStatemnt.setString(2, t.getNameCategoryDetail());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				int idCategory = resultSet.getInt("idCategory");
				categoryDetail = new CategoryDetail(idCategoryDetail, nameCategoryDetail, idCategory);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return categoryDetail;
	}

	public ArrayList<CategoryDetail> selectByIdCategory(Category t) {
		List<CategoryDetail> lisCategoryDetails = new ArrayList<CategoryDetail>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.categorydetail where idCategory =?;";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			preStatemnt.setInt(1, t.getIdCategory());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				int idCategory = resultSet.getInt("idCategory");
				CategoryDetail categoryDetail = new CategoryDetail(idCategoryDetail, nameCategoryDetail, idCategory);
				lisCategoryDetails.add(categoryDetail);
				count++;
			}
			if (count > 0) {
				System.out.println("There are " + count + " CategoryDetail!");
				show((ArrayList<CategoryDetail>) lisCategoryDetails);
			} else {
				System.out.println("No CategoryDetail!!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return (ArrayList<CategoryDetail>) lisCategoryDetails;
	}

	public void infoAll() {
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String mysql = "SELECT * FROM management_products.viewcategorydetail;";
			PreparedStatement preStatemnt = connection.prepareStatement(mysql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idCategoryDetail = resultSet.getInt("idCategoryDetail");
				String nameCategoryDetail = resultSet.getString("nameCategoryDetail");
				String nameCategory = resultSet.getString("nameCategory");
				String result = "CategoryDetailInfomation [ID=" + idCategoryDetail + ", Name=" + nameCategoryDetail
						+ ", Category=" + nameCategory + "]";
				System.out.println(result);
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(CategoryDetailDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
