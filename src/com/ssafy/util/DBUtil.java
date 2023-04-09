package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private final String URL = "jdbc:mysql://127.0.0.1:3306/aptcrawl?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private final String USER_ID = "ssafy";
	private final String USER_PW = "ssafy";

	private DBUtil() {
	}

	private static DBUtil instance;

	public static DBUtil getInstance() {
		if (instance == null)
			instance = new DBUtil();
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER_ID, USER_PW);
	}

	public void close(AutoCloseable... autoClosables) {
		try {
			for (AutoCloseable ac : autoClosables) {
				if (ac != null)
					ac.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
