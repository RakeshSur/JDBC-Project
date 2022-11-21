//UpdateTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			// read inputs
			sc = new Scanner(System.in);
			String city = null;
			if (sc != null) {
				System.out.println("Enter the adress of student");
				city = sc.next();
			}
			// convert then input value for sql query
			city = "'" + city + "'";// gives 'hyd'
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// create jdbc statement obj
			if (con != null)
				st = con.createStatement();
			// prepare sql query
			// DELETE FROM STUDENT WHERE CITY='HYD'
			String query = "DELETE FROM STUDENT WHERE CITY=" + city;
			System.out.println(query);
			// send and execute the query in database s/w
			int count = 0;
			if (st != null)
				count = st.executeUpdate(query);
			if (count == 0)
				System.out.println("No record(S) found for deletion");
			else
				System.out.println("No of record that are affected are:: " + count);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
