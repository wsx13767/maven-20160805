package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.IndexItem;


	public class IndexItemDAO {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	public IndexItemDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public IndexItem createObject(ResultSet rs) throws Exception{
		IndexItem iiem=new IndexItem();
		iiem.setOrderNumber(rs.getInt("ORDER_NUMBER"));
		iiem.setItemInformation(rs.getString("ITEM_INFORMATION"));
		iiem.setRemark(rs.getString("REMARK"));
		iiem.setPicture(rs.getString("PICTURE"));
		return iiem;
	}
	
	public List<IndexItem> findAll(String sql) throws Exception{
		List<IndexItem> list = new ArrayList<IndexItem>();	
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
