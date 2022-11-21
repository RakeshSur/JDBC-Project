package com.nt.jdbc;

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

public class Retrive_Job_Seeker_Info {
	public static final String RETRIVE_QUERY = "SELECT * FROM JOB_SEEKER WHERE JID=?";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				PreparedStatement ps = con.prepareStatement(RETRIVE_QUERY);) {
			int id = 0;
			if (sc != null & ps!=null) {
				System.out.println("Enter the job id number: ");
				id = sc.nextInt();
				
				ps.setInt(1, id);

			}
			try(ResultSet rs = ps.executeQuery();
					){
				if(rs!=null) {
					if(rs.next()) {
						int jid = rs.getInt(1);
						String name = rs.getString(2);
						String address = rs.getString(3);
						String quli = rs.getString(4);
						try(InputStream is = rs.getBinaryStream(5);
							   Reader reader= rs.getCharacterStream(6);
								OutputStream os = new FileOutputStream("photo.jpg");
								Writer writer = new FileWriter("abc.txt");
								){
							IOUtils.copy(is, os);
							IOUtils.copy(reader, writer);
							java.sql.Date sqdate = rs.getDate(7);
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							String dob = sdf.format(sqdate);
							
							System.out.println("job id "+jid+" "+"name "+name+" "+"address "+address+" "+"qulification "+quli+" "+"dob is "+dob+" LOB are retrive");
							
						}
						
					}
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
