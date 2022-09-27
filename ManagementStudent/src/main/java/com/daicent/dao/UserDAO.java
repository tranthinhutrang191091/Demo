package com.daicent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.daicent.database.JDBCUtil;
import com.daicent.model.Class;
import com.daicent.model.Student;
import com.daicent.model.User;

public class UserDAO implements DAOInterface<User> {

	@Override
	public int insert(User t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			User user1 = selectById(t);
			User user2 = selectByUserName(t);
			User user3 = selectByIdStudent(t);
			if (user1.getUserName() == null && user2.getUserName() == null && user3.getUserName() == null) {
				Student student = StudentDAO.getInstance().selectById(new Student(t.getIdStudent(), "", 0));
				if (student.getNameSudent() != null) {
					// Bước 2: tạo đối tượng PreparedStatement
					String sql = "INSERT INTO `student_management`.`user` (`idUser`, `userName`, `password`, `idStudent`) VALUES (?, ?, ?, ?);";
					PreparedStatement preStatemnt = connection.prepareStatement(sql);
					preStatemnt.setInt(1, t.getIdUser());
					preStatemnt.setString(2, t.getUserName());
					preStatemnt.setString(3, t.getPassword());
					preStatemnt.setInt(4, t.getIdStudent());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("Có " + result + " dòng bị thay đổi! Thêm User thành công!");
					} else {
						System.out.println(" Thêm User không thành công!");
					}
				} else {
					System.out.println("Thêm User không thành công! Student có idStudent = " + t.getIdStudent()
							+ " không tồn tại!");
				}
			} else {
				System.out.println("Thêm User không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(User t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			User user1 = selectById(t);
			if (user1.getUserName() != null) {
				// Bước 2: tạo đối tượng PreparedStatement
				String sql = "UPDATE `student_management`.`user` SET `password` = ? WHERE (`idUser` = ?);";
				PreparedStatement preStatemnt = connection.prepareStatement(sql);
				preStatemnt.setString(1, t.getPassword());
				preStatemnt.setInt(2, t.getIdUser());
				// Bước 3: thực thi câu lệnh sql
				result = preStatemnt.executeUpdate();
				// Bước 4: kiểm tra kết quả
				if (result > 0) {
					System.out.println("Có " + result + " dòng bị thay đổi! Sửa password thành công!");
				} else {
					System.out.println(" Sửa password không thành công!");
				}
			} else {
				System.out.println(" Sửa User không thành công! User có idUser = " + t.getIdUser() + " không tồn tại");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(User t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "DELETE FROM `student_management`.`user` WHERE (`idUser` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdUser());
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Xóa User thành công");
			} else {
				System.out.println(" Xóa User không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<User> selectAll() {
		ArrayList<User> listUser = new ArrayList<User>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.user;";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idUser = resultSet.getInt("idUser");
				String userName = resultSet.getString("userName");
				String password = resultSet.getString("password");
				int idStudent = resultSet.getInt("idStudent");
				User user = new User(idUser, userName, password, idStudent);
				listUser.add(user);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUser;
	}

	@Override
	public User selectById(User t) {
		User user = new User();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.user WHERE (`idUser` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdUser());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idUser = resultSet.getInt("idUser");
				String userName = resultSet.getString("userName");
				String password = resultSet.getString("password");
				int idStudent = resultSet.getInt("idStudent");
				user = new User(idUser, userName, password, idStudent);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	private User selectByUserName(User t) {
		User user = new User();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.user WHERE (`userName` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setString(1, t.getUserName());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idUser = resultSet.getInt("idUser");
				String userName = resultSet.getString("userName");
				String password = resultSet.getString("password");
				int idStudent = resultSet.getInt("idStudent");
				user = new User(idUser, userName, password, idStudent);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	private User selectByIdStudent(User t) {
		User user = new User();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.user WHERE (`idStudent` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdStudent());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idUser = resultSet.getInt("idUser");
				String userName = resultSet.getString("userName");
				String password = resultSet.getString("password");
				int idStudent = resultSet.getInt("idStudent");
				user = new User(idUser, userName, password, idStudent);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public ArrayList<User> selectByCondition(String condition) {
		ArrayList<User> listUser = new ArrayList<User>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.user WHERE " + condition + ";";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idUser = resultSet.getInt("idUser");
				String userName = resultSet.getString("userName");
				String password = resultSet.getString("password");
				int idStudent = resultSet.getInt("idStudent");
				User user = new User(idUser, userName, password, idStudent);
				listUser.add(user);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUser;
	}

	private boolean login(String userName, String password) {
		boolean result = false;
		User user = new User();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.user WHERE (`userName` = ? and  `password` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setString(1, userName);
			preStatemnt.setString(2, password);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			while (resultSet.next()) {
				int idUser = resultSet.getInt("idUser");
				String userName1 = resultSet.getString("userName");
				String password1 = resultSet.getString("password");
				int idStudent = resultSet.getInt("idStudent");
				user = new User(idUser, userName1, password1, idStudent);
				result = true;
				break;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
