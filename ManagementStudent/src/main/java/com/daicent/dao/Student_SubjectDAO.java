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
import com.daicent.model.Student_Subject;
import com.daicent.model.Subject;
import com.daicent.model.Unit;

public class Student_SubjectDAO implements DAOInterface<Student_Subject> {

	public static Student_SubjectDAO getInstance() {
		return new Student_SubjectDAO();
	}

	@Override
	public int insert(Student_Subject t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Student_Subject studentSubject = selectById(t);
			if (studentSubject.getIdStudent_Subject() == 0) {
				Student student = StudentDAO.getInstance().selectById(new Student(t.getIdStudent(), "", 0));
				if (student.getNameSudent() != null) {
					Subject subject = SubjectDAO.getInstance().selectById(new Subject(t.getIdSubject(), ""));
					if (subject.getNameSubject() != null) {
						// Bước 2: tạo đối tượng PreparedStatement
						String sql = "INSERT INTO `student_management`.`student_subject` (`idStudent_Subject`, `idStudent`, `idSubject`, `scores`) VALUES (?, ?, ?, ?);";
						PreparedStatement preStatemnt = connection.prepareStatement(sql);
						preStatemnt.setInt(1, t.getIdStudent_Subject());
						preStatemnt.setInt(2, t.getIdStudent());
						preStatemnt.setInt(3, t.getIdSubject());
						preStatemnt.setFloat(4, t.getScores());
						// Bước 3: thực thi câu lệnh sql
						result = preStatemnt.executeUpdate();
						// Bước 4: kiểm tra kết quả
						if (result > 0) {
							System.out.println("Có " + result + " dòng bị thay đổi! Thêm Student_Subject thành công");
						} else {
							System.out.println(" Thêm Student_Subject không thành công!");
						}
					} else {
						System.out.println("Thêm Student_Subject không thành công! Subject có idSubject = "
								+ t.getIdSubject() + " không tồn tại!");
					}
				} else {
					System.out.println("Thêm Student_Subject không thành công! Student có idStudent = "
							+ t.getIdStudent() + " không tồn tại!");
				}
			} else {
				System.out.println("Student_Subject = " + t.getIdStudent_Subject()
						+ " đã tồn tại! Thêm Student_Subject không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Student_Subject t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Student_Subject studentSubject = selectById(t);
			if (studentSubject.getIdStudent_Subject() != 0) {
				Student student = StudentDAO.getInstance().selectById(new Student(t.getIdStudent(), "", 0));
				if (student.getNameSudent() != null) {
					Subject subject = SubjectDAO.getInstance().selectById(new Subject(t.getIdSubject(), ""));
					if (subject.getNameSubject() != null) {
						// Bước 2: tạo đối tượng PreparedStatement
						String sql = "UPDATE `student_management`.`student_subject` SET `idStudent` = ?, `idSubject` = ?, `scores` = ? WHERE (`idStudent_Subject` = ?);";
						PreparedStatement preStatemnt = connection.prepareStatement(sql);
						preStatemnt.setInt(1, t.getIdStudent());
						preStatemnt.setInt(2, t.getIdSubject());
						preStatemnt.setFloat(3, t.getScores());
						preStatemnt.setInt(4, t.getIdStudent_Subject());
						// Bước 3: thực thi câu lệnh sql
						result = preStatemnt.executeUpdate();
						// Bước 4: kiểm tra kết quả
						if (result > 0) {
							System.out.println("Có " + result + " dòng bị thay đổi! Sửa Student_Subject thành công!");
						} else {
							System.out.println(" Sửa Student_Subject không thành công!");
						}
					} else {
						System.out.println(" Sửa Student_Subject không thành công!Subject có idSubject = "
								+ t.getIdSubject() + " không tồn tại!");
					}
				} else {
					System.out.println(" Sửa Student_Subject không thành công!Student có idStudent = "
							+ t.getIdStudent() + " không tồn tại!");
				}
			} else {
				System.out.println(" Sửa Student_Subject không thành công!Student_Subject có idStudent_Subject = "
						+ t.getIdStudent_Subject() + " không tồn tại!");

			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Student_Subject t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "DELETE FROM `student_management`.`student_subject` WHERE (`idStudent_Subject` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdStudent_Subject());
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Xóa Student_Subject thành công!");
			} else {
				System.out.println(" Xóa Student_Subject không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Student_Subject> selectAll() {
		ArrayList<Student_Subject> listStudent_Subject = new ArrayList<Student_Subject>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.student_subject;";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idStudent_Subject = resultSet.getInt("idStudent_Subject");
				int idStudent = resultSet.getInt("idStudent");
				int idSubject = resultSet.getInt("idSubject");
				Float scores =resultSet.getFloat("scores");
				Student_Subject student_Subject = new Student_Subject(idStudent_Subject, idStudent, idSubject, scores);
				listStudent_Subject.add(student_Subject);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStudent_Subject;
	}

	@Override
	public Student_Subject selectById(Student_Subject t) {
		Student_Subject studentSubject = new Student_Subject();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM `student_management`.`student_subject` WHERE (`idStudent_Subject` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdStudent_Subject());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idStudent_Subject = resultSet.getInt("idStudent_Subject");
				int idStudent = resultSet.getInt("idStudent");
				int idSubject = resultSet.getInt("idSubject");
				Float scores = resultSet.getFloat("scores");
				studentSubject = new Student_Subject(idStudent_Subject, idStudent, idSubject, scores);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentSubject;
	}

	@Override
	public ArrayList<Student_Subject> selectByCondition(String condition) {
		ArrayList<Student_Subject> listStudent_Subject = new ArrayList<Student_Subject>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.student_subject WHERE " + condition + ";";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idStudent_Subject = resultSet.getInt("idStudent_Subject");
				int idStudent = resultSet.getInt("idStudent");
				int idSubject = resultSet.getInt("idSubject");
				Float scores = resultSet.getFloat("scores");
				Student_Subject studentSubject = new Student_Subject(idStudent_Subject, idStudent, idSubject, scores);
				listStudent_Subject.add(studentSubject);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStudent_Subject;
	}

}
