package dao;
import model.*;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class OrderSearchDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public OrderSearchDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public BuildOrder createObject(ResultSet rs) throws Exception{
		BuildOrder buildOrder = new BuildOrder();
		UserData userData = new UserData();
		userData.setUserId(rs.getInt("USER_ID"));
		userData.setAccount(rs.getString("ACCOUNT"));
		buildOrder.setOrderNumber(rs.getString("ORDER_NUMBER"));
		buildOrder.setRemark(rs.getString("REMARK"));
		buildOrder.setItemInformation(rs.getString("ITEM_INFORMATION"));
		buildOrder.setDeadlineString(rs.getString("NDEADLINE"));
		buildOrder.setBuildTimeString(rs.getString("NBUILD_TIME"));
		buildOrder.setPicture(rs.getString("PICTURE"));
		buildOrder.setBuildState(rs.getString("STATE"));
		buildOrder.setUserData(userData);
		return buildOrder;
	}
	
	public List<BuildOrder> findByBUId(int user_id) throws Exception{
		List<BuildOrder> list = new ArrayList<BuildOrder>();
		String sql = "select build_order.*, user_data.account, to_char(build_order.build_time, 'yyyy-mm-dd hh24:mi:ss') \"NBUILD_TIME\", to_char(build_order.deadline, 'yyyy-mm-dd hh24:mi:ss') \"NDEADLINE\", decode(build_order.build_state,null,' ','live','發佈中','dead','已截止','已完結') \"STATE\" "
				+ "from build_order, user_data "
				+ "where build_order.user_id = ? "
				+ "and build_order.user_id = user_data.user_id";
		pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        this.rs = pstmt.executeQuery();
        while(rs.next()){
        	list.add(createObject(rs));
        }
        close();
        return list;
	}
	
	public List<BuildOrder> findByJUId(int user_id) throws Exception{
		List<BuildOrder> list = new ArrayList<BuildOrder>();
		String sql = "select build_order.*, user_data.account, to_char(build_order.build_time, 'yyyy-mm-dd hh24:mi:ss') \"NBUILD_TIME\", to_char(build_order.deadline, 'yyyy-mm-dd hh24:mi:ss') \"NDEADLINE\", decode(build_order.build_state,null,' ','live','發佈中','dead','已截止','已完結') \"STATE\" "
				+ "from build_order, user_data "
				+ "where order_number "
				+ "in (select order_number from join_order where user_id = ?) "
				+ "and build_order.user_id = user_data.user_id";
		pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
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
