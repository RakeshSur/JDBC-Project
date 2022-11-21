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
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class LOBRetriveTest_Oracle {
	private static final String RETRIVE_LOB_QUERY = "SELECT * FROM ACTORS_INFO WHERE AID=?";

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL" ,"scott", "tiger");
				PreparedStatement ps = con.prepareStatement(RETRIVE_LOB_QUERY);){
			int aid = 0;
			if(sc!=null) {
				System.out.println("Enter the id of actor-----------------------:: ");
				aid = sc.nextInt();
				if(ps!=null) 
					ps.setInt(1, aid);
				//excute the query
				try(ResultSet rs = ps.executeQuery();
						){
					if(rs!=null) {
						if(rs.next()) {
							int id = rs.getInt(1);
							String name = rs.getString(2);
							try(InputStream is =rs.getBinaryStream(3);
									Reader reader = rs.getCharacterStream(4);
									//create empty destination file using strem
									OutputStream os = new FileOutputStream("photo.jpeg");
									Writer writer = new FileWriter("retrive_photo.jpeg");
									
									){
								IOUtils.copy(is, os);
								IOUtils.copy(reader, writer);
								System.out.println("Actor info "+id+" "+name+" "+"LOBs are retrived:: ");
								
							}
						}
							else {
								System.out.println("Actors not found:: ");
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
