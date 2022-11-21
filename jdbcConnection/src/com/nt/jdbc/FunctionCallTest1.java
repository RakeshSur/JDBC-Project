package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class FunctionCallTest1 {
	
	private static final String FUNCTION_CALL = "{?= call f1(?,?)}";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				CallableStatement cs = con.prepareCall(FUNCTION_CALL);) {
			int studentno = 0;
			if (sc != null) {
				System.out.println("Enter the no:: ");
				studentno= sc.nextInt();
			}
			if (cs != null) {
				cs.registerOutParameter(1, Types.FLOAT);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.setInt(2, studentno);
				cs.execute();
				
				System.out.println("avg is  " + cs.getInt(1));
				System.out.println("Name is " + cs.getString(3));
			}

		} 
		catch (SQLException se) {
			if (se.getErrorCode() == 1403) {
				System.out.println("Invaild entry");
				se.printStackTrace();
			} else if (se.getErrorCode() == 1017) {
				System.out.println("Invaild credential");
			} else {
				System.out.println("Some db problem");
				//se.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
