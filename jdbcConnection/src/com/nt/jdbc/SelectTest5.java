//SelectTest5.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*program for emp and dept table findout the location*/
public class SelectTest5 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//taking input
			sc = new Scanner(System.in);
			int deptno =0;
			if(sc!=null) {
				System.out.println("Enter the deptno:: ");
				deptno = sc.nextInt();
			}
				//Establish the connection
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
				// create jdbc statement obj
				if(con!=null)
					st = con.createStatement();
				// prepare sql query
				 //"SELECT ENAME,LOC,DNAME FROM EMP E,DEPT D WHERE D.DEPTNO="+deptno;
				
				String query =  "SELECT ENAME,LOC,DNAME FROM EMP E,DEPT D WHERE D.DEPTNO="+deptno;
				System.out.println(query);
				// send the query to d/b s/w
				if(st!=null) 
					rs = st.executeQuery(query);
					// process the resultset objcet
					if(rs!=null) {
					while(rs.next()) {
						System.out.println(rs.getString(1)+"--------->"+rs.getString(2)+"--------->"+rs.getString(3));
					}
					}
					
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(st!=null) 
				st.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		

	}

}
