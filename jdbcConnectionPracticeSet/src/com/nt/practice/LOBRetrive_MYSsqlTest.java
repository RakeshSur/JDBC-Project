package com.nt.practice;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;


public class LOBRetrive_MYSsqlTest {
	private static final String SELECT_QUERY = "SELECT * FROM ACTRESS_INFO WHERE ANO=?";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps = con.prepareStatement(SELECT_QUERY);
				
				Scanner sc = new Scanner(System.in);
				){
			int no = 0;
			if(sc!=null) {
				System.out.println("Enter the no::");
				no = sc.nextInt();
			}
			if(ps!=null) {
				ps.setInt(1, no);
			}
			try(ResultSet rs = ps.executeQuery();
					){
			if(rs!=null) {
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					java.sql.Date sdate = rs.getDate(3);
					java.sql.Time stime = rs.getTime(4);
					java.sql.Timestamp sdobdot = rs.getTimestamp(5);
					try(InputStream is = rs.getBinaryStream(6);
							Reader reader = rs.getCharacterStream(7);
							OutputStream os = new FileOutputStream("photo.jpeg");
							Writer writer = new FileWriter("file.txt");){
						
				
					//convert java.sql.date to String date
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dob=sdf.format(sdate);
					//
					long ms=stime.getTime();
					java.util.Date utime = new java.util.Date(ms);
					SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
					String time = sdf2.format(utime);
					//
					long ms1=sdobdot.getTime();
					java.util.Date utimeandDate = new java.util.Date(ms1);
					SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					String dt = sdf3.format(utimeandDate);
					//
					IOUtils.copy(is, os);
					IOUtils.copy(reader,writer);
					System.out.println("Actor info "+id+" "+name+" "+dob+" "+time+" "+dt+" LOBs are retrived");
					}
				}
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
