//LoginAap.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginAap {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				Statement st = con.createStatement()
						){
			//read inputs
			String user = null,pass = null;
			if(sc!=null) {
				System.out.println("Enter user id:: ");
				user = sc.nextLine();
				System.out.println("Enter password:: ");
				pass = sc.nextLine();
				//converting input value as required for sql query
				user = "'"+user+"'";
				pass = "'"+pass+"'";
				//prepare sql query
				// SELECT COUNT(*) FROM USER_INFO WHERE UNAME ='RAJA' AND PWD='RANI';
				String query = "SELECT COUNT(*) FROM USER_INFO WHERE UNAME ="+user+"AND PWD="+pass;
				System.out.println(query);
				//send and execute sql query to the db s/w
				try(ResultSet rs = st.executeQuery(query)){
					if(rs!=null) {
						rs.next();
						int count = rs.getInt(1);
						if(count ==0)
							System.out.println("invalid credential");
						else
							System.out.println("valid credential");
					}
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
