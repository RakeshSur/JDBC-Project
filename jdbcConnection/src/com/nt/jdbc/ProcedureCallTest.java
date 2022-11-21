//ProcedureCallTest.java
package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class ProcedureCallTest {
	private static final String CALL_PROCEDURE = "{call squere_of_number(?,?)}";

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
				Scanner sc = new Scanner(System.in);) {
			int value1 = 0;
			if (sc != null) {
				System.out.println("Enter the value:: ");
				value1 = sc.nextInt();

			}
			if (cs != null) {
				// register jdbc out param with jdbc Type ( all out return param must be
				// registered)
				cs.registerOutParameter(2, Types.INTEGER);
				// set value in param
				cs.setInt(1, value1);
				// call pl/sql procedure
				cs.execute();
				// gather result from out param
				int result = cs.getInt(2);
				System.out.println("Two number squere is :: " + result);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
