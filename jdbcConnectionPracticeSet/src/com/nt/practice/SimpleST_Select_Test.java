package com.nt.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleST_Select_Test {

	public static void main(String[] args) {
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				Statement st = con.createStatement();

		) {
			String query = "SELECT * FROM PRODUCT";
			if (st != null) {
				rs = st.executeQuery(query);
				if (rs != null) {
					while (rs.next()) {
						System.out.println(rs.getInt(1) + "             " + rs.getString(2) + "                      "
								+ rs.getString(3));
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
