package com.daicent.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class JDBCUtil {

	public static Connection getConnection() {
		Connection connect = null;
		try {
			
//			Driver driver = new Driver();
			//Đăng ký MySQL với DriverManager
//			DriverManager.registerDriver(driver);
			String url ="jdbc:mySQL://localhost:3306/management_products";
			String userName="root";
			String password ="112358";
			// Tạo kết nối
			connect=DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	public static void closeConnection(Connection connect) {
		if(connect!=null) {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void printInfo(Connection connect) {
		if(connect!=null) {
			try {
				DatabaseMetaData m = connect.getMetaData();
				System.out.println(m.getDatabaseProductName());

				System.out.println(m.getDatabaseProductVersion());
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

}
