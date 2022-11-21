package com.nt.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;

public class JoinRowSet_Demo {

	public static void main(String[] args) {
		try (CachedRowSet crrowSet1 = new OracleCachedRowSet();
				CachedRowSet crrowSet2 = new OracleCachedRowSet();
				JoinRowSet jrowSet = new OracleJoinRowSet();) {
			crrowSet1.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			crrowSet1.setUsername("scott");
			crrowSet1.setPassword("tiger");
			crrowSet1.setMatchColumn(5);
			crrowSet1.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
			crrowSet1.execute();

			crrowSet2.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			crrowSet2.setUsername("scott");
			crrowSet2.setPassword("tiger");
			crrowSet2.setMatchColumn(1);
			crrowSet2.setCommand("SELECT DEPTNO,DNAME,LOC FROM DEPT");
			crrowSet2.execute();

			// add multiple CachedRowSet into JoinRowSet
			jrowSet.addRowSet(crrowSet2);
			jrowSet.addRowSet(crrowSet1);

			// process the joinRowSet
			while (jrowSet.next()) {
				System.out.println(jrowSet.getString(1) + " " + jrowSet.getString(2) + " " + jrowSet.getString(3) + " "
						+ jrowSet.getString(4) + " " + jrowSet.getString(5) + " " + jrowSet.getString(6) + " "
						+ jrowSet.getString(7));
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
