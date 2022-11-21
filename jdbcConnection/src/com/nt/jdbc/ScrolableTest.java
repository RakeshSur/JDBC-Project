package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrolableTest {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery("SELECT * FROM EMP");) {
			if (rs != null) {
				System.out.println("Record display (Top to Bottom");
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
							+ " " + rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));
				}
				System.out.println("============================================");
				rs.afterLast();
				System.out.println("Record diplay Bottom to Top");
				while (rs.previous()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
					+ " " + rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));
					
				}
				//display record randomly
				System.out.println("Record display randomly:::::::::::::::::::::::");
				rs.last();
				System.out.println(rs.getRow()+"-------------------->"+" "+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
				+ " " + rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));
				rs.first();
				System.out.println(rs.getRow()+"-------------------->"+" "+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
				+ " " + rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));
				rs.absolute(3);
				System.out.println(rs.getRow()+"-------------------->"+" "+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
				+ " " + rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));
				rs.absolute(-10);
				System.out.println(rs.getRow()+"-------------------->"+" "+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
				+ " " + rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));
				rs.relative(2);
				System.out.println(rs.getRow()+"-------------------->"+" "+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
				+ " " + rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));
				rs.relative(-2);
				System.out.println(rs.getRow()+"-------------------->"+" "+rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4)
				+ " " + rs.getDate(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " " + rs.getInt(8));
				
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
