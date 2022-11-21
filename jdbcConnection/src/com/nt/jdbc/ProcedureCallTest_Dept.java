package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class ProcedureCallTest_Dept {
	/*
	 * create or replace procedure get_dept_details(p_deptno in number,details out
	 * sys_refcursor) 2 is 3 begin 4 open details for 5 select dname,loc from dept
	 * where deptno=p_deptno; 6* end;
	 */
	private static final String GET_DETAILS = "{call get_dept_details(?,?)}";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				CallableStatement cs = con.prepareCall(GET_DETAILS);) {

			int no = 0;
			if (sc != null) {
				System.out.println("Enter the deptno:: ");
				no = sc.nextInt();
			}
			if (cs != null) {
				cs.registerOutParameter(2, Types.REF_CURSOR);
				cs.setInt(1, no);
				cs.execute();
			}
			try (ResultSet rs = (ResultSet) cs.getObject(2);) {

				if (rs != null) {
					boolean isRsEmpty = false;
					while (rs.next()) {
						System.out.println("DName is "+rs.getString(1) + " " +"Location is "+ rs.getString(2));
						isRsEmpty = true;
					}
					if (!isRsEmpty)
						System.out.println("Record not found:::");
					else
						System.out.println("Record found display::");

				}
			}
		}

		catch (SQLException se) {
			if (se.getErrorCode() == 1403) {
				System.out.println("Invaild entry");
			} else if (se.getErrorCode() == 1017) {
				System.out.println("Invaild credential");
			} else {
//				System.out.println("Some db problem");
				se.printStackTrace();
			}
			// se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
