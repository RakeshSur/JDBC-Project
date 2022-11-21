package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectExelTest {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:Excel:///E:\\java documents\\collage.xlsx");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM Sheet1");
				){
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getString(4));
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
