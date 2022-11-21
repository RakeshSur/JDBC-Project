//SelectTest_MySql.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest_MySql {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ414DB1","root","root");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM PRODUCT");
				){
			//process the reslutSet object
			if(rs!=null) {
				boolean rsEmpty = true;
				while(rs.next()) {
					rsEmpty = false;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
				}//while
				if(rsEmpty)
					System.out.println("No Record found::");
				else
					System.out.println("Record found And display:::::");
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
