package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelect_Test {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				Statement st = con.createStatement();

		) {
			String qury = null;
			if (sc != null) {
				System.out.println("Enter the query:::");
				qury = sc.nextLine();
			}
			// execute the query
			if (st != null) {
				boolean flag = st.execute(qury);
				if (flag) {
					System.out.println("SELECT query is executed::");
					try (ResultSet rs = st.getResultSet();) {
						if (rs != null) {
							while (rs.next()) {
								System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getFloat(3) + " "
										+ rs.getString(4));
							}
						}

					}
				} else {
					System.out.println("Non select query is executed");
					int count = st.getUpdateCount();
					System.out.println("NO of record are affected " + count);
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
