package com.daicent.ManagementStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import com.daicent.dao.SchoolDAO;
import com.daicent.database.JDBCUtil;
import com.daicent.model.School;
import com.daicent.model.Student;
import com.daicent.model.Subject;

public class TestSchool {
	public static void main(String[] args) {
//		School school1 = new School(7, "Trường Tiểu Học IA20");
//		School school2 = new School(9, "Trường Tiểu Học Âu Lạc");
//		SchoolDAO.getInstance().insert(school1);
//		SchoolDAO.getInstance().update(school1);
//		SchoolDAO.getInstance().delete(school1);
//		SchoolDAO.getInstance().insert(school2);

//		ArrayList<School> listSchool = SchoolDAO.getInstance().selectAll();
//		for (School sc : listSchool) {
//			System.out.println(sc.toString());
//		}

//		School school1 = new School(); school1.setIdSchool(10);
//		School school2 = SchoolDAO.getInstance().selectById(school1);
		
//		String a = "nameSchool like concat('%','Văn', '%')";
//		ArrayList<School> listSchool = SchoolDAO.getInstance().selectByCondition(a);
//		for (School sc : listSchool) {
//			System.out.println(sc.toString());
//		}

		// School sc = new School("x\" or 1=1; -- ","");
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.avgscoresstudent;";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			System.out.println("Bạn đã thực thi: " + sql);
			int count = 0;
			while (resultSet.next()) {
				int idStudent =resultSet.getInt("idStudent");
				String nameStudent =resultSet.getString("nameStudent");
				int idClass =resultSet.getInt("idClass");
				String nameClass =resultSet.getString("nameClass");
				int idUnit =resultSet.getInt("idUnit");
				String nameUnit =resultSet.getString("nameUnit");
				int idSchool =resultSet.getInt("idSchool");
				String nameSchool =resultSet.getString("nameSchool");
				int countSubject =resultSet.getInt("countSubject");
				Float avgScores =resultSet.getFloat("avgScores");
				String result ="StudentInfomation [idStudent=" + idStudent
						+ ", nameStudent=" + nameStudent 
						+ ", idClass=" + idClass 
						+ ", nameClass=" + nameClass 
						+ ", idUnit=" + idUnit 
						+ ", nameUnit=" + nameUnit 
						+ ", idSchool=" + idSchool 
						+ ", nameSchool=" + nameSchool 
						+ ", countSubject=" + countSubject 
						+ ", avgScores=" + avgScores 
						+ "]";
				System.out.println(result);
				
				
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "call findStudent(?);";
			String s ="Trang";
			CallableStatement callStatement = (CallableStatement) connection.prepareCall(sql);
			callStatement.setString(1, s);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = callStatement.executeQuery();
			// Bước 4: kiểm tra kết quả
			System.out.println("Bạn đã thực thi: " + sql);
			int count = 0;
			while (resultSet.next()) {
				int idStudent = resultSet.getInt("idStudent");
				String nameStudent =resultSet.getString("nameStudent");
				String nameClass= resultSet.getString("nameClass");
				String result ="StudentInfomation [idStudent=" + idStudent
						+ ", nameStudent=" + nameStudent 
						+ ", nameClass=" + nameClass 
						+ "]";
				System.out.println(result);
				count++;
			}
			System.out.println("Có " + count + " dòng!");
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
