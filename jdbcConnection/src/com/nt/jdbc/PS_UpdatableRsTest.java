package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PS_UpdatableRsTest {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareStatement("SELECT  SNO,NAME,AVG,ADDRS FROM STUDENT",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();
				 ){
			if(rs!=null) {
				System.out.println("Select Operation:: ");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				}
				//Insert operation
				rs.moveToInsertRow();
				rs.updateInt(1, 6);
				rs.updateString(2, "Jigisha");
				rs.updateFloat(3, (float) 77.88);
				rs.updateString(4, "Gujrat");
				rs.insertRow();
				System.out.println("Record inserted");
				
				
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
