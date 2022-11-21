package com.nt.practice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsInsertTest {
	private static final String INSERT_QEURY = "INSERT INTO EMAMI VALUES(E1.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				PreparedStatement ps = con.prepareStatement(INSERT_QEURY);
				Scanner sc = new Scanner(System.in);
				){
			String name = null ,dateOfJoin = null, dob = null ;
			if(sc!=null && ps!=null) {
				System.out.println("Enter the name::");
				name = sc.next();
			    System.out.println("Enter the date and time of joining (dd-MM-yyyy hh:mm:ss)");
				sc.nextLine();
			    dateOfJoin = sc.nextLine();
			    System.out.println("Enter the date of birth(dd-MM-yyy)");
			    dob = sc.next();
				
		
			//convert date String to java.sql.TimeStamp obj
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			java.util.Date udate = sdf.parse(dateOfJoin);
			java.sql.Timestamp sdate = new java.sql.Timestamp(udate.getTime());
			//converting String dob to java.date obj
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udate2 = sdf2.parse(dob);
			java.sql.Date sdate2 = new java.sql.Date(udate2.getTime());
			if(ps!=null) {
				ps.setString(1, name);
				ps.setTimestamp(2, sdate);
				ps.setDate(3, sdate2);
				//excute query
				int count = ps.executeUpdate();
				if(count==0)
					System.out.println("Record not inserted::");
				else
					System.out.println("Record inseted:::");
			}
				
			}
			
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
