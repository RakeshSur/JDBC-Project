package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Age_Calculator_query_MySQL {
	private static final String AGE_CALCULATOR_QUERY = "SELECT (DATEDIFF(now(),DOB))/365.25 FROM NTAJ414DB1.CUSTOMER_INFO WHERE CNO=?";

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1","root","root");
				PreparedStatement ps = con.prepareStatement(AGE_CALCULATOR_QUERY);
				
				){
			int no=0;
			if(sc!=null && ps!=null) {
				System.out.println("Enter the customer no:::  ");
				no = sc.nextInt();
				ps.setInt(1, no);
				
			}
			try(ResultSet rs = ps.executeQuery();
					){
				if(rs!=null) {
					if(rs.next()) 
						System.out.println("Age is :: "+rs.getFloat(1));
					else
						System.out.println("Customer not found::: ");
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
