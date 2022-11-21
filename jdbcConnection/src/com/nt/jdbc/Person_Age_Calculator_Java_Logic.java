package com.nt.jdbc;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Person_Age_Calculator_Java_Logic {
	private static final String GET_DOB = "SELECT DOB FROM CUSTOMER_INFO WHERE CNO=?";

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareStatement(GET_DOB);
				){
			int no = 0;
			if(sc!=null) {
				System.out.println("Enter the customer no:: ");
				no=sc.nextInt();
			}
			if(ps!=null) {
				ps.setInt(1, no);
			}
			try(ResultSet rs= ps.executeQuery();
					){
				if(rs!=null) {
					if(rs.next()) {
						java.util.Date udob = rs.getDate(1);
						java.util.Date sysDate = new java.util.Date();
						long ageInms = sysDate.getTime()-udob.getTime();
						System.out.println("Age is::  "+(ageInms/(1000.0f*60.0f*60.0f*24.0f*365.25f)));
						
					}
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
