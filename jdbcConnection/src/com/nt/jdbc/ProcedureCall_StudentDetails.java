package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class ProcedureCall_StudentDetails {
	private static final String PROCEDURE_CALL = "{call get_student_details(?,?,?)}";

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				CallableStatement cs = con.prepareCall(PROCEDURE_CALL);
				){
			int no = 0;
			if(sc!=null) {
				System.out.println("Enter Student No:: ");
				no = sc.nextInt();
			}
			if(cs!=null) {
				cs.registerOutParameter(2, Types.INTEGER);
				cs.registerOutParameter(3, Types.FLOAT);
				//set param
				cs.setInt(1, no);
				//execute procedure
				cs.execute();
				int totalmark = cs.getInt(2);
				float avg = cs.getFloat(3);
				System.out.println("Total mark "+totalmark);
				System.out.println("avg mark "+avg);
			}
			
		}
		catch (SQLException se) {
			if(se.getErrorCode()==1403) {
				System.out.println("Invaild entry");
			}
			else if(se.getErrorCode()==1017) {
				System.out.println("Invaild credential");
			}
			else {
//				System.out.println("Some db problem");
				se.printStackTrace();
			}
			//se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
