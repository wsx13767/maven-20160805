package dao;
import model.*;

import java.util.*;
import java.sql.*;

public class UserDataDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public UserDataDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public UserData createObject(ResultSet rs) throws Exception{
		UserData userData = new UserData();
		userData.setUserId(rs.getInt("USER_ID"));
		userData.setAccount(rs.getString("ACCOUNT"));
		userData.setPassword(rs.getString("PASSWORD"));
		userData.setName(rs.getString("NAME"));
		userData.setPhone(rs.getString("PHONE"));
		userData.setEmail(rs.getString("EMAIL"));
		userData.setMoney(rs.getBigDecimal("MONEY"));
		return userData;
	}

	public List<UserData> findAll() throws Exception{
		List<UserData> list = new ArrayList<UserData>();
		String sql = "select * from user_data";
 		this.rs = stmt.executeQuery(sql);
		while(rs.next()){
			list.add(createObject(rs)); 
		}
		close();
		return list;
	}
	
	public UserData findById(int user_id) throws Exception{
		UserData userData = null;
		String sql = "select * from user_data where user_id = ?";
		pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			userData=createObject(rs);
		}
		close();
		return userData;
	}
	
	public List<UserData> findByAccountList(String account) throws Exception{
		List<UserData> list = new ArrayList<UserData>();
		String sql = "select * from user_data where account = ?";
		pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, account);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			list.add(createObject(rs)); 
		}
		close();
		return list;
	}
	
	public UserData findByAccount(String account) throws Exception{
		UserData userData = null;
		String sql = "select * from user_data where account = ?";
		pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, account);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			userData=createObject(rs);
		}
		close();
		return userData;
	}
	public UserData findByEmail (String email) throws Exception{
		UserData userData = null;
		String sql = "select * from user_data where email = ?";
		pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			userData=createObject(rs);
		}
		close();
		return userData;
	}
	public void updatePassword(int user_id, String password) throws Exception{
		String sql = "update user_data set password = ? where user_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setInt(2, user_id);
		pstmt.executeQuery();
		conn.commit();
		close();
	}
	
	public void updateName(int user_id, String name) throws Exception{
		String sql = "update user_data set name = ? where user_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setInt(2, user_id);
		pstmt.executeQuery();
		conn.commit();
		close();
	}
	
	public void updatePhone(int user_id, String phone) throws Exception{
		String sql = "update user_data set phone = ? where user_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, phone);
		pstmt.setInt(2, user_id);
		pstmt.executeQuery();
		conn.commit();
		close();
	}
	
	public void updateEmail(int user_id, String email) throws Exception{
		String sql = "update user_data set email = ? where user_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setInt(2, user_id);
		pstmt.executeQuery();
		conn.commit();
		close();
	}

	private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
}
