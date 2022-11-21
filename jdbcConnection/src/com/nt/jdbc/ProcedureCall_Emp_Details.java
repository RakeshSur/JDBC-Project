package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class ProcedureCall_Emp_Details {
	private static final String CALL_PROCEDURE_QUERY = "{call emp_details(?,?,?,?)}";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE_QUERY);) {
			int value1 = 0;
			if (sc != null) {
				System.out.println("Enter the Employee number:: ");
				value1 = sc.nextInt();
			}
			if (sc != null) {
				// register jdbc out param with jdbc Type ( all out return param must be
				// registered)
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.FLOAT);
				cs.registerOutParameter(4, Types.INTEGER);
				// set value in param
				cs.setInt(1, value1);
				// execute procedure
				cs.execute();

				String name = cs.getString(2);
				float sal = cs.getFloat(3);
				int dept = cs.getInt(4);
				System.out.println("Ename " + name);
				System.out.println("Salaray is " + sal);
				System.out.println("Dept no is " + dept);

			}

		} catch (SQLException se) {
			if(se.getErrorCode()==1403) {
				System.out.println("Invaild entry");
			}
			else if(se.getErrorCode()==1017) {
				System.out.println("Invaild credential");
			}
			else {
				System.out.println("Some db problem");
			}
			//se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
