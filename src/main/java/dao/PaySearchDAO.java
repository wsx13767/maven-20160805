package dao;
import model.*;

import java.util.*;
import java.sql.*;

public class PaySearchDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public PaySearchDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public PaySearch createObject(ResultSet rs) throws Exception{
		PaySearch paySearch = new PaySearch();
		paySearch.setUserId(rs.getInt("USER_ID"));
		paySearch.setTime(rs.getDate("JOIN_TIME"));
		paySearch.setMoney(rs.getBigDecimal("MONEY"));
		paySearch.setMoneyAdd(rs.getBigDecimal("NULL"));
		paySearch.setItemName(rs.getString("NAME"));
		paySearch.setCount(rs.getBigDecimal("COUNT"));
		return paySearch;
	}

	public List<PaySearch> findById(int user_id) throws Exception{
		List<PaySearch> list = new ArrayList<PaySearch>();
		String sql = "(select join_order.user_id, join_order.join_time, join_order.money, null, item_information.name, join_order.count "
				+ "from join_order, item_information "
				+ "where join_order.user_id=? and join_order.item_number=item_information.item_number "
				+ "union "
				+ "select user_id, time, null, money, null, null "
				+ "from cash_log "
				+ "where user_id=?) "
				+ "order by join_time desc";
		pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2, user_id);
        this.rs = pstmt.executeQuery();
		while(rs.next()){
			list.add(createObject(rs));
		}
		close();
		return list;
	}

	private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
}
