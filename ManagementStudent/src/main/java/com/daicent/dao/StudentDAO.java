package com.daicent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.daicent.database.JDBCUtil;
import com.daicent.model.Class;
import com.daicent.model.Student;
import com.daicent.model.Unit;

public class StudentDAO implements DAOInterface<Student> {

	public static StudentDAO getInstance() {
		return new StudentDAO();
	}

	@Override
	public int insert(Student t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Student student = selectById(t);
			if (student.getNameSudent() == null) {
				Class class0 = ClassDAO.getInstance().selectById(new Class(t.getIdClass(), "", 0));
				if (class0.getNameClass() != null) {
					// Bước 2: tạo đối tượng PreparedStatement
					String sql = "INSERT INTO `student_management`.`student` (`idStudent`, `nameStudent`, `idClass`) VALUES (?, ?, ?);";
					PreparedStatement preStatemnt = connection.prepareStatement(sql);
					preStatemnt.setInt(1, t.getIdStudent());
					preStatemnt.setString(2, t.getNameSudent());
					preStatemnt.setInt(3, t.getIdClass());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("Có " + result + " dòng bị thay đổi! Thêm Student thành công!");
					} else {
						System.out.println(" Thêm Student không thành công!");
					}
				} else {
					System.out.println(
							"Thêm Student không thành công! Class có idClass = " + t.getIdClass() + " không tồn tại!");
				}
			} else {
				System.out.println(
						"Thêm Student không thành công! Student có idStudent= " + t.getIdStudent() + " đã tồn tại!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Student t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Student student = selectById(t);
			if (student.getNameSudent() != null) {
				Class class0 = ClassDAO.getInstance().selectById(new Class(t.getIdClass(), "", 0));
				if (class0.getNameClass() != null) {
					// Bước 2: tạo đối tượng PreparedStatement
					String sql = "UPDATE `student_management`.`student` SET `nameStudent` = ?, `idClass` = ? WHERE (`idStudent` = ?);";
					PreparedStatement preStatemnt = connection.prepareStatement(sql);
					preStatemnt.setString(1, t.getNameSudent());
					preStatemnt.setInt(1, t.getIdClass());
					preStatemnt.setInt(3, t.getIdStudent());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("Có " + result + " dòng bị thay đổi! Sửa Student thành công!");
					} else {
						System.out.println(" Sửa Student không thành công!");
					}
				} else {
					System.out.println(
							" Sửa Student không thành công! Class có idClass = " + t.getIdClass() + " không tồn tại");
				}
			} else {
				System.out.println(
						" Sửa Student không thành công! Student có idStudent = " + t.getIdStudent() + " không tồn tại");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Student t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "DELETE FROM `student_management`.`student` WHERE (`idStudent` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdStudent());
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Xóa Student thành công");
			} else {
				System.out.println(" Xóa Student không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Student> selectAll() {
		ArrayList<Student> listStudent = new ArrayList<Student>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.student;";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idStudent = resultSet.getInt("idStudent");
				String nameStudent = resultSet.getString("nameStudent");
				int idClass = resultSet.getInt("idClass");
				Student student = new Student(idStudent, nameStudent, idClass);
				listStudent.add(student);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStudent;
	}

	@Override
	public Student selectById(Student t) {
		Student student = new Student();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.student WHERE (`idStudent` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdStudent());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idStudent = resultSet.getInt("idStudent");
				String nameStudent = resultSet.getString("nameStudent");
				int idClass = resultSet.getInt("idClass");
				student = new Student(idStudent, nameStudent, idClass);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public ArrayList<Student> selectByCondition(String condition) {
		ArrayList<Student> listStudent = new ArrayList<Student>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.student WHERE " + condition + ";";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idStudent = resultSet.getInt("idStudent");
				String nameStudent = resultSet.getString("nameStudent");
				int idClass = resultSet.getInt("idClass");
				Student student = new Student(idStudent, nameStudent, idClass);
				listStudent.add(student);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStudent;
	}

}
