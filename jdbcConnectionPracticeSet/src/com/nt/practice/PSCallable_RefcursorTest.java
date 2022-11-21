package com.nt.practice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;


public class PSCallable_RefcursorTest {
	private static final String CURSOR_QUERY = "{call get_emp_salDetails(?) }";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				CallableStatement cs = con.prepareCall(CURSOR_QUERY);
				
				){
			if(cs!=null) {
				cs.registerOutParameter(1, OracleTypes.CURSOR);
				cs.execute();
				try(ResultSet rs = (ResultSet) cs.getObject(1);
						){
					if(rs!=null) {
						boolean isRsEmpty= false;
						while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2));
							isRsEmpty=true;
						}
						if(!isRsEmpty)
					System.out.println("Record not found");
						else
							System.out.println("Record found and diplay:::");
					
				}
				
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
