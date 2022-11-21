package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CSVUpdateTest {
	private static final String UPDATE_QUERY = "UPDATE FILE SET ADRESS='JALESWAR' WHERE ID=?";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:Text:///E:\\java documents\\file.csv");
				PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
				Scanner sc = new Scanner(System.in);
				){
			int no =0; 
			if(sc!=null) {
				System.out.println("Enter the student id:::");
				no = sc.nextInt();
				
				
				//set value to query param
				if(ps!=null) {
					ps.setInt(1, no);
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
