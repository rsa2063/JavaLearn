package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JDBC_SqlServer {

	public Boolean ConnectSqlServer() {
		Connection conn = null;
		Statement stmt = null;

		try {
			// 加载数据库引擎，返回给定字符串名的类
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("加载数据库引擎失败");
			System.exit(0);
		}

		try {
			conn = DriverManager
					.getConnection(
							"jdbc:sqlserver://localhost:1433;databaseName=books;integratedSecurity=true;",
							"sa", "123");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				log("jdbc connection successfully");
			}
		}

		if (conn != null) {
			try {
				
				String sql = "select distinct country, fruit, price from fruit_code";
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					    ResultSet.CONCUR_READ_ONLY);
				ResultSet result = null;
				if (stmt.execute(sql)) {
					result = (ResultSet) stmt.getResultSet();
				}
				if (result != null) {
					ResultSetMetaData rsSum = result.getMetaData();
					log("result column count is " + rsSum.getColumnCount());
					log("country fruit	price");
					while (result.next()) {
						log(result.getString(1) + "	" + result.getString(2)
								+ "	" + result.getDouble("price"));

					}

				}
				result.beforeFirst();
				log("2  -----------------------------------------");
				dumpRs((ResultSet) stmt.getResultSet());

				//test filteredrowset
				RowSetFactory factory = RowSetProvider.newFactory();
				FilteredRowSet filteredRs = factory.createFilteredRowSet();
				filteredRs.setCommand("select distinct country, fruit, price from fruit_code order by price asc");
				filteredRs.execute(conn);
				log("3  -----------------------------------------");
				dumpRs(filteredRs);
				filteredRs.setFilter(new SearchFilter("0"));
				filteredRs.beforeFirst();
				log("4   -----------------------------------------");
				dumpRs(filteredRs);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return true;
	}
	
	public void dumpRs(ResultSet result) throws SQLException{
		int i = 0;
		if (result != null) {
			while (result.next()) {
				log(result.getString(1) + "	" + result.getString(2)
						+ "	" + result.getDouble("price"));

				i++;
			}
			result.last();
			log("rs row count is " + result.getRow());
			log("rs row count is " + i);
		}
	}

	public static void log(String l) {
		System.out.println("Log: " + l);
	}
}
