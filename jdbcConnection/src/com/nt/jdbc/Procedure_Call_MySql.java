package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Procedure_Call_MySql {
	public static final String PROCUDRE_CALL = "{call p_get_product_details_byPriceRange(?,?)}";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				CallableStatement cs = con.prepareCall(PROCUDRE_CALL);) {
			int startPrice = 0,endPrice = 0;
			if (sc != null) {
				System.out.println("Enter the Starting price:: ");
				startPrice= sc.nextInt();
				System.out.println("Enter the End Price");
				endPrice = sc.nextInt();
			}
			if (cs != null) {
				cs.setInt(1, startPrice);
				cs.setInt(2, endPrice);
				cs.execute();
			
			try(ResultSet rs = cs.getResultSet();
					){
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
				}
			}
			}
		} 
		catch (SQLException se) {
			if (se.getErrorCode() == 1403) {
				System.out.println("Invaild entry");
				se.printStackTrace();
			} else if (se.getErrorCode() == 1017) {
				System.out.println("Invaild credential");
			} else {
				System.out.println("Some db problem");
				//se.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
