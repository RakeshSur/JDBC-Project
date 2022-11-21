package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PS_BatchProcessing_groupticketReservation {
	private static final String GROUP_TICKET_RESERVATION = "INSERT INTO JDBC_TRAIN_JOURNEY VALUES(TKD_ID_SQNCE.NEXTVAL,?,?,?,?,?,?)";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				PreparedStatement ps = con.prepareStatement(GROUP_TICKET_RESERVATION);
				
		) {
			int groupSize=0;
			String srcplc=null,destplc=null;
			boolean isItgroupReservertaion = false;
			int trainno = 0;
			String dtoj = null;
			java.sql.Timestamp stimedate = null;
			if(sc!=null) {
				System.out.println("Enter the group size::");
				groupSize = sc.nextInt();
				System.out.println("Enter the source place::");
				srcplc = sc.next();
				System.out.println("Enter the destination place::");
				destplc = sc.next();
				System.out.println("Enter the train no::  ");
				trainno = sc.nextInt();
				System.out.println("Enter the date and time of the journey (dd-MM-yyyy hh:mm:ss");
				sc.nextLine();
				dtoj = sc.nextLine();
				System.out.println("Is it group Reservation ");
				isItgroupReservertaion = sc.nextBoolean();
			}
			//convert String date and time of journey to java.sql.Timestamp 
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			java.util.Date udate = sdf.parse(dtoj);
			stimedate = new java.sql.Timestamp(udate.getTime());
			//read group of passenger info and add them to batch of query
			if(ps!=null && sc!=null) {
				for(int i=1; i<=groupSize; i++) {
					System.out.println("Enter the "+i+" passenger name");
					String name = sc.next();
					//add each set of query param values(passenger ,journey details ) to batch processing
					ps.setString(1, name);
					ps.setString(2, srcplc);
					ps.setString(3, destplc);
					ps.setBoolean(4, isItgroupReservertaion);
					ps.setInt(5, trainno);
					ps.setTimestamp(6, stimedate);
					ps.addBatch();
					}
				//execute the query with batch query param values
				int result[] = ps.executeBatch();
				//process the result[]
				if(result!=null)
					System.out.println("Group of Ticket booking is done for "+groupSize+" passengers");
				else
					System.out.println("Group Ticket booking is not done");
			}
			
			

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
