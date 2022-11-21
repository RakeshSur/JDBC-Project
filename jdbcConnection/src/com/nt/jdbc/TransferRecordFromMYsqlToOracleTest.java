package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferRecordFromMYsqlToOracleTest {
	private static final String ORA_SELECT_QUERY = "INSERT INTO PRODUCT VALUES(?,?,?,?)";
	private static final String MY_SQL_INSERT_QUERY = "SELECT * FROM PRODUCT";

	public static void main(String[] args) {
		try (Connection mysqlCon = DriverManager.getConnection("jdbc:mysql:///NTAJ414DB1", "root", "root");
				Statement st = mysqlCon.createStatement();
				Connection oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott",
						"tiger");
				PreparedStatement ps = oraCon.prepareStatement(ORA_SELECT_QUERY);

		) {
			try (ResultSet rs = st.executeQuery(MY_SQL_INSERT_QUERY);) {
				int count = 0;
				if (rs != null && ps != null) {
					while (rs.next()) {
						int id = rs.getInt(1);
						String name = rs.getString(2);
						int price = rs.getInt(3);
						int qty = rs.getInt(4);
						//// set the above values INSERT query param value to insert record in
						//// oracleDatabase
						ps.setInt(1, id);
						ps.setString(2, name);
						ps.setInt(3, price);
						ps.setInt(4, qty);
						int result = ps.executeUpdate();
						if (result == 0) {
							System.out.println("NO record inserted:: ");
						} else {
							System.out.println("Record inserted::");
							count++;
						}
						System.out.println(count + " No of record are inserted:: ");

					}
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
