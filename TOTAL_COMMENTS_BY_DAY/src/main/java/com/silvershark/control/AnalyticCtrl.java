/**
 * 
 */
package com.silvershark.control;

import java.sql.Connection;
import java.util.List;

import com.silvershark.connection.PhoenixExecuteSQL;
import com.silvershark.connection.PhoenixJDBCConnection;

/**
 * @author Rodrigo
 *
 */
public class AnalyticCtrl {
	
	private static final String TOTAL_LIKES_BY_DAY = "SELECT COUNT(*),TO_DATE(DATE_INPUT) FROM FACEBOOK_FEED_DATA_LIKES GROUP BY DATE_INPUT";

	@SuppressWarnings("static-access")
	public List<String> getTotalLikesByDay(){
		PhoenixJDBCConnection jdbc = new PhoenixJDBCConnection();
		Connection conn = jdbc.createConnection();
		PhoenixExecuteSQL sql = new PhoenixExecuteSQL();
		sql.executeQUERY(TOTAL_LIKES_BY_DAY, conn);
		return null;
	}
}
