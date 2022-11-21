package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowset_Demo {

	public static void main(String[] args) {
		try(JdbcRowSet jrowSet = new OracleJDBCRowSet();
				
				){
			//set JDBC properties
			jrowSet.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			jrowSet.setUsername("scott");
			jrowSet.setPassword("tiger");
			//set query
			jrowSet.setCommand("SELECT * FROM STUDENT");
			//execute query
			jrowSet.execute();
			//process the resultSet
			while(jrowSet.next()) {
				System.out.println(jrowSet.getInt(1)+" "+jrowSet.getString(2)+" "+jrowSet.getFloat(3)+" "+jrowSet.getString(4));
			}//while
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class
