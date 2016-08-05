package dao;

import java.sql.*;  
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import model.*;

import org.apache.commons.lang.time.DateFormatUtils;
public class InsertJoinOrderDAO{  
	
	
	private String  getCurrentDate(){
		//設定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date today = new java.util.Date();
	    return sdf.format(today);
	}
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public InsertJoinOrderDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}

	public String insert( List <InsertJoinOrder> list,String orderNumber,String userId,int totalMoney) throws Exception{
		
		InsertJoinOrderDAO ijod=new InsertJoinOrderDAO();
			//System.out.println(orderNumber.trim());
			for(int i=0;i<list.size();i++){
				//System.out.println("i="+i);
				pstmt = conn.prepareStatement("insert into SSD.JOIN_ORDER ( ORDER_NUMBER, ITEM_NUMBER, COUNT, JOIN_TIME, GET, USER_ID, MONEY, \"SIZE\", SUGER, ICE) values (?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi:ss'), ?, ?, ?, ?, ?, ? )");
				pstmt.setString(1,orderNumber.trim());
				pstmt.setString(2, list.get(i).getItemNumber().trim());
				pstmt.setString(3, list.get(i).getJItemQuan());	
				pstmt.setString(4, getCurrentDate());	
				pstmt.setInt(5, 0);
				pstmt.setString(6, userId);	
				pstmt.setString(7, list.get(i).getJTotalPrice());
				if(list.get(i).getJSize().trim().equals("null")){
					pstmt.setString(8,"");	
				}else{
					pstmt.setString(8, list.get(i).getJSize().trim());	
				}
				
				if( list.get(i).getJSuger().trim().equals("null")){
					pstmt.setString(9,"");	
				}else{
					pstmt.setString(9, list.get(i).getJSuger().trim());	
				}
				
				if(list.get(i).getJIce().trim().equals("null")){
					pstmt.setString(10,"");	
				}else{
					pstmt.setString(10, list.get(i).getJIce().trim());	
				}
				pstmt.executeQuery(); 
			}
			//System.out.println(totalMoney);
			//System.out.println(userId);
			pstmt = conn.prepareStatement("UPDATE USER_DATA SET MONEY=MONEY - ?  WHERE USER_ID= ?");
			pstmt.setInt(1,totalMoney);
			pstmt.setString(2, userId);
			pstmt.executeQuery();
			conn.commit();
			close();
			return "ok";		
			//pstmt.executeUpdate();
	}


		private void close() throws Exception {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}

	
}  