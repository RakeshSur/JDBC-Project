package com.nit.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest1 {
	private static  String SELECT = "SELECT * FROM EMP WHERE SAL>=2000 AND SAL<=4000";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SELECT);
				){
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getRow()+""+ rs.getString(1)+" "+ rs.getString(2)+" "+rs.getString(3)
					+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8));
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
