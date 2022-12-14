package com.daicent.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.daicent.annotation.UserAnnotation;
import com.daicent.database.JDBCUtil;
import com.daicent.model.User;

public class UserReflection {
	public static UserReflection getInstance() {
		return new UserReflection();
	}

	public int insert(Object t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Class<?> listClass = t.getClass();
			Field[] listField = listClass.getDeclaredFields();
			int idUser = 0;
			String userName = "";
			String password = "";
			int idStudent = 0;
			for (Field f : listField) {

				if (f.getName().equals("idUser")) {
					f.setAccessible(true);
					idUser = (Integer) f.get(t);
				}
				if (f.getName().equals("userName")) {
					f.setAccessible(true);
					userName = (String) f.get(t);
				}
				if (f.getName().equals("password")) {
					f.setAccessible(true);
					password = (String) f.get(t);
				}
				if (f.getName().equals("idStudent")) {
					f.setAccessible(true);
					idStudent = (Integer) f.get(t);
				}

			}
			// Bước 2: tạo đối tượng PreparedStatement

			Class user = User.class;
			Annotation annotation = user.getAnnotation(UserAnnotation.class);
			UserAnnotation userAnnotation = null;
			if (annotation instanceof UserAnnotation) {
				userAnnotation = (UserAnnotation) annotation;
			}
			// INSERT INTO `student_management`.`user` (`idUser`, `userName`, `password`,
			// `idStudent`) VALUES ('4', 'sfs', 'sdg', '7');

			String sql = "INSERT INTO `student_management`.`user` (" + userAnnotation.idUser() + ","
					+ userAnnotation.userName() + "," + userAnnotation.password() + "," + userAnnotation.idStudent()
					+ ") VALUES (?, ?, ?, ?);";

			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, idUser);
			preStatemnt.setString(2, userName);
			preStatemnt.setString(3, password);
			preStatemnt.setInt(4, idStudent);
			System.out.println(preStatemnt.toString());
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Thêm User thành công!");
			} else {
				System.out.println(" Thêm User không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int update(Object t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Class<?> listClass = t.getClass();
			Field[] listField = listClass.getDeclaredFields();
			int idUser = 0;
			String userName = "";
			String password = "";
			int idStudent = 0;
			for (Field f : listField) {

				if (f.getName().equals("idUser")) {
					f.setAccessible(true);
					idUser = (Integer) f.get(t);
				}
				if (f.getName().equals("userName")) {
					f.setAccessible(true);
					userName = (String) f.get(t);
				}
				if (f.getName().equals("password")) {
					f.setAccessible(true);
					password = (String) f.get(t);
				}
				if (f.getName().equals("idStudent")) {
					f.setAccessible(true);
					idStudent = (Integer) f.get(t);
				}

			}
			Class user = User.class;
			Annotation annotation = user.getAnnotation(UserAnnotation.class);
			UserAnnotation userAnnotation = null;
			if (annotation instanceof UserAnnotation) {
				userAnnotation = (UserAnnotation) annotation;
			}
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "UPDATE `student_management`.`user` SET " + userAnnotation.password() + " = ? WHERE ("
					+ userAnnotation.idUser() + " = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setString(1, password);
			preStatemnt.setInt(2, idUser);
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			System.out.println(preStatemnt.toString());
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Sửa User thành công!");
			} else {
				System.out.println(" Sửa User không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(Object t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Class<?> listClass = t.getClass();
			Field[] listField = listClass.getDeclaredFields();
			int idUser = 0;
			String userName = "";
			String password = "";
			int idStudent = 0;
			for (Field f : listField) {

				if (f.getName().equals("idUser")) {
					f.setAccessible(true);
					idUser = (Integer) f.get(t);
				}
			}
			Class user = User.class;
			Annotation annotation = user.getAnnotation(UserAnnotation.class);
			UserAnnotation userAnnotation = null;
			if (annotation instanceof UserAnnotation) {
				userAnnotation = (UserAnnotation) annotation;
			}
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "DELETE FROM `student_management`.`user` WHERE (" + userAnnotation.idUser() + " = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, idUser);
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Xóa User thành công!");
			} else {
				System.out.println(" Xóa User không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<User> selectAll() {
		List<User> list = new ArrayList<User>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Class<?> listClass = Class.forName("com.daicent.model.User");
			Constructor<?> constructors = listClass.getConstructor(int.class, String.class, String.class, int.class);
			Class user = User.class;
			Annotation annotation = user.getAnnotation(UserAnnotation.class);
			UserAnnotation userAnnotation = null;
			if (annotation instanceof UserAnnotation) {
				userAnnotation = (UserAnnotation) annotation;
			}
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT " + userAnnotation.idUser() + "," + userAnnotation.userName() + ","
					+ userAnnotation.password() + "," + userAnnotation.idStudent() + " FROM student_management.user;";
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
				User user0 = (User) constructors.newInstance(idUser, userName, password, idStudent);
				list.add(user0);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			Logger.getLogger(UserReflection.class.getName()).log(Level.SEVERE, null, e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<User>) list;
	}

}
