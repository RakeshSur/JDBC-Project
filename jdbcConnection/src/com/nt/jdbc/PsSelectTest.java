//PsSelectTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsSelectTest {
	private static final String QUERY = "SELECT * FROM DEPT WHERE DEPTNO = ?";

	public static void main(String[] args) {
		try(//establish the connection
				Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				//create pre_paredStatement having pre comple sql query
				PreparedStatement ps = con.prepareStatement(QUERY);
						//execute the pre-compiled sqlQury
						ResultSet rs = ps.executeQuery();
				){
			//process the result
			if(sc!=null) {
				System.out.println("Enetr the deptno:: ");
				int dept= sc.nextInt();
				ps.setInt(1, dept);
			}
			if(rs!=null) {
				
				while (rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
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
