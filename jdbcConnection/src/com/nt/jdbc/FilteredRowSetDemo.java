package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

import oracle.jdbc.rowset.OracleFilteredRowSet;

public class FilteredRowSetDemo {
	//nested inner class - static inner class
	private static class filtered1 implements Predicate{
		private String cond;
		public filtered1(String cond) {
			this.cond = cond;
		}

		@Override
		public boolean evaluate(RowSet rs) {
			System.out.println("FilteredRowSetDemo.filtered1.evaluate()");
			try {
				if(rs.getString("ENAME").startsWith(cond))
					return true;
			}
		
			catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public boolean evaluate(Object value, int column) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean evaluate(Object value, String columnName) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

	public static void main(String[] args) {
		try(OracleFilteredRowSet fr = new OracleFilteredRowSet();
				){
			fr.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			fr.setUsername("scott");
			fr.setPassword("tiger");
			fr.setCommand("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
			fr.setFilter(new filtered1("A"));
			fr.execute();
			while (fr.next()) {
				System.out.println(fr.getInt(1) + " " + fr.getString(2) + " " + fr.getString(3) + " "
						+ fr.getFloat(4));
			} // while
			
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
