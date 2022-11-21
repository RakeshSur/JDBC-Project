//SelectTest2.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2 {

	/* java Ap to get emp details from given salaray range */

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// read inputs
			sc = new Scanner(System.in);
			int deptno = 0;
			if (sc != null) {
				System.out.println("Enter the deptno");
				deptno = sc.nextInt();
			}
			// System.out.println("Enter the end range emp salary");
			// endSal = sc.nextFloat();
			// load jdbc class(optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// create jdbc statement obj
			if (con != null)
				st = con.createStatement();
			// prepare sql query
			// select ename,deptno,max(sal) from emp group by deptno,ename order by max(sal)
			// asc;
			String query = "SELECT ENAME,DEPTNO,JOB FROM EMP WHERE DEPTNO=" + deptno;
			System.out.println(query);
			// send the query to d/b s/w
			if (st != null)
				rs = st.executeQuery(query);
			// process the resultset objcet
			if (rs != null) {
				while (rs.next() == true) {
					System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3));
				}
			}
			else
				System.out.println("record not found");

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
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

		} // finalyy

	}// main

}// class
