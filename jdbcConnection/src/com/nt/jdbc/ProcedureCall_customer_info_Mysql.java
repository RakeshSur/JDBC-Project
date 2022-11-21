package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProcedureCall_customer_info_Mysql {
	/*
	 * SE `ntaj414db1`;
DROP procedure IF EXISTS `GET_DETAILS_JOBSEEKER`;

DELIMITER $$
USE `ntaj414db1`$$
CREATE PROCEDURE `GET_DETAILS_JOBSEEKER` (in no int)
BEGIN
select canme,billamt from cutomer_info where cno=no;

END;$$

DELIMITER ;
	 */
    private static final String PROCEDURE_CALL = "{call GET_DETAILS_CUSTOMER(?) }";
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj414db1", "root", "root");
				CallableStatement cs = con.prepareCall(PROCEDURE_CALL); 
				){
			int no=0;
			if(sc!=null) {
				System.out.println("Enter the no");
				no=sc.nextInt();
			}
			if(cs!=null) {
				cs.setInt(1, no);
				cs.execute();
				try(ResultSet rs = cs.getResultSet();
						){
					while(rs.next()) {
						System.out.println(rs.getString(1)+" "+rs.getFloat(2));
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
