//need to check the for loop
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_Insert_Student_oracle {
	private static final String INSERT_QUERY = "Insert into student values(s1.nextval,?,?,?)";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
				Scanner sc = new Scanner(System.in);
				){
			String name =null; float avg = 0.0f; String addr=null; int count = 0;
			if(sc!=null&&ps!=null) {
				System.out.println("Enter how many time inserted record");
				count = sc.nextInt();
				for(int i=1; i<=count; i++) {
					 System.out.println("Enter the name:: ");
					 name = sc.next();
					 System.out.println("Enter the avg marks:: ");
					 avg = sc.nextFloat();
					 System.out.println("Enter the address:: ");
					 addr = sc.next();
					 
					 ps.setString(1, name);
					 ps.setFloat(2, avg);
					 ps.setString(3, addr);
				}

			}
			int result = ps.executeUpdate();
			if(result==0) {
				System.out.println("REcord not inserted::");
			}
			else {
				System.out.println("Record inserted");
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
