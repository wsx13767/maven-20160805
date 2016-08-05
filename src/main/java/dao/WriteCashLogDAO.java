package dao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class WriteCashLogDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	  
    
    public WriteCashLogDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
    
    //取得當前lognumber+1
    public int findlognum() throws Exception{
    	String sql = "SELECT MAX(TO_NUMBER(log_number)) FROM cash_log";
    	pstmt = conn.prepareStatement(sql);
    	rs = pstmt.executeQuery();
    	rs.next();
    	int a=rs.getInt(1);
    	int lognum=rs.getInt(1)+1;
    	return lognum;
    }
    
    private String getCurrentDate(){
    	//設定日期格式
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	java.util.Date today = new java.util.Date();
    	return sdf.format(today);
    	
    	}
    
    //寫入LOG
    public int cashlog (long userid,int money) throws Exception{
    	String sql = "Insert into SSD.cash_log (USER_ID,log_number,TIME,MONEY) values (?,?,to_date(?, 'yyyy-mm-dd hh24:mi:ss'),?)";
    	//Date date = new Date();
    	int lognum = findlognum(); 
    
    	pstmt = conn.prepareStatement(sql);
    	
    	
		
    	pstmt.setFloat(1,userid);
    	pstmt.setInt(2,lognum);
    	pstmt.setString(3,getCurrentDate());
    	pstmt.setInt(4,money);
    	
    	rs = pstmt.executeQuery();

    	
    	if(rs.next()){close(); return 1;}
    	else{close(); return 0;}
    	        }
    
    
    private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
   
}
