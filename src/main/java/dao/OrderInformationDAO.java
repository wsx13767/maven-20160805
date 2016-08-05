package dao;
import model.*;

import java.util.*;
import java.math.BigDecimal;
import java.sql.*;

import com.google.gson.Gson;

public class OrderInformationDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public OrderInformationDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public JoinOrder createObject(ResultSet rs) throws Exception{
		JoinOrder joinOrder = new JoinOrder();
		UserData userData = new UserData();
		BuildOrder buildOrder = new BuildOrder();
		ItemInformation itemInformation = new ItemInformation();

		//joinOrder.setCount(rs.getBigDecimal("COUNT"));
		buildOrder.setItemInformation(rs.getString("ITEM_INFORMATION"));
		buildOrder.setOrderNumber(rs.getString("ORDER_NUMBER"));
		buildOrder.setDeadlineString(rs.getString(2));
		buildOrder.setBuildTimeString(rs.getString(3));
		buildOrder.setRemark(rs.getString("REMARK"));
		buildOrder.setPicture(rs.getString("PICTURE"));
		itemInformation.setName(rs.getString("NAME"));
		itemInformation.setItemNumber(rs.getString("ITEM_NUMBER"));
		itemInformation.setMax(rs.getBigDecimal("MAX"));
		itemInformation.setMin(rs.getBigDecimal("MIN"));
		itemInformation.setMoney(rs.getBigDecimal("MONEY"));
		itemInformation.setBigPrice(rs.getBigDecimal("BIG_PRICE"));
		itemInformation.setMidPrice(rs.getBigDecimal("MID_PRICE"));
		itemInformation.setSmallPrice(rs.getBigDecimal("SMALL_PRICE"));
		itemInformation.setSuger(rs.getString("SUGER"));
		itemInformation.setIce(rs.getString("ICE"));
		itemInformation.setSize(rs.getString("SIZE"));
		userData.setAccount(rs.getString("ACCOUNT"));
		joinOrder.setUserData(userData);
		joinOrder.setBuildOrder(buildOrder);
		joinOrder.setItemInformation(itemInformation);

		return joinOrder;
	}

	public List < JoinOrder > findAll(String sql) throws Exception{
		List<JoinOrder> list = new ArrayList<JoinOrder>();	
		this.rs = stmt.executeQuery(sql);
		while(rs.next()){
			list.add(createObject(rs)); 
		}
		close();
		return list;
	}
	
	/*public JoinOrder findById(int join_log) throws Exception{
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
	}*/

	private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
	
}

