package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PS_UpdateabelRsTest_Omm {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareCall("SELECT EMPNO,ENAME,JOB,SAL FROM OMM" ,
						ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();
				){
			if(rs!=null) {
				System.out.println("Record display top to bottom");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				}
				System.out.println("=========================================");
				//rs.afterLast();
				while(rs.previous()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				}
				//update
				/*rs.moveToInsertRow();
				rs.updateInt(1, 1509);
				rs.updateString(2, "Ramesh");
				rs.updateString(3, "Eng");
				rs.updateInt(4, 30000);
				rs.insertRow();
				System.out.println("Record inserted");
				*/
				//delete
				rs.absolute(15);
				rs.deleteRow();
				System.out.println("record deleted");
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
