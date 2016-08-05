package dao;
import model.*;

import java.util.*;
import java.sql.*;

public class BuildOrderDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public BuildOrderDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public BuildOrder createObject(ResultSet rs) throws Exception{
		BuildOrder buildOrder = new BuildOrder();
		UserData userData = new UserData();
		userData.setUserId(rs.getInt("USER_ID"));
		buildOrder.setOrderNumber(rs.getString("ORDER_NUMBER"));
		buildOrder.setRemark(rs.getString("REMARK"));
		buildOrder.setItemInformation(rs.getString("ITEM_INFORMATION"));
		buildOrder.setDeadline(rs.getDate("DEADLINE"));
		buildOrder.setPicture(rs.getString("PICTURE"));
		buildOrder.setBuildTime(rs.getDate("BUILD_TIME"));
		buildOrder.setBuildState(rs.getString("BUILD_STATE"));
		buildOrder.setUserData(userData);
		return buildOrder;
	}

	public List<BuildOrder> findAll() throws Exception{
		List<BuildOrder> list = new ArrayList<BuildOrder>();
		String sql = "select * from build_order";
 		this.rs = stmt.executeQuery(sql);
		while(rs.next()){
			list.add(createObject(rs)); 
		}
		close();
		return list;
	}
	
	public List<BuildOrder> findByUserId(int user_id) throws Exception{
		List<BuildOrder> list = new ArrayList<BuildOrder>();
		String sql = "select * from build_order where user_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user_id);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			list.add(createObject(rs));
		}
		close();
		return list;
	}

	public BuildOrder findById(int order_number) throws Exception{
		BuildOrder buildOrder = null;
		String sql = "select * from build_order where order_number = ?";
		pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, order_number);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			buildOrder=createObject(rs);
		}
		close();
		return buildOrder;
	}
	
	public void updateStateToDead(String order_number) throws Exception{
		String sql = "update build_order set build_state = 'dead' where order_number = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, order_number);
		pstmt.executeQuery();
		conn.commit();
		close();
	}
	
	public void updateStateToFinish(String order_number) throws Exception{
		String sql = "update build_order set build_state = 'finsh' where order_number = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, order_number);
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
