//DateTimeInsertTest_Oracle.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsertTest_Oracle {
	private static final String INSERT_CUSTOMER_QUERY = "INSERT INTO CUSTOMER_INFO VALUES(s1.nextval,?,?,?,?,?)";

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott", "tiger");
				PreparedStatement ps = con.prepareStatement(INSERT_CUSTOMER_QUERY);
				Scanner sc = new Scanner(System.in);) {
			String name = null, dob = null, top = null, orderDateTime = null;
			float billAmt = 0.0f;
			if (sc != null && ps != null) {
				System.out.println("Enter the coustmer name:: ");
				name = sc.next();
				System.out.println("Enter the billAmmount:: ");
				billAmt = sc.nextFloat();
				System.out.println("Enter the DOB(dd-MM-yyyy):: ");
				dob = sc.next();
				System.out.println("Enter the TOP(hh:mm:ss) :: ");
				top = sc.next();
				System.out.println("Enter the orderDateTIme(dd-MM-YYYY hh:mm:ss) :: ");
				sc.nextLine();
				orderDateTime = sc.nextLine();

				// converting string DOB to java.sql.Date class obj

				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date uddob = sdf1.parse(dob);
				java.sql.Date sdob = new java.sql.Date(uddob.getTime());
				// converting top to java.sql.Time class obj
				java.sql.Time stop = java.sql.Time.valueOf(top);
				 //converting orderDateTime to java.sql.Timestamp class obj
				SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				java.util.Date uodt = sdf3.parse(orderDateTime);
				java.sql.Timestamp suodt = new java.sql.Timestamp(uodt.getTime());

				// set sql value to params
				if(ps!=null) {

				ps.setString(1, name);
				ps.setFloat(2, billAmt);
				ps.setDate(3, sdob);
				ps.setTime(4, stop);
				ps.setTimestamp(5, suodt);
				int count = ps.executeUpdate();
				if (count == 0) {
					System.out.println("Record not inserted::");

				} else {
					System.out.println("Record inserted:: ");
				}

			}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
