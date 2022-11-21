package com.nt.practice;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PSRetrive_Test {
	private static final String SELECT_QUERY = "SELECT * FROM EMAMI";
	

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott","tiger");
				PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
				ResultSet rs = ps.executeQuery();
				){
			if(rs!=null) {
				while(rs.next()) {
					int eno = rs.getInt(1);
					String name = rs.getString(2);
					java.sql.Timestamp djoin = rs.getTimestamp(3);
					java.sql.Date bob = rs.getDate(4);
					//converting java.sql.TimeStamp to String date format
					long ms = djoin.getTime();
					java.sql.Date sud = new java.sql.Date(ms);
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");
					String joinDate = sdf.format(sud);
					//convertion java.sql.date to string date format
					long ms1 = bob.getTime();
					java.sql.Date sud1 = new java.sql.Date(ms1);
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyy");
					String sdob = sdf1.format(sud1);
					System.out.println("Eno "+eno+" Ename "+name+" D and T of joing "+joinDate+" Date of birth "+sdob);
					
					
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
