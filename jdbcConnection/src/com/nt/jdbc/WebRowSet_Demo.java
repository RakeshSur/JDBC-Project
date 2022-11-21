package com.nt.jdbc;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSet_Demo {

	public static void main(String[] args) {
		try(WebRowSet wrowSet= new OracleWebRowSet();
				){
			//set JDBC properties
			wrowSet.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			wrowSet.setUsername("scott");
			wrowSet.setPassword("tiger");
			//set query
			wrowSet.setCommand("SELECT * FROM STUDENT");
			//execute query
			wrowSet.execute();
			// process the result
						while (wrowSet.next()) {
							System.out.println(wrowSet.getInt(1) + " " + wrowSet.getString(2) + " " + wrowSet.getFloat(3) + " "
									+ wrowSet.getString(4));
						} // while
						System.out.println("------------------------------------------------------");
						wrowSet.writeXml(System.out);
						System.out.println("------------------------------------------------------");
						Writer writer = new FileWriter("Student.xml");
						wrowSet.writeXml(writer);
					     writer.flush(); writer.close();
						
			
			
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
