package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Capabilities_Database_Oracle {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott" ,"tiger");
				 
				){
			//create database Metadata obj
			DatabaseMetaData dbmd = null;
			if(con!=null) {
				dbmd =con.getMetaData();
			}
			if(dbmd!=null) {
				//get more info about database s/w
				System.out.println("data base s/w name "+dbmd.getDatabaseProductName());
				System.out.println("data base s/w version "+dbmd.getDatabaseProductVersion());
				System.out.println("Db version "+dbmd.getDatabaseMajorVersion()+" "+dbmd.getDatabaseMinorVersion());
				System.out.println("JDBC driver name "+dbmd.getDriverName()+" JDBC version "+dbmd.getJDBCMajorVersion()+" "+dbmd.getJDBCMinorVersion());
				System.out.println("Max connection count "+dbmd.getMaxConnections());
				System.out.println("Max db table name length "+dbmd.getMaxTableNameLength());
				System.out.println("Max user name length "+dbmd.getMaxUserNameLength());
				System.out.println("Max column count in db table "+dbmd.getMaxColumnsInTable());
				System.out.println("Max row size "+dbmd.getMaxRowSize());
				System.out.println("Support stored procedure "+dbmd.supportsStoredProcedures());
				System.out.println("All numeric function "+dbmd.getNumericFunctions());
				System.out.println("All system function "+dbmd.getSystemFunctions());
				System.out.println("All SQL key words "+dbmd.getSQLKeywords());
				
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
