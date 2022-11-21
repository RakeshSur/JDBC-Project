package com.nt.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;



public class ConnpolTest {
	private static final String GET_STUDENT_DETAILS="SELECT * FROM STUDENT";

	public static void main(String[] args) {
		OracleConnectionPoolDataSource ds = null;
		try {
			//create DataSource Object
			 ds =new OracleConnectionPoolDataSource();
			 //set jdbc properties to it
			 ds.setDriverType("thin");
			 ds.setURL("jdbc:oracle:thin:@localhost:1521:ORCL");
			 ds.setUser("scott");
			 ds.setPassword("tiger");
			 
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//get pooled jdbc connection obj
		try(Connection con= ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_STUDENT_DETAILS);
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
