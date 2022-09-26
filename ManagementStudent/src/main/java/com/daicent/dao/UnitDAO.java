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
import com.daicent.model.Unit;

public class UnitDAO implements DAOInterface<Unit> {

	public static UnitDAO getInstance() {
		return new UnitDAO();
	}

	@Override
	public int insert(Unit t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "INSERT INTO `student_management`.`unit` (`idUnit`, `nameUnit`, `idSchool`) VALUES (?, ?, ?);";
			Unit unit = selectById(t);
			if (unit.getNameUnit() == null) {
				School school = SchoolDAO.getInstance().selectById(new School(t.getIdSchool(), ""));
				if (school.getNameSchool() != null) {
					PreparedStatement preStatemnt = connection.prepareStatement(sql);
					preStatemnt.setInt(1, t.getIdUnit());
					preStatemnt.setString(2, t.getNameUnit());
					preStatemnt.setInt(3, t.getIdSchool());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("Có " + result + " dòng bị thay đổi! Thêm Unit thành công");
					} else {
						System.out.println(" Thêm Unit không thành công!");
					}
				} else {
					System.out.println(
							"School có idSchool = " + t.getIdSchool() + " không tồn tại! Thêm Unit không thành công!");
				}
			} else {
				System.out.println("Unit có idUnit = " + t.getIdUnit() + " đã tồn tại! Thêm Unit không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Unit t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			Unit unit = selectById(t);
			if (unit.getNameUnit() != null) {
				School school = SchoolDAO.getInstance().selectById(new School(t.getIdSchool(), ""));
				if (school.getNameSchool() != null) {
					// Bước 2: tạo đối tượng PreparedStatement
					String sql = "UPDATE `student_management`.`unit` SET `nameUnit` = ?, `idSchool` = ? WHERE (`idUnit` = ?);";
					PreparedStatement preStatemnt = connection.prepareStatement(sql);
					preStatemnt.setString(1, t.getNameUnit());
					preStatemnt.setInt(2, t.getIdSchool());
					preStatemnt.setInt(3, t.getIdUnit());
					// Bước 3: thực thi câu lệnh sql
					result = preStatemnt.executeUpdate();
					// Bước 4: kiểm tra kết quả
					if (result > 0) {
						System.out.println("Có " + result + " dòng bị thay đổi! Sửa Unit thành công!");
					} else {
						System.out.println(" Sửa Unit không thành công!");
					}
				} else {
					System.out.println(
							" Sửa Unit không thành công! School có idSchool = " + t.getIdSchool() + " không tồn tại");
				}
			} else {
				System.out.println(" Sửa Unit không thành công! Unit có idUnit = " + t.getIdUnit() + " không tồn tại");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Unit t) {
		int result = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "DELETE FROM `student_management`.`unit` WHERE (`idUnit` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdUnit());
			// Bước 3: thực thi câu lệnh sql
			result = preStatemnt.executeUpdate();
			// Bước 4: kiểm tra kết quả
			if (result > 0) {
				System.out.println("Có " + result + " dòng bị thay đổi! Xóa Unit thành công");
			} else {
				System.out.println(" Xóa Unit không thành công!");
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Unit> selectAll() {
		ArrayList<Unit> listUnit = new ArrayList<Unit>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.unit;";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idUnit = resultSet.getInt("idUnit");
				String nameUnit = resultSet.getString("nameUnit");
				int idSchool = resultSet.getInt("idSchool");
				Unit unit = new Unit(idUnit, nameUnit, idSchool);
				listUnit.add(unit);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUnit;
	}

	@Override
	public Unit selectById(Unit t) {
		Unit unit = new Unit();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.unit WHERE (`idUnit` = ?);";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			preStatemnt.setInt(1, t.getIdUnit());
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idUnit = resultSet.getInt("idUnit");
				String nameUnit = resultSet.getString("nameUnit");
				int idSchool = resultSet.getInt("idSchool");
				unit = new Unit(idUnit, nameUnit, idSchool);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unit;
	}

	@Override
	public ArrayList<Unit> selectByCondition(String condition) {
		ArrayList<Unit> listUnit = new ArrayList<Unit>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection connection = JDBCUtil.getConnection();
			// Bước 2: tạo đối tượng PreparedStatement
			String sql = "SELECT * FROM student_management.unit WHERE " + condition + ";";
			PreparedStatement preStatemnt = connection.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh sql
			ResultSet resultSet = preStatemnt.executeQuery();
			// Bước 4: kiểm tra kết quả
			int count = 0;
			while (resultSet.next()) {
				int idUnit = resultSet.getInt("idUnit");
				String nameUnit = resultSet.getString("nameUnit");
				int idSchool = resultSet.getInt("idSchool");
				Unit unit = new Unit(idUnit, nameUnit, idSchool);
				listUnit.add(unit);
				count++;
			}
			// Bước 5: ngắt kết nối với database
			JDBCUtil.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUnit;
	}

}
