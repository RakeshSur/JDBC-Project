package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ps_Scrolable_StudentTest {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENT",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY
						);
				ResultSet rs = ps.executeQuery();){
			if(rs!=null) {
				System.out.println("Record display top to bottom::");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				}
				rs.afterLast();
				System.out.println("Record display bottom  to top  ::");
				while(rs.previous()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				}
				System.out.println("=============================================");
				System.out.println("Record display randomly ");
				rs.first();
				System.out.println(rs.getRow()+"--------------------->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				rs.last();
				System.out.println(rs.getRow()+"--------------------->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				rs.absolute(3);
				System.out.println(rs.getRow()+"--------------------->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				rs.absolute(-4);
				System.out.println(rs.getRow()+"--------------------->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				rs.relative(2);
				System.out.println(rs.getRow()+"--------------------->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
				rs.relative(-1);
				System.out.println(rs.getRow()+"--------------------->"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
//				rs.absolute(3);
//				rs.updateString(2, "BUBUN");
//				rs.updateRow();
				
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
