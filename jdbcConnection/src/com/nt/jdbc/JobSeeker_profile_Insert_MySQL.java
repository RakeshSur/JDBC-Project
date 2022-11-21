//JobSeeker_profile_Insert_MySQL.java
package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class JobSeeker_profile_Insert_MySQL {

	private static final String JOB_SEEKER_LOB_INSERT_QUERY = "INSERT INTO JOB_SEEKER (NAME,ADDRESS,QUALIFICATION,PHOTO,RESUME,DOB) VALUES(?,?,?,?,?,?)";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps = con.prepareStatement(JOB_SEEKER_LOB_INSERT_QUERY);) {
			String name = null, address = null, qulification = null, photopath = null, resumepath = null, dob = null;
			if (sc != null) {
				System.out.println("Enter the jobseeker Deatails-----------------------:: ");
				System.out.println("Enter the name------------------:: ");
				name = sc.next();
				System.out.println("Enter the address---------------:: ");
				address = sc.next();
				System.out.println("Eneter the qulification---------:: ");
				qulification = sc.next();
				System.out.println("Enter the jobseeker photgraph-----------------:: ");
				photopath = sc.next().trim().replace("?", "");
				System.out.println("Enter the job seeker resume--------------------:: ");
				resumepath = sc.next().trim().replace("?", "");
				System.out
						.println("Enter the job seeker date of birth (dd:MM:yyyy) fromat-------------------------:: ");
				sc.nextLine();
			

				dob = sc.nextLine();

				try (InputStream is = new FileInputStream(photopath); Reader reader = new FileReader(resumepath);) {
					// convert date String to java.sql.date obj
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udate = sdf.parse(dob);
					java.sql.Date sdate = new java.sql.Date(udate.getTime());

					if (ps != null) {
						ps.setString(1, name);
						ps.setString(2, address);
						ps.setString(3, qulification);
						ps.setBinaryStream(4, is);
						ps.setCharacterStream(5, reader);
						ps.setDate(6, sdate);
						
						int count = ps.executeUpdate();
						if(count ==0)
							System.out.println("Record not inserted:: ");
						else
							System.out.println("Record inserted::::::");

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
