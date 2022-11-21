package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Postgres_Scorlabel_Test {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ntaj414db1", "postgres",
				"tiger");
				PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENTS", ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();

		) {
			if(rs!=null) {
				System.out.println("Record displaly top to bottom:::");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				}
				rs.afterLast();
				while(rs.previous()) {
			  System.out.println("Record  display bottom to top:: ");
			  System.out.println(rs.getRow()+"------------>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
			}
				rs.first();
				System.out.println(rs.getRow()+"------------>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				
				rs.absolute(3);
				System.out.println(rs.getRow()+"------------>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				rs.relative(1);
				System.out.println(rs.getRow()+"------------>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				//insert
				rs.moveToInsertRow();//create empty record
				rs.updateInt(1,108);
				rs.updateString(2, "Bubun");
				rs.updateFloat(3, (float) 88.4);
				rs.updateString(4, "Khurda");
				rs.insertRow();
				//update
				rs.absolute(1);
				rs.updateString(2,"Sajal");
				rs.updateRow();
				
				
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
