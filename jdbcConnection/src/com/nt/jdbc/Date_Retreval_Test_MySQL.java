package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Date_Retreval_Test_MySQL {
	private static final String RETRIVE_DATE_QUERY = "SELECT * FROM CUSTOMER_INFO";

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps = con.prepareStatement(RETRIVE_DATE_QUERY);
				ResultSet rs = ps.executeQuery();) {
			if (rs != null) {
				while (rs.next()) {
				int cno = rs.getInt(1);
				String name = rs.getString(2);
				float billamt = rs.getFloat(3);
				java.sql.Date dob = rs.getDate(4);
				java.sql.Time top = rs.getTime(5);
				java.sql.Timestamp order_date_time = rs.getTimestamp(6);

				// convert java.sql.time obj and date to String date
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				String sdob = sdf1.format(dob);

				long ms = top.getTime();
				java.util.Date udate = new java.util.Date(ms);
				SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
				String stop = sdf2.format(udate);

				long ms2 = order_date_time.getTime();
				java.util.Date udate2 = new java.util.Date(ms2);
				SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				String sdate_order = sdf3.format(udate2);

				
					System.out.println("cno is:: " + cno + " " + "name " + name + " " + "billamount " + billamt + " "
							+ "date of birth:: " + sdob + " " + "time of purchage " + stop + " "
							+ "Date of order and time " + sdate_order);

				}

			}

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
