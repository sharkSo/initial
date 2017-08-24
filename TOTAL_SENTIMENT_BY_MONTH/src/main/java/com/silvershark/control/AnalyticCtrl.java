/**
 * 
 */
package com.silvershark.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.silvershark.connection.PhoenixExecuteSQL;
import com.silvershark.connection.PhoenixJDBCConnection;

/**
 * @author Rodrigo
 *
 */
public class AnalyticCtrl {
	
	private static final String TOTAL_LIKES_BY_DAY = "SELECT COUNT(*),SENTIMENT,MONTH(TO_TIMESTAMP(DATE_INPUT)),YEAR(TO_TIMESTAMP(DATE_INPUT)) FROM SENTIMENT_RESULTS_FB GROUP BY SENTIMENT,MONTH(TO_TIMESTAMP(DATE_INPUT)),YEAR(TO_TIMESTAMP(DATE_INPUT))";

	@SuppressWarnings("static-access")
	public List<String> getTotalLikesByDay(){
		PhoenixJDBCConnection jdbc = new PhoenixJDBCConnection();
		Connection conn = jdbc.createConnection();
		PhoenixExecuteSQL sql = new PhoenixExecuteSQL();
		ResultSet rs = sql.executeQUERY(TOTAL_LIKES_BY_DAY, conn);
		try {
			return sql.writeResultSet(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
