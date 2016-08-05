package dao;
import model.*;

import java.util.*;
import java.sql.*;

public class JoinOrderDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public JoinOrderDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public JoinOrder createObject(ResultSet rs) throws Exception{
		JoinOrder joinOrder = new JoinOrder();
		UserData userData = new UserData();
		BuildOrder buildOrder = new BuildOrder();
		ItemInformation itemInformation = new ItemInformation();
		userData.setUserId(rs.getInt("USER_ID"));
		joinOrder.setJoinLog(rs.getString("JOIN_LOG"));
		buildOrder.setOrderNumber(rs.getString("ORDER_NUMBER"));
		itemInformation.setItemNumber(rs.getString("ITEM_NUMBER"));
		joinOrder.setCount(rs.getBigDecimal("COUNT"));
		joinOrder.setJoinTime(rs.getDate("JOIN_TIME"));
		joinOrder.setGet(rs.getBigDecimal("GET"));
		userData.setUserId(rs.getInt("USER_ID"));
		joinOrder.setMoney(rs.getBigDecimal("MONEY"));
		joinOrder.setUserData(userData);
		joinOrder.setBuildOrder(buildOrder);
		joinOrder.setItemInformation(itemInformation);
		return joinOrder;
	}

	public List<JoinOrder> findAll() throws Exception{
		List<JoinOrder> list = new ArrayList<JoinOrder>();
		String sql = "select * from join_order";
 		this.rs = stmt.executeQuery(sql);
		while(rs.next()){
			list.add(createObject(rs)); 
		}
		close();
		return list;
	}

	public JoinOrder findById(int join_log) throws Exception{
		JoinOrder joinOrder = null;
		String sql = "select * from join_order where join_log = ?";
		pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, join_log);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			joinOrder=createObject(rs);
		}
		close();
		return joinOrder;
	}
	
	public void updateGet(String join_log) throws Exception{
		String sql = "update join_order set get = 1 where join_log = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, join_log);
		pstmt.executeQuery();
		conn.commit();
		close();
	}

	private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
}
