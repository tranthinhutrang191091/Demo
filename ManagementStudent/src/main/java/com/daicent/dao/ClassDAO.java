package com.daicent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.daicent.database.JDBCUtil;
import com.daicent.model.Class;
import com.daicent.model.School;
import com.daicent.model.Unit;

public class ClassDAO implements DAOInterface<Class> {

	public static ClassDAO getInstance() {
		return new ClassDAO();
	}

	@Override
	public int insert(Class t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "INSERT INTO `student_management`.`class` (`idClass`, `nameClass`, `idUnit`) VALUES (?, ?, ?);";

			Class classO = selectById(t);
			if (classO.getNameClass() != null) {
				System.out
						.println("Thêm Class không thành công! Class có idClass = " + t.getIdClass() + " đã tồn tại!");
			} else {
				Unit unit = UnitDAO.getInstance().selectById(new Unit(t.getIdUnit(), "", 0));
				if (unit.getNameUnit() != null) {
					PreparedStatement preStatemnt = connection.prepareStatement(sql);
					preStatemnt.setInt(1, t.getIdClass());
					preStatemnt.setString(2, t.getNameClass());
					preStatemnt.setInt(3, t.getIdUnit());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println(" Thêm Class thành công!");
					} else {
						System.out.println(" Thêm Class không thành công!");
					}
				} else {
					System.out.println("Unit có idUnit = " + t.getIdUnit() + " không tồn tại");
				}
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Class t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Class classO = selectById(t);
			if (classO.getNameClass() != null) {
				Unit unit = UnitDAO.getInstance().selectById(new Unit(t.getIdUnit(), "", 0));
				if (unit.getNameUnit() != null) {
					// Bước 2: tạo đối tượng PreparedStatement
					String sql = "UPDATE `student_management`.`class` SET `nameClass` = ?, `idUnit` = ? WHERE (`idClass` = ?);";
					PreparedStatement preStatemnt = connection.prepareStatement(sql);
					preStatemnt.setString(1, t.getNameClass());
					preStatemnt.setInt(2, t.getIdUnit());
					preStatemnt.setInt(3, t.getIdClass());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("Có " + result + " dòng bị thay đổi! Sửa Class thành công!");
					} else {
						System.out.println(" Sửa Class không thành công!");
					}
				} else {
					System.out.println(
							" Sửa Class không thành công! Unit có idUnit = " + t.getIdUnit() + " không tồn tại");
				}
			} else {
				System.out.println(
						"Sửa Class không thành công! Class có idClass = " + t.getIdClass() + " không tồn tại!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Class t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "DELETE FROM `student_management`.`class` WHERE (`idClass` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdClass());
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Xóa Class thành công!");
			} else {
				System.out.println(" Xóa Class không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Class> selectAll() {
		ArrayList<Class> listClass = new ArrayList<Class>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 3: thực thi câu lệnh sql bằng PreparedStatement
			String sql = "SELECT * FROM student_management.class;";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idClass = resultSet.getInt("idClass");
				String nameClass = resultSet.getString("nameClass");
				int idUnit = resultSet.getInt("idUnit");
				Class classO = new Class(idClass, nameClass, idUnit);
				listClass.add(classO);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listClass;
	}

	@Override
	public Class selectById(Class t) {
		Class classO = new Class();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.class WHERE (`idClass` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdClass());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idClass = resultSet.getInt("idClass");
				String nameClass = resultSet.getString("nameClass");
				int idUnit = resultSet.getInt("idUnit");
				classO = new Class(idClass, nameClass, idUnit);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classO;
	}

	@Override
	public ArrayList<Class> selectByCondition(String condition) {
		ArrayList<Class> listClass = new ArrayList<Class>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.class WHERE " + condition + ";";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idClass = resultSet.getInt("idClass");
				String nameClass = resultSet.getString("nameClass");
				int idUnit = resultSet.getInt("idUnit");
				Class classO = new Class(idClass, nameClass, idUnit);
				listClass.add(classO);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listClass;
	}

}
