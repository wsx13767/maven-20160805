package dao;
import model.*;

import java.util.*;
import java.sql.*;

public class CashLogDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public CashLogDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public CashLog createObject(ResultSet rs) throws Exception{
		CashLog cashLog = new CashLog();
		UserData userData = new UserData();
		userData.setUserId(rs.getInt("USER_ID"));
		cashLog.setLogNumber(rs.getString("LOG_NUMBER"));
		cashLog.setTime(rs.getDate("TIME"));
		cashLog.setMoney(rs.getBigDecimal("MONEY"));
		cashLog.setUserData(userData);
		return cashLog;
	}

	public List<CashLog> findAll() throws Exception{
		List<CashLog> list = new ArrayList<CashLog>();
		String sql = "select * from cash_log";
 		this.rs = stmt.executeQuery(sql);
		while(rs.next()){
			list.add(createObject(rs)); 
		}
		close();
		return list;
	}

	public CashLog findById(int log_number) throws Exception{
		CashLog cashLog = null;
		String sql = "select * from cash_log where log_number = ?";
		pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, log_number);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			cashLog=createObject(rs);
		}
		close();
		return cashLog;
	}

	private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
}
