package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostGresSqlSelect_Test {
	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ntaj414db1", "postgres", "tiger");
				PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENTS");
				ResultSet rs = ps.executeQuery();
				){
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
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
