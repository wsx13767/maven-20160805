package dao;
import java.sql.*;





public class LoginCheckDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	  
    
    public LoginCheckDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
    
    public boolean FindIDPW(String account,String password ) throws Exception{
    	String sql = "SELECT account,password FROM user_data where account=? and  password=?";
    	
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,account);
    	pstmt.setString(2,password);
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
