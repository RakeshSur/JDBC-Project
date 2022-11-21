package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSet_Metadata_Oracel {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");) {
			ResultSetMetaData rsmd = null;
			if (rs != null) {
				rsmd = rs.getMetaData();
			}
			if (rs != null && rsmd != null) {
				// get column count
				int colCount = rsmd.getColumnCount();
				// display column
				for (int i = 1; i <= colCount; ++i) {
					System.out.println(rsmd.getColumnName(i)+"\t\t ");
				}
				System.out.println();
				// display column types
				for (int i = 1; i <= colCount; ++i) {
					System.out.println(rsmd.getColumnType(i)+"\t");
				}
				System.out.println();
				// display column values
				while (rs.next()) {
					for (int i = 1; i <= colCount; ++i) {
						System.out.println(rs.getString(i) + "\t\t\t");
					}
					System.out.println();

				}
			}

		} 
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
