package com.nt.practice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class Callable_OracleTest {
	private static final String FUNCTION_QUERY = "{call p1(?,?) }";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				CallableStatement cs = con.prepareCall(FUNCTION_QUERY);
				Scanner sc = new Scanner(System.in);
				){
			int no= 0;
			if(sc!=null){
				System.out.println("Enter the number :::");
				no = sc.nextInt();
			}
			if(cs!=null) {
				
				cs.registerOutParameter(2,OracleTypes.INTEGER);
				cs.setInt(1, no);
				cs.execute();
				System.out.println("no is "+cs.getInt(2));
			
			}
			
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
