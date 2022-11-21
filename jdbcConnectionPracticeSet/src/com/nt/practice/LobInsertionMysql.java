package com.nt.practice;

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

public class LobInsertionMysql {
	private static final String INSERT_QUERY="INSERT INTO ACTRESS_INFO(ANAME,ADOB,ADOT,ASTACTING,APHOTO,ADETAILS)VALUES(?,?,?,?,?,?)";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ414DB1", "root", "root");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
				Scanner sc = new Scanner(System.in);
				
				){
			String name = null,dob=null,dot=null,stacting=null,photopath=null,filepath=null;
			if(sc!=null) {
				System.out.println("Enter Actress name ");
				name = sc.next();
				System.out.println("Enter Actress date of birth As in format of (dd-MM-yyyy) ");
				dob = sc.next();
				System.out.println("Enter the Actress date of Timming as in format(hh:mm:ss) ");
				dot = sc.next();
				System.out.println("Enter her date and time of Starting acting as format (dd-MM-yyyy hh:mm:ss) ");
				sc.nextLine();
				stacting = sc.nextLine();
				System.out.println("Enter her image as path ");
				photopath = sc.next().trim().replace("?", "");
				System.out.println("Enter her biodata as path ");
				sc.nextLine();
				filepath = sc.next().trim().replace("?", "");
			}
			
			
				//set date String to java.sql.date format
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udate = sdf1.parse(dob);
				java.sql.Date sdate= new java.sql.Date(udate.getTime());
				//set time String to java.sql.time format
				java.sql.Time stime = java.sql.Time.valueOf(dot);
				//set date and time String to java.sql.TimeStamp format
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				java.util.Date udate1 = sdf2.parse(stacting);
				java.sql.Timestamp sdate1= new java.sql.Timestamp(udate1.getTime());
				try(InputStream is = new FileInputStream(photopath);
						Reader reader = new FileReader(filepath);
						){
					if(ps!=null) {
						ps.setString(1, name);
						ps.setDate(2, sdate);
						ps.setTime(3, stime);
						ps.setTimestamp(4, sdate1);
						ps.setBinaryStream(5, is);
						ps.setCharacterStream(6, reader);
						//excute the query
						int count = ps.executeUpdate();
						if(count==0)
							System.out.println("Record not inserted");
						else
							System.out.println("Record inserted :::");
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
