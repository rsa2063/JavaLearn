package utils;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

public class SearchFilter implements Predicate {
	private String column;

	public SearchFilter(String searchRegex) {
		if (searchRegex != null && !searchRegex.isEmpty()) {
			column = searchRegex;
		}
	}

	public boolean evaluate(RowSet rs) {
		try {
			if (!rs.isAfterLast()) {
				double price = rs.getDouble("price");
//				System.out.println("filter column is price");
				return price > Double.parseDouble(column);
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean evaluate(Object value, int column) throws SQLException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean evaluate(Object value, String columnName)
			throws SQLException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
