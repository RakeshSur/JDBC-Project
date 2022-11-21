//Lob_InsertionTest_Oracle .java
package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Lob_InsertionTest_Oracle {
	private static final String ACTOR_INSERT_QUERY = "INSERT INTO ACTORS_INFO VALUES(S1.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareStatement(ACTOR_INSERT_QUERY);) {
			String name = null, photpath = null, profilepath = null;
			if (sc != null) {
				System.out.println("Enter the details:: ");
				System.out.println("Enter the name of the actor:: ");
				name = sc.next();
				System.out.println("Enter the image of the actor/acters:: ");
				photpath = sc.next().trim().replace("?", "");
				System.out.println("Enter the profile of the actor/acters::");
				sc.nextLine();
				profilepath = sc.next().trim().replace("?", "");

			}
			try (InputStream is = new FileInputStream(photpath); Reader reader = new FileReader(profilepath);) {
				if (ps != null) {
					ps.setString(1, name);
					ps.setBinaryStream(2, is);
					ps.setCharacterStream(3, reader);
					// execute the query
					int count = ps.executeUpdate();
					if (count == 0)
						System.out.println("Record not inserted:: ");
					else
						System.out.println("Record inserted:: ");

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
