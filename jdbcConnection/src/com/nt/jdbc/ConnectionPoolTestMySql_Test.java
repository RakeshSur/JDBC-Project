package com.nt.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class ConnectionPoolTestMySql_Test {
	private static final String GET_PRODUCT = "SELECT * FROM PRODUCT";

	public static void main(String[] args) {
		MysqlConnectionPoolDataSource ds = null;
		try {
			ds = new MysqlConnectionPoolDataSource();
			ds.setDatabaseName("mysql");
			ds.setUrl("jdbc:mysql:///ntaj414db1");
			ds.setUser("root");
			ds.setPassword("root");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_PRODUCT);
				ResultSet rs = ps.executeQuery();) {
			if (rs != null) {
				boolean isEmpty = false;
				while (rs.next()) {
					System.out.println(
							rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " " + rs.getFloat(4));
					isEmpty = true;
				}
				if (!isEmpty)
					System.out.println("Record not found");
				else
					System.out.println("Record found and displayed");

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
