//PsInsertTestMySql1.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTestMySql1 {
	private static final String PRODUCT_QUERY = "INSERT INTO PRODUCT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ414DB1", "root","root");
				PreparedStatement ps=con.prepareStatement(PRODUCT_QUERY);){
			int count = 0;
			if(sc!=null) {
				System.out.println("Enter the product count:: ");
				count = sc.nextInt();
			}
			if(sc!=null && ps!=null) {
				// read the product details
				System.out.println("Enter the product details:: ");
				for (int i = 1; i <= count; ++i) {
					// read each student details
					System.out.println("Enter " + i + "product details:: ");
					System.out.println("Enter the product pid:: ");
					int pid = sc.nextInt();
					System.out.println("Enter the product name:: ");
					String pname = sc.next();
					System.out.println("Enter the product price:: ");
					float price = sc.nextFloat();
					System.out.println("Enter the product quanty:: ");
					float qty = sc.nextFloat();
					// set each product details as preCompiled sql query as query param values(?)
					ps.setInt(1, pid);
					ps.setString(2, pname);
					ps.setFloat(3, price);
					ps.setFloat(4,qty);
					// execute the query
					int result = ps.executeUpdate();
					// process the result
					if (result == 0)
						System.out.println("  Product record is not inserted::");
					else
						System.out.println(" Product record is inserted");
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
