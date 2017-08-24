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
	
	private static final String TOTAL_LIKES_BY_DAY = "SELECT COUNT(DISTINCT(LINK)),DATA_ID, TO_DATE(DATE_INPUT) FROM FACEBOOK_FEED_DATA_LIKES  WHERE DATA_ID='999577163512356_1085772951559443' GROUP BY TO_DATE(CF1.DATE_INPUT),DATA_ID ORDER BY 1 DESC";

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
