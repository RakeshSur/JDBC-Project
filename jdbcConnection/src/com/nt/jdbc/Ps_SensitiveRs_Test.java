package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ps_SensitiveRs_Test {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareStatement("SELECT  SNO,NAME,AVG,ADDRS FROM STUDENT",
						                                                                               ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE
						);
				ResultSet rs = ps.executeQuery();){
			if(rs!=null) {
				System.out.println("Record Display top to bottom::::");
				int count =0;
				while(rs.next()) {
					if(count==0) {
						System.out.println("In next 20 sec modify the record of studnt in db table::");
						Thread.sleep(20000);
					}
					rs.refreshRow();
					count++;
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
