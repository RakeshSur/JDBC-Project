package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataCapabilityTest_MYSql {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///NTAJ414DB1", "root", "root");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM PRODUCT");
				){
			ResultSetMetaData rsmd = null;
			if(rs!=null) {
				rsmd = rs.getMetaData();
			}
			if(rsmd!=null) {
				// get column count
				int colCount = rsmd.getColumnCount();
				// display more info about each column db table
				for (int i = 1; i <= colCount; ++i) {
					System.out.println("Column index  " + i);
					System.out.println("Column Name " + rsmd.getColumnName(i));
					System.out.println("Column  Data type Name " + rsmd.getColumnTypeName(i));
					System.out.println("Column Scle " + rsmd.getScale(i));
					System.out.println("Coulmn Prescion " + rsmd.getPrecision(i));
					System.out.println("is Column Signed " + rsmd.isSigned(i));
					System.out.println("Is column Autoincrement " + rsmd.isAutoIncrement(i));
					System.out.println("is Column Nullable " + rsmd.isNullable(i));
					System.out.println("is column Currency " + rsmd.isCurrency(i));
					System.out.println("is column Searchable " + rsmd.isSearchable(i));
					System.out.println("column display size " + rsmd.getColumnDisplaySize(i));
					System.out.println("is column Writeble " + rsmd.isWritable(i));
					System.out.println("=================================================");

				}
				
			}
			
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
