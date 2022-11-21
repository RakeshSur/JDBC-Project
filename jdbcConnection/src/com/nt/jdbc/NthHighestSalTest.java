//NthHighestSalTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NthHighestSalTest {

	public static void main(String[] args) {
		int no = 0;
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");

		) {
			if (sc != null) {

				System.out.println("Enter the number of highest sal you want:: ");
				no = sc.nextInt();

			}
			String nth = "SELECT * FROM(SELECT ROWNUM,ENAME,SAL FROM (SELECT * FROM EMP ORDER BY SAL DESC)) WHERE ROWNUM ="
					+ no;
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(nth);
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getInt(1) +" "+ rs.getString(2) +" "+ rs.getInt(3));
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
