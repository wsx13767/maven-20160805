package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CountJoinItem;


	public class CountJoinItemDAO {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	public CountJoinItemDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public CountJoinItem createObject(ResultSet rs) throws Exception{
		CountJoinItem cji=new CountJoinItem();
		cji.setCountJoinItem(rs.getInt("SUM(COUNT)"));
		return cji;
	}
	
	public List<CountJoinItem> findAll(String sql) throws Exception{
		List<CountJoinItem> list = new ArrayList<CountJoinItem>();	
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
