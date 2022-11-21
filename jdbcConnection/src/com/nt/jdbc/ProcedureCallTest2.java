package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.OracleTypes;

public class ProcedureCallTest2 {
	/*
	CREATE OR REPLACE PROCEDURE GETEMP_DETAILS 
(
  ECHAR IN VARCHAR2 
, DETAIlS OUT SYS_REFCURSOR 
) AS 
BEGIN
open details for
 select ename,empno,sal,job from emp where ename like echar;
END GETEMP_DETAILS;
	 */
	private static final String CALL_PROCEDURE = "{call getemp_details(?,?)}";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);) {
			String initchar = null;
			if (sc != null) {
				System.out.println("Enter the initial character:: ");
				initchar = sc.next();
			}
			if (cs != null) {
				cs.registerOutParameter(2, OracleTypes.CURSOR);

				cs.setString(1, initchar+"%");
				cs.execute();
			}
			try (ResultSet rs = (ResultSet) cs.getObject(2);) {
				if (rs != null) {
					boolean isRsEmpty = false;
					while (rs.next()) {

						System.out.println(rs.getString(1) + " " + rs.getInt(2) +" "+ rs.getInt(3)+" "+rs.getString(4));
						isRsEmpty = true;
					}
					if (!isRsEmpty)
						System.out.println("Records are not found");
					else
						System.out.println("Record found and diplayed");

				}
			}

		} catch (SQLException se) {
			if (se.getErrorCode() == 1403) {
				System.out.println("Invaild entry");
			} else if (se.getErrorCode() == 1017) {
				System.out.println("Invaild credential");
			} else {
				System.out.println("Some db problem");
				se.printStackTrace();
			}
			// se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
