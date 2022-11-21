package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PostgresSql_InsertTest {
	private static final String INSERT_QUERY = "INSERT INTO STUDENTS VALUES(NEXTVAL('SID_SEQ'),?,?,?)";

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ntaj414db1", "postgres",
				"tiger");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
				Scanner sc = new Scanner(System.in);) {
			String name = null;
			float avg = 0.0f;
			String addr = null;
			if (sc != null) {
				System.out.println("Enter the name of the student:: ");
				name = sc.next();
				System.out.println("Enter the avg of student:: ");
				avg = sc.nextFloat();
				System.out.println("Enter the address of student:: ");
				addr = sc.next();
			}
			if (ps != null) {
				ps.setString(1, name);
				ps.setFloat(2, avg);
				ps.setString(3, addr);
				// execute the query
				int count = ps.executeUpdate();
				if (count == 0)
					System.out.println("Record not inserted::::::");
				else
					System.out.println("Record inserted::");

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
