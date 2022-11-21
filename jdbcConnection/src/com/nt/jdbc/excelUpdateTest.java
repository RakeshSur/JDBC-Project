package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class excelUpdateTest {
	private static final String UPDATE_QUERY = "UPDATE SHEET1 SET SADDRS='JALESWAR' WHERE SNO=?";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:Excel:///E:\\java documents\\collage.xlsx");
				PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
				Scanner sc = new Scanner(System.in);
				){
			int id =0; 
			if(sc!=null) {
				System.out.println("Enter the student id:::");
				id = sc.nextInt();
				
				
				//set value to query param
				if(ps!=null) {
					ps.setInt(1, id);
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
