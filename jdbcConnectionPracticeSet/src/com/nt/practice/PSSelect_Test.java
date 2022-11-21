package com.nt.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSSelect_Test {
	private static final String SELCECT_QUERY = "SELECT * FROM PRODUCT";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott","tiger");
				PreparedStatement ps = con.prepareStatement(SELCECT_QUERY);
				ResultSet rs = ps.executeQuery();
				){
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getRow()+" "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
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
