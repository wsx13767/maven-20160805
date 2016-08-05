package dao;
import java.sql.*;





public class RegisterDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	  
    
    public RegisterDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
    
    // 帳號檢查 如果帳號已存在回傳F 帳號不存在回傳T
    public boolean RegisterAccountCheck(String account) throws Exception{
    	String sql = "SELECT account FROM USER_DATA where account = ?";
       	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,account);
    	rs = pstmt.executeQuery();
    	if(rs.next()){close(); return false;}
    	else{close(); return true;}
    	        }
 // email檢查 如果email已存在回傳F email不存在回傳T
    public boolean RegisterEmailCheck(String email) throws Exception{
    	String sql = "SELECT email FROM USER_DATA where email = ?";
       	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,email);
    	rs = pstmt.executeQuery();
    	if(rs.next()){close(); return false;}
    	else{close(); return true;}
    	        }
    
    
    
    
    public int finduserid() throws Exception{
    	String sql = "SELECT max(user_id) FROM user_data";
    	pstmt = conn.prepareStatement(sql);
    	rs = pstmt.executeQuery();
    	rs.next();
    	int a=rs.getInt(1);
    	
    	int userid=rs.getInt(1)+1;
    	
    	return userid;
    }
    
    public boolean Register(String account,String password,String name,String phone,String email ) throws Exception{
    	String sql = "Insert into SSD.USER_DATA (USER_ID,ACCOUNT,PASSWORD,NAME,PHONE,EMAIL,MONEY) values (?,?,?,?,?,?,0)";
    	int userid=finduserid();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setInt(1,userid);
    	pstmt.setString(2,account);
    	pstmt.setString(3,password);
    	pstmt.setString(4,name);
    	pstmt.setString(5,phone);
    	pstmt.setString(6,email);
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
