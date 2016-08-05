package dao;
import model.*;

import java.util.*;
import java.sql.*;

public class ItemInformationDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public ItemInformationDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public ItemInformation createObject(ResultSet rs) throws Exception{
		ItemInformation itemInformation = new ItemInformation();
		BuildOrder buildOrder = new BuildOrder();
		UserData userData = new UserData();
		userData.setUserId(rs.getInt("USER_ID"));
		itemInformation.setName(rs.getString("NAME"));
		itemInformation.setItemNumber(rs.getString("ITEM_NUMBER"));
		buildOrder.setOrderNumber(rs.getString("ORDER_NUMBER"));
		itemInformation.setMax(rs.getBigDecimal("MAX"));
		itemInformation.setMin(rs.getBigDecimal("MIN"));
		itemInformation.setMoney(rs.getBigDecimal("MONEY"));
		itemInformation.setSuger(rs.getString("SUGER"));
		itemInformation.setIce(rs.getString("ICE"));
		itemInformation.setSize(rs.getString("SIZE"));
		itemInformation.setBigPrice(rs.getBigDecimal("BIG_PRICE"));
		itemInformation.setMidPrice(rs.getBigDecimal("MID_PRICE"));
		itemInformation.setSmallPrice(rs.getBigDecimal("SMALL_PRICE"));
		itemInformation.setUserData(userData);
		itemInformation.setBuildOrder(buildOrder);
		return itemInformation;
	}

	public List<ItemInformation> findAll() throws Exception{
		List<ItemInformation> list = new ArrayList<ItemInformation>();
		String sql = "select * from item_information";
 		this.rs = stmt.executeQuery(sql);
		while(rs.next()){
			list.add(createObject(rs)); 
		}
		close();
		return list;
	}

	public ItemInformation findById(int item_number) throws Exception{
		ItemInformation itemInformation = null;
		String sql = "select * from item_information where item_number = ?";
		pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, item_number);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			itemInformation=createObject(rs);
		}
		close();
		return itemInformation;
	}
	public List<ItemInformation> findByOrdernumber(int ordernumber)throws Exception{
		List<ItemInformation> list = new ArrayList<ItemInformation>();
		String sql = "SELECT * FROM ITEM_INFORMATION WHERE ORDER_NUMBER = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ordernumber);
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
