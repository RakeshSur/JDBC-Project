package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessing_Test_MySql {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ414DB1", "root", "root");
				Statement st = con.createStatement();
				){
			if(st!=null) {
				st.addBatch("INSERT INTO PRODUCT VALUES(400,'BED',10000,1)");
				st.addBatch("UPDATE PRODUCT SET PRICE=800 WHERE PNAME='BOOK'");
				st.addBatch("DELETE FROM PRODUCT WHERE PID=454");
			}
			int result[] = st.executeBatch();
			int sum=0;
			for(int i=0; i<result.length; i++) 
				sum = sum+result[i];
			System.out.println("No of record affect is "+sum);
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
