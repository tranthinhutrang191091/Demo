package com.daicent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.daicent.database.JDBCUtil;
import com.daicent.model.School;

public class SchoolDAO implements DAOInterface<School> {

	public static SchoolDAO getInstance() {
		return new SchoolDAO();
	}

	@Override
	public int insert(School t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			School school = selectById(new School(t.getIdSchool(), ""));
			if (school.getNameSchool() == null) {
				// Bước 2: tạo đối tượng PreparedStatement
				String sql = "INSERT INTO `student_management`.`school` (`idSchool`, `nameSchool`) VALUES (?,?);";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, t.getIdSchool());
				preparedStatement.setString(2, t.getNameSchool());
				// Bước 3: thực thi câu lệnh sql
				result = preparedStatement.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println("Có " + result + " dòng bị thay đổi! Thêm School thành công");
				} else {
					System.out.println(" Thêm School không thành công!");
				}
			} else {
				System.out.println(
						" Thêm School không thành công! School có idSchool = " + t.getIdSchool() + " đã tồn tại");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(School t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "UPDATE `student_management`.`school` SET `nameSchool` = ? WHERE (`idSchool` = ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, t.getNameSchool());
			preparedStatement.setInt(2, t.getIdSchool());
			// Bước 3: thực thi câu lệnh sql
			result = preparedStatement.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Sửa School thành công");
			} else {
				System.out.println(" Thay đổi School không thành công! School có idSchool = " + t.getIdSchool()
						+ " không tồn tại!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(School t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng Statement
			String sql = "DELETE FROM `student_management`.`school` WHERE (`idSchool` = ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, t.getIdSchool());
			// Bước 3: thực thi câu lệnh sql
			result = preparedStatement.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Xóa School thành công!");
			} else {
				System.out.println(
						" Xóa School không thành công! School có idSchool = " + t.getIdSchool() + " không tồn tại!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<School> selectAll() {
		ArrayList<School> listSchool = new ArrayList<School>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng Statement
			String sql = "SELECT * FROM student_management.school;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preparedStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idSchool = resultSet.getInt("idSchool");
				String nameSchool = resultSet.getString("nameSchool");
				School school = new School(idSchool, nameSchool);
				listSchool.add(school);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSchool;
	}

	@Override
	public School selectById(School t) {
		School school = new School();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng Statement
			String sql = "SELECT * FROM `student_management`.`school` WHERE (`idSchool` = ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, t.getIdSchool());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preparedStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idSchool = resultSet.getInt("idSchool");
				String nameSchool = resultSet.getString("nameSchool");
				school = new School(idSchool, nameSchool);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return school;
	}

	@Override
	public ArrayList<School> selectByCondition(String condition) {
		ArrayList<School> listSchool = new ArrayList<School>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng Statement
			String sql = "SELECT * FROM student_management.school WHERE " + condition + ";";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preparedStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idSchool = resultSet.getInt("idSchool");
				String nameSchool = resultSet.getString("nameSchool");
				School school = new School(idSchool, nameSchool);
				listSchool.add(school);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSchool;
	}

}
