package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertExcelTest {
	private static final String INSERT_QUERY = "INSERT INTO SHEET1 VALUES(?,?,?,?)";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:Excel:///E:\\java documents\\collage.xlsx");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
				Scanner sc = new Scanner(System.in);
				){
			int no =0; String name=null,addrs = null;
			float avg = 0.0f;
			if(sc!=null) {
				System.out.println("Enter the student id:::");
				no = sc.nextInt();
				System.out.println("Enter the student name::");
				name = sc.next();
				System.out.println("Enter the student avg::");
				avg= sc.nextFloat();
				System.out.println("Enter the student addrs::");
				addrs = sc.next();
				//set value to query param
				if(ps!=null) {
					ps.setInt(1, no);
					ps.setString(2,name);
					ps.setFloat(3, avg);
					ps.setString(4, addrs);
					//execute the query
					int count = ps.executeUpdate();
					if(count==0)
						System.out.println("NO record inseted:::::");
					else
						System.out.println("Record inserted::::::::::::");
					
				}
				
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
