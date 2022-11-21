//SelectTest3.java
package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*java Aap to get emp highest salary */
public class SelectTest3 {

	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//taking input
			sc = new Scanner(System.in);
			int rownum = 0;
			if(sc!=null) {
				System.out.println("Enter rownum :");
				rownum = sc.nextInt();
			}
			//load jdbc class(optional)
			//Class.forName("orcle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			//con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// create jdbc statement obj
			if(con!=null)
				st = con.createStatement();
			// prepare sql query
			//select * from(select * from emp order by sal desc) emp where rownum<=rownum;
			 String cond1 = "(";
			 String cond2 = ")";
			 
				String query = "SELECT * FROM"+cond1+"SELECT ENAME,JOB,SAL  FROM EMP ORDER BY SAL DESC"+cond2+"EMP WHERE ROWNUM<="+rownum;
				System.out.println(query);
				// send the query to d/b s/w
				if(st!=null)
					rs = st.executeQuery(query);
				// process the resultset objcet
				if(rs!=null) {
					while(rs.next()) {
						System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3));
					}
				}
			
			
		}
		catch (SQLException se) {
			se.printStackTrace();

		
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();

			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // finalyy

	}

}
