package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferSTableOracleTo_Excel {
	private static final String RETRIVE_QUERY_ORACLE = "SELECT * FROM  STUDENT";
	private static final String INSERT_QUERY_EXCEL = "INSERT INTO SHEET1 VALUES(?,?,?,?)";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				Connection con1 = DriverManager.getConnection("jdbc:Excel:///E:\\java documents\\collage.xlsx");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(RETRIVE_QUERY_ORACLE);
				PreparedStatement ps = con1.prepareStatement(INSERT_QUERY_EXCEL);
				
				
				){
			int count = 0;
			if(rs!=null) {
				while(rs.next()) {
					//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getString(4));
					int id = rs.getInt(1);
					String name = rs.getString(2);
					float avgg = rs.getFloat(3);
					String addrs = rs.getString(4);
					if(ps!=null) {
						ps.setInt(1,id);
						ps.setString(2, name);
						ps.setFloat(3, avgg);
						ps.setString(4, addrs);
						int result = ps.executeUpdate();
						if(result==0)
							System.out.println("Record not inserted:::");
						else
							System.out.println("Record inserted:::");
						count++;
					}
					System.out.println("Record inserted "+count);
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
