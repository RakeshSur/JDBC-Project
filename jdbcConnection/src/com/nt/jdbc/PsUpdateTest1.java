//PsUpdateTest1.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsUpdateTest1 {
	private static final String UPDATE_QUERY = "UPDATE STUDENT SET AVG = AVG+10/100 WHERE ADDRESS NOT IN(?,?)";

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
				){
			int count = 0;
			if(sc!=null) {
				System.out.println("Enter the update count:: ");
				count = sc.nextInt();
					
				}
			if(sc!=null && ps!=null) {
				System.out.println("Enter the new update:: ");
				for(int i=1; i<=count; ++i) {
					System.out.println("Enter "+i+ " new updates");
					System.out.println("Eneter the adress1:: ");
					String address = sc.next();
				//	System.out.println("Eneter the adress2:: ");
				//	String address2 = sc.next();
					//System.out.println("Enter the avg:: ");
					//float avg = sc.nextFloat();
					
					ps.setString(1,address);
					ps.setString(2,address);
					//ps.setFloat(2, avg);
				
					int result = ps.executeUpdate();
					// process the result
					if (result == 0)
						System.out.println("  Avg record is not inserted::");
					else
						System.out.println(" Avg record is inserted");
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
