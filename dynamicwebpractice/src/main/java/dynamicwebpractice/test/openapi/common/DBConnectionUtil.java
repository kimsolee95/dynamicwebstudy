package dynamicwebpractice.test.openapi.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
	/*
	 * DB connection 객체 생성
	 * */
	public Connection getDBConnection() {
		
		Connection conn = null;
		String dbFile = "C:\\assignment01\\db\\test1.db"; //"C:\\dynamicwebpractice\\test1.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			return conn;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
