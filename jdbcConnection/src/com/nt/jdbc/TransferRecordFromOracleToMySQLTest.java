//TransferRecordFromOracleToMySQLTest.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferRecordFromOracleToMySQLTest {
	private static final String MY_SQL_INSERT_QUERY = "INSERT INTO PRODUCT VALUES(?,?,?,?)";
	private static final String ORA_SELECT_QUERY = "SELECT * FROM PRODUCT";

	public static void main(String[] args) {
		try(Connection oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				Connection mySQLCon = DriverManager.getConnection("jdbc:mysql:///NTAJ414DB1", "root","root");
				Statement st = oraCon.createStatement();
				PreparedStatement ps = mySQLCon.prepareStatement(MY_SQL_INSERT_QUERY);
				){
			//execute the sql query in oracle d/b
			try(ResultSet rs = st.executeQuery(ORA_SELECT_QUERY);
					){
				//process the resultset and also insert the record into MySQL database
				int count = 0;
				if(rs!=null && ps!=null) {
					while(rs.next()) {
						//get each record from oracle d/b
						int id=rs.getInt(1);
						String name =rs.getString(2);
						int price =rs.getInt(3);
						int quantity =rs.getInt(4);
						//set the above values INSERT query param value to insert record in MySqldatabase
						ps.setInt(1, id);  ps.setString(2, name); ps.setInt(3, price); ps.setInt(4, quantity);
						//execute query
						int result = ps.executeUpdate();
						//process the result
						if(result ==0)
							System.out.println("Record not inserted::");
						else
							System.out.println("record inserted::");
						count++;
						
					}
					System.out.println(count +"  no of record inserted::");
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
