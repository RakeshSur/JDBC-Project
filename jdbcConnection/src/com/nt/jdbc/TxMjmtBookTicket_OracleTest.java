package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TxMjmtBookTicket_OracleTest {
	private static final String GET_AVAILABLE_TICKET = "SELECT AVAIL_TICKET FROM TICKET_BOOKING WHERE TID=?";

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				Statement st = con.createStatement();
				Scanner sc = new Scanner(System.in);
				PreparedStatement ps = con.prepareStatement(GET_AVAILABLE_TICKET);
				){
			int bookTicket = 0,avaTicket=0;
			if(sc!=null) {
				System.out.println("Enter the Booking Ticket::");
				bookTicket = sc.nextInt();
				System.out.println("Enter the ");
			}
			
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
