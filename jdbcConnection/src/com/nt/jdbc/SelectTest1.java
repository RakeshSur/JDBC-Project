package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest1 {

	public static void main(String[] args)  {

		Scanner sc = null;
		Connection con = null;
		Statement st=null;
		ResultSet rs = null;
		try{
			sc = new Scanner(System.in);
			float startSalary = 0.0f;
			float endSalary =  0.0f;
			if(sc!=null){
			System.out.println("Enter start range of salary");
			startSalary=sc.nextFloat();
			System.out.println("Enter end range of salary");
            endSalary=sc.nextFloat();
			  }
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
			if(con!=null)
			st=con.createStatement();
			String query = "SELECT EMPNO, ENAME,JOB,SAL FROM EMP WHERE SAL>="+startSalary+"AND SAL<="+endSalary;
			System.out.println(query);
			if(st!=null){
				rs=st.executeQuery(query);
			}
				if(rs!=null){
				while(rs.next()==true){
				            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				
				
				         }
				  }
			}
			catch(SQLException se){
			 se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			   cnf.printStackTrace();
			}
			catch(Exception e){
			e.printStackTrace();
			
			}
			finally{
			             try{
							 if (rs!=null)
								 rs.close();
						
						 }
						 catch(SQLException se){
						se.printStackTrace();
						   }
						    try{
							 if (st!=null)
								 st.close();
						
						 }
						 catch(SQLException se){
						se.printStackTrace();
						   }
						   try{
							 if (con!=null)
								 con.close();
						
						 }
						 catch(SQLException se){
						se.printStackTrace();
						   }

						    try{
							 if (sc!=null)
								 sc.close();
						
						 }
						 catch(Exception e){
						e.printStackTrace();
						   }

			  }	
    }

}
