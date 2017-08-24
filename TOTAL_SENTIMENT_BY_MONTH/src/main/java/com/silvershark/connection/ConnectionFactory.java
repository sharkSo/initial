package com.silvershark.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			DriverManager.getConnection("jdbc:mysql://localhost:3306/total_comments_by_day?useTimezone=true&serverTimezone=UTC", "root", "root");
		} catch (SQLException e) {
//			throw new RuntimeException(e);
			e.printStackTrace();
		}
	}
}