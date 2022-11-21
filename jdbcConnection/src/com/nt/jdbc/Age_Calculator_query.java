package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Age_Calculator_query {
	private static final String GET_PERSON_AGE_QUERY = "SELECT (SYSDATE-DOB)/365.25 FROM CUSTOMER_INFO WHERE CNO =?";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareStatement(GET_PERSON_AGE_QUERY);) {
			int no = 0;
			if (sc != null) {
				System.out.println("Enter the no:: ");
				no = sc.nextInt();
				if (ps != null) {
					ps.setInt(1, no);
					try (ResultSet rs = ps.executeQuery()) {
						if(rs!=null) {
						if (rs.next()) {
							System.out.println("person age is :: " + rs.getFloat(1));
						}
						else {
							System.out.println("person is not found:: ");
						}
						}
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
