package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSet_Demo {

	public static void main(String[] args) {
		try (CachedRowSet crrowSet = new OracleCachedRowSet();) {
			// set JDBC properties
			crrowSet.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			crrowSet.setUsername("scott");
			crrowSet.setPassword("tiger");
			// set query
			crrowSet.setCommand("SELECT * FROM STUDENT");
			// execute query
			crrowSet.execute();
			// process the result
			while (crrowSet.next()) {
				System.out.println(crrowSet.getInt(1) + " " + crrowSet.getString(2) + " " + crrowSet.getFloat(3) + " "
						+ crrowSet.getString(4));
			} // while
			System.out.println("Stop the DB s/w during the sleep pereiod of the application: ");
			Thread.sleep(60000);
			crrowSet.absolute(2);
			crrowSet.updateString(4, "purulia");
			crrowSet.updateRow();
            System.out.println("Start the DB s/w after the sleep pereiod of the application: ");
			Thread.sleep(60000);
			crrowSet.acceptChanges();
			// process the results
			while (crrowSet.next()) {
				System.out.println(crrowSet.getInt(1) + " " + crrowSet.getString(2) + " " + crrowSet.getFloat(3) + " "
						+ crrowSet.getString(4));
			} // while

		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
