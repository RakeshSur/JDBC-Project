package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransferTxMgmtTest {
	private static final String GET_BALANCE_ACCNO = "SELECT BALANCE FROM JDBC_ACCOUNT_INFO WHERE ACCNO=?";

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
				Statement st = con.createStatement();
				PreparedStatement ps = con.prepareStatement(GET_BALANCE_ACCNO);
				Scanner sc = new Scanner(System.in);) {
			long srcNO = 0, destNo = 0;
			float amount = 0.0f;
			if (sc != null) {
				System.out.println("Enter the source Account NO::");
				srcNO = sc.nextLong();
				System.out.println("Enter the dest Account NO::");
				destNo = sc.nextLong();
				System.out.println("Enter amount to transfer::");
				amount = sc.nextFloat();
				// set value to the query param
				if (ps != null) {
					ps.setLong(1, srcNO);
				}
				try (ResultSet rs = ps.executeQuery();) {
					float balance = 0.0f;
					if (rs.next()) {
						balance = rs.getFloat(1);
						if (amount > balance) {
							System.out.println("Unsufficent balance in the source accoutn to (Tx aborted)");
							return;
						}
						} 
						else {
							System.out.println("Source account not found");
						}
				
				} // nested try end
					// begin Tx
				if (con != null)
					con.setAutoCommit(false);
				if (st != null) {
					// add query to the batch

					// for withdraw operation
					st.addBatch("UPDATE JDBC_ACCOUNT_INFO  SET BALANCE = BALANCE-" + amount + "WHERE ACCNO=" + srcNO);
					// for deposit operation
					st.addBatch("UPDATE JDBC_ACCOUNT_INFO  SET BALANCE = BALANCE+" + amount + "WHERE ACCNO=" + destNo);
					// execute batch query
					int result[] = st.executeBatch();
					// process the result by Txmanagement
					if (result != null) {
						boolean taskFlag = true;
						for (int i = 0; i < result.length; ++i) {
							if (result[i] == 0) {
								taskFlag = false;
								break;
							} // if
						} // for
						if (taskFlag) {
							con.commit();
							System.out.println("Transaction commited Money Transfer::");
						} else {
							con.rollback();
							System.out.println("TransActio not commited(money not Transfer)");
						}
					}

				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
