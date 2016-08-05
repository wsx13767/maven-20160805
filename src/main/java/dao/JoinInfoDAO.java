package dao;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoinInfoDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public JoinInfoDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public JoinOrder createObject(ResultSet rs) throws Exception{
		JoinOrder joinOrder = new JoinOrder();
		UserData userData = new UserData();
		ItemInformation itemInformation = new ItemInformation();
		userData.setAccount(rs.getString("ACCOUNT"));
		itemInformation.setName(rs.getString("NAME"));
		joinOrder.setCount(rs.getBigDecimal("COUNT"));
		joinOrder.setJoinTime(rs.getDate("JOIN_TIME"));
		joinOrder.setMoney(rs.getBigDecimal("MONEY"));
		joinOrder.setGet(rs.getBigDecimal("GET"));
		joinOrder.setJoinLog(rs.getString("JOIN_LOG"));
		
		joinOrder.setUserData(userData);
		joinOrder.setItemInformation(itemInformation);
		return joinOrder;
	}

	public List<JoinOrder> findByOrderNumber(String order_number) throws Exception{
		List<JoinOrder> list = new ArrayList<JoinOrder>();
		String sql = "select user_data.account, item_information.name, join_order.count ,join_order.join_time, join_order.money, join_order.get, join_order.join_log "
					+"from join_order, item_information, user_data "
					+"where join_order.order_number = ? "
					+"and item_information.order_number = ? "
					+"and join_order.item_number = item_information.item_number "
					+"and join_order.user_id = user_data.user_id "
					+"order by join_order.join_time";
		pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order_number);
        pstmt.setString(2, order_number);
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
