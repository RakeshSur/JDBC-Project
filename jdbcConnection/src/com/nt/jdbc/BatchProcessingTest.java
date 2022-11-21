package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTest {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				Statement st = con.createStatement();
				){
			if(st!=null) {
				//add queries to the batch(Only non select query )
				st.addBatch("INSERT INTO STUDENT VALUES(10,'SANJAY',88.23,'SIMILIPAL')");
				st.addBatch("UPDATE STUDENT SET AVG = AVG-10 WHERE SNO>=3 AND SNO<=6");
				st.addBatch("DELETE FROM STUDENT WHERE ADDRS='KANPUR'");
		
			//send and execute batch of sql query in db s/w
			int result[] = st.executeBatch();
			//process the result
			int sum = 0;
			for(int i=0; i<result.length; i++) 
				sum = sum+result[i];
			

			System.out.println("No of record affect is "+sum);
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
