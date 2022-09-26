package com.daicent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.daicent.database.JDBCUtil;
import com.daicent.model.School;
import com.daicent.model.Subject;

public class SubjectDAO implements DAOInterface<Subject> {

	public static SubjectDAO getInstance() {
		return new SubjectDAO();
	}

	@Override
	public int insert(Subject t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Subject subject = selectById(t);
			if (subject.getNameSubject() == null) {
				// Bước 2: tạo đối tượng PreparedStatement
				String sql = "INSERT INTO `student_management`.`subject` (`idSubject`, `nameSubject`) VALUES (?, ?)";
				PreparedStatement preStatemnt = connection.prepareStatement(sql);
				preStatemnt.setInt(1, t.getIdSubjcet());
				preStatemnt.setString(2, t.getNameSubject());
				// Bước 3: thực thi câu lệnh sql
				result = preStatemnt.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println(" Thêm Subject thành công!");
				} else {
					System.out.println(" Thêm Subject không thành công!");
				}
			} else {
				System.out.println("Subject có idSubject = " + t.getIdSubjcet() + " đã tồn tại");
			}

			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Subject t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Subject subject = selectById(t);
			if (subject.getNameSubject() != null) {
				// Bước 2: tạo đối tượng PreparedStatement
				String sql = "UPDATE `student_management`.`subject` SET `nameSubject` = ? WHERE (`idSubject` = ?);";
				PreparedStatement preStatemnt = connection.prepareStatement(sql);
				preStatemnt.setString(1, t.getNameSubject());
				preStatemnt.setInt(2, t.getIdSubjcet());
				// Bước 3: thực thi câu lệnh sql
				result = preStatemnt.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println("Có " + result + " dòng bị thay đổi! Sửa Subject thành công!");
				} else {
					System.out.println(" Sửa Subject không thành công!");
				}
			} else {
				System.out.println("Subject có idSubject = " + t.getIdSubjcet() + " không tồn tại");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Subject t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "DELETE FROM `student_management`.`subject` WHERE (`idSubject` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdSubjcet());
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Xóa Subject thành công!");
			} else {
				System.out.println(" Xóa Subject không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Subject> selectAll() {
		ArrayList<Subject> listSubject = new ArrayList<Subject>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 3: thực thi câu lệnh sql
			String sql = "SELECT * FROM student_management.subject;";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idSubject = resultSet.getInt("idSubject");
				String nameSubject = resultSet.getString("nameSubject");
				Subject subject = new Subject(idSubject, nameSubject);
				listSubject.add(subject);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSubject;
	}

	@Override
	public Subject selectById(Subject t) {
		Subject subject = new Subject();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.subject WHERE (`idSubject` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdSubjcet());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idSubject = resultSet.getInt("idSubject");
				String nameSubject = resultSet.getString("nameSubject");
				subject = new Subject(idSubject, nameSubject);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subject;
	}

	@Override
	public ArrayList<Subject> selectByCondition(String condition) {
		ArrayList<Subject> listSubject = new ArrayList<Subject>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.subject WHERE " + condition + ";";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idSubject = resultSet.getInt("idSubject");
				String nameSubject = resultSet.getString("nameSubject");
				Subject subject = new Subject(idSubject, nameSubject);
				listSubject.add(subject);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSubject;
	}

}
