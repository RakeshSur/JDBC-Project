package com.nt.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class RetriveDateAndTime_MySql {
	private static final String RETRIVE_QUERY = "SELECT * FROM CUSTOMER_INFO";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps = con.prepareStatement(RETRIVE_QUERY);
				ResultSet rs = ps.executeQuery();
				){
			if(rs!=null) {
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					float billamt = rs.getFloat(3);
					java.sql.Date dob = rs.getDate(4);
					java.sql.Time top = rs.getTime(5);
					java.sql.Timestamp orderDateTime = rs.getTimestamp(6);
					
					//converting java.sql.Date and time to String
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					String dateofBirth = sdf1.format(dob);
					
					long ms = top.getTime();
					java.util.Date utime = new java.util.Date(ms);
					SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
					String time = sdf2.format(utime);
					
					long ms1 = orderDateTime.getTime();
					java.util.Date utime1 = new java.util.Date(ms1);
					SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					String dateTime = sdf3.format(utime1);
					
					System.out.println("id "+id+" name "+name+" billamt "+billamt+" dateofBirth "+dateofBirth+" Time of purchage "+time+" order date and time "+dateTime);
					
					
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
