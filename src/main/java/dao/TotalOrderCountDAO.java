package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.TotalOrderCount;


	public class TotalOrderCountDAO {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	public TotalOrderCountDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public TotalOrderCount createObject(ResultSet rs) throws Exception{
		TotalOrderCount toc=new TotalOrderCount();
		toc.setOrderCount(rs.getInt(1));
		return toc;
	}
	
	public List<TotalOrderCount> findAll(String sql) throws Exception{
		List<TotalOrderCount> list = new ArrayList<TotalOrderCount>();	
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
