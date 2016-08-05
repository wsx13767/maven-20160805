package dao;
import java.sql.*;


import java.util.*;



public class CashDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	  
    
    public CashDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
    
    // 取得現在金錢
    public int getnowmoney(String account) throws Exception{
    	String sql = "SELECT money FROM user_data WHERE account = ?";
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,account);
    	rs = pstmt.executeQuery();
    	rs.next();
    	int a=rs.getInt(1);
    	return a;
    }
    
    //加值金錢
    public boolean cash(String account,int money) throws Exception{
    	String sql = "UPDATE user_data SET money=? WHERE account = ?";
    	pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,money);
    	pstmt.setString(2,account);
    	rs = pstmt.executeQuery();

    	
    	if(rs.next()){close(); return true;}
    	else{close(); return false;}
    	        }
    
    
    
    
    private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
   
}
