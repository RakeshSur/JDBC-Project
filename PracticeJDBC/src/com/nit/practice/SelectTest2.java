package com.nit.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest2 {
	private static String SELECT_QUERY = "SELECT ENAME,SAL,MGR FROM EMP WHERE JOB IN('CLERK','SALESMAN','ANALYST') ORDER BY JOB";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(SELECT_QUERY);
				){
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getRow()+""+ rs.getString(1)+" "+ rs.getFloat(2)+" "+rs.getInt(3));
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
