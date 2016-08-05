package dao;
import model.*;

import java.util.*;
import java.sql.*;

public class OrderManagementMoreDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public OrderManagementMoreDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public JoinOrder createObject(ResultSet rs) throws Exception{
		JoinOrder joinOrder = new JoinOrder();
		UserData userData = new UserData();
		BuildOrder buildOrder = new BuildOrder();
		ItemInformation itemInformation = new ItemInformation();
		userData.setAccount(rs.getString("ACCOUNT"));
		itemInformation.setName(rs.getString("NAME"));
		joinOrder.setCount(rs.getBigDecimal("COUNT"));
		joinOrder.setMoney(rs.getBigDecimal("MONEY"));
		joinOrder.setSize(rs.getString("SIZE"));
		joinOrder.setSuger(rs.getString("SUGER"));
		joinOrder.setIce(rs.getString("ICE"));
		joinOrder.setJoinTime(rs.getDate("JOIN_TIME"));
		joinOrder.setJoinTimeString(rs.getString("TIME"));
		//userData.setPhone(rs.getString("PHONE"));
		userData.setEmail(rs.getString("EMAIL"));
		joinOrder.setGet(rs.getBigDecimal("GET"));
		joinOrder.setJoinLog(rs.getString("JOIN_LOG"));
		
		joinOrder.setUserData(userData);
		joinOrder.setBuildOrder(buildOrder);
		joinOrder.setItemInformation(itemInformation);
		return joinOrder;
	}


	public List<JoinOrder> findByOrderNumber(String order_number, int user_id) throws Exception{
		List<JoinOrder> list = new ArrayList<JoinOrder>();
		String sql = "select user_data.account, item_information.name, join_order.count, join_order.money, "
					+"decode(join_order.\"SIZE\",null,' ','b','(大杯/','m','(中杯/','s','(小杯/',' ') \"SIZE\", "
					+"decode(join_order.suger,null,' ',0,'無糖/',3,'微糖/',5,'半糖/',7,'少糖/',10,'全糖/',' ')　suger, "
					+"decode(join_order.ice,null,' ',0,'去冰)',5,'少冰)',10,'正常)',' ')　ice, "
					+"join_order.join_time, user_data.phone, user_data.email, join_order.get, join_order.join_log, to_char(join_order.join_time, 'yyyy-mm-dd hh24:mi:ss') \"TIME\" "
					+"from join_order, user_data, item_information, build_order "
					+"where join_order.user_id = user_data.user_id "
					+"and join_order.item_number = item_information.item_number "
					+"and build_order.order_number = join_order.order_number "
					+"and build_order.order_number = ? "
					+"and build_order.user_id = ? "
					+"order by join_order.join_time";
		pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order_number);
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
