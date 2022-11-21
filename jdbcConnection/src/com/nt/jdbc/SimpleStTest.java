package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Scanner;

//import com.mysql.cj.protocol.Resultset;

public class SimpleStTest {

	public static void main(String[] args) {
		try (
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");

				Statement st = con.createStatement();

		) {
			String query = "SELECT DNAME,DEPTNO FROM DEPT";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getString(1) + " " + rs.getInt(2));
				}

			}
			else {
				System.out.println("Record not found");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
