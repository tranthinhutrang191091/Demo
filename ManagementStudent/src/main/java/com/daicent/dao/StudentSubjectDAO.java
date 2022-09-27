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
import com.daicent.model.StudentSubject;
import com.daicent.model.Subject;
import com.daicent.model.Unit;

public class StudentSubjectDAO implements DAOInterface<StudentSubject> {

	public static StudentSubjectDAO getInstance() {
		return new StudentSubjectDAO();
	}

	@Override
	public int insert(StudentSubject t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			StudentSubject studentSubject = selectById(t);
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
	public int update(StudentSubject t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			StudentSubject studentSubject = selectById(t);
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
	public int delete(StudentSubject t) {
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
	public ArrayList<StudentSubject> selectAll() {
		ArrayList<StudentSubject> listStudent_Subject = new ArrayList<StudentSubject>();
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
				StudentSubject student_Subject = new StudentSubject(idStudent_Subject, idStudent, idSubject, scores);
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
	public StudentSubject selectById(StudentSubject t) {
		StudentSubject studentSubject = new StudentSubject();
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
				studentSubject = new StudentSubject(idStudent_Subject, idStudent, idSubject, scores);
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
	public ArrayList<StudentSubject> selectByCondition(String condition) {
		ArrayList<StudentSubject> listStudent_Subject = new ArrayList<StudentSubject>();
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
				StudentSubject studentSubject = new StudentSubject(idStudent_Subject, idStudent, idSubject, scores);
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
