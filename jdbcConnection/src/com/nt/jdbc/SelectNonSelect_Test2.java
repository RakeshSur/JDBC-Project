package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelect_Test2 {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				Statement st = con.createStatement();) {
			String query = null;
			if (sc != null) {
				System.out.println("Enter the Query:::");
				query = sc.nextLine();
			}
			if (st != null) {
				boolean flag = st.execute(query);
				if (flag) {
					System.out.println("SELECT Query is executed:::::::::::");
					try(ResultSet rs = st.getResultSet();
							){
						if(rs!=null) {
							while(rs.next()) {
								System.out.println(rs.getInt(1) + "             " + rs.getString(2) + "                      " + rs.getString(3));
							}
						}
					}
				}
				else {
					System.out.println("NON SELECT query is executed:::");
					int count = st.getUpdateCount();
					System.out.println("No of  affected records are ::"+count);
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
