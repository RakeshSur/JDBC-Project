package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//import java.util.Scanner;

public class DateRetrival_Test_Oracl {
	private static final String GET_CUSTOMER_QUERY = "SELECT * FROM CUSTOMER_INFO";
	//private static final String GET_TODAYS_DATE_QUERY = "SELECT (SYSDATE-DOB)/365.25 FROM CUSTOMER_INFO WHERE CNO =?";

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareStatement(GET_CUSTOMER_QUERY);
				//PreparedStatement ps1 = con.prepareStatement(GET_TODAYS_DATE_QUERY);
				ResultSet rs = ps.executeQuery();
				//ResultSet rs1 = ps1.executeQuery();
				) {
			if (rs != null) {
				while (rs.next()) {
					int cno = rs.getInt(1);
					String name = rs.getString(2);
					float billamt = rs.getFloat(3);
					java.sql.Date sqdob = rs.getDate(4);
					java.sql.Time sqtop = rs.getTime(5);
					java.sql.Timestamp sqtorder_date_time = rs.getTimestamp(6);

					// convert java.sql.date class obj into String date value required format
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					String sdob = sdf1.format(sqdob);
					// convert java.sql.Time class obj to String time value in required format
					long ms = sqtop.getTime();
					java.util.Date utop = new java.util.Date(ms);// convert milisecond into java.util.date class obj
					SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
					String stop = sdf2.format(utop);// convert java.util.date into String value

					// convert java.sql.TimeStamp class obj to String date and time value in
					// required format

					long ms2 = sqtorder_date_time.getTime();
					java.util.Date jud = new java.util.Date(ms2);
					SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					String sqdtorder_date_time = sdf3.format(jud);
					System.out.println(
							"cno:: " + cno + " " + "name:: " + name + " " + "billamoutn:: " + billamt + " " + "sdob:: "
									+ sdob + " " + "stop:: " + stop + " " + "oeder_date_time:: " + sqdtorder_date_time);

				}
//				try (Scanner sc = new Scanner(System.in)) {
//					if (sc != null && ps1 != null) {
//						System.out.println("Enter the customer cno:: ");
//						int cno = sc.nextInt();
//						ps1.setInt(1, cno);
//
//						if (rs1 != null) {
//							if (rs1.next()) {
//								System.out.println("Date of birth is    "+rs1.getFloat(1));
//							}
//						}
//					}
//
//				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
