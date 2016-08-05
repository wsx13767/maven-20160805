package dao;
import model.*;

import java.util.*;
import java.math.BigDecimal;
import java.sql.*;

import com.google.gson.Gson;

public class jQcloudDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public jQcloudDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public JoinOrder createObject(ResultSet rs) throws Exception{
		JoinOrder joinOrder = new JoinOrder();
		UserData userData = new UserData();
		BuildOrder buildOrder = new BuildOrder();
		ItemInformation itemInformation = new ItemInformation();

		joinOrder.setCount(rs.getBigDecimal("COUNT"));
		itemInformation.setName(rs.getString("NAME"));
		itemInformation.setItemNumber(rs.getString("ITEM_NUMBER"));
		buildOrder.setOrderNumber(rs.getString("ORDER_NUMBER"));
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
