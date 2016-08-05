package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.PptItem;


	public class PptItemDAO {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	public PptItemDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public PptItem createObject(ResultSet rs) throws Exception{
		PptItem indi=new PptItem();
		indi.setOrderNumber(rs.getInt("ORDER_NUMBER"));
		indi.setItemInformation(rs.getString("ITEM_INFORMATION"));
		indi.setPicture(rs.getString("PICTURE"));
		return indi;
	}
	
	public List<PptItem> findAll(String sql) throws Exception{
		List<PptItem> list = new ArrayList<PptItem>();	
		this.rs = stmt.executeQuery(sql);
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
