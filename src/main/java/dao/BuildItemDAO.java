package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class BuildItemDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public BuildItemDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	


	//訂單編號
	public int getOrderNumber() throws Exception{
	    pstmt = conn.prepareStatement("SELECT max(TO_NUMBER(ORDER_NUMBER)) FROM BUILD_ORDER");
	    rs = pstmt.executeQuery();
	    rs.next();
	    int a=rs.getInt(1);
	    System.out.println(a);
	    int ordernumber=rs.getInt(1)+1;
	    System.out.println(ordernumber);
	    return ordernumber;
	  }
	 //商品編號
	 public int getItemNumber() throws Exception{
		  pstmt = conn.prepareStatement("SELECT max(TO_NUMBER(ITEM_NUMBER)) FROM ITEM_INFORMATION");
		  rs = pstmt.executeQuery();
		  rs.next();
		  int b=rs.getInt(1);
		  System.out.println(b);
		  int itemnumber=rs.getInt(1)+1;
		  System.out.println(itemnumber);
		  return itemnumber;
	  }	
	 
	 
	 
	 
	 	//取得現在時間
		private String getCurrentDate(){
			//設定日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date today = new java.util.Date();
			System.out.println(sdf.format(today));
			return sdf.format(today);
			}
		
		
	 //食品訂單建立
    public int BuildOrder(String remark,String itemInformation,String deadline,String name,String max,String money,String min,String userId,String picture) throws Exception{
    	//BUILD_ORDER資料填入
    	String sql = "INSERT INTO SSD.BUILD_ORDER (ITEM_INFORMATION,ORDER_NUMBER,REMARK,DEADLINE,BUILD_TIME,BUILD_STATE,USER_ID,PICTURE) VALUES(?,?,?,to_date(?,'YYYY-MM-DD hh24:mi'),to_date(?,'YYYY-MM-DD hh24:mi:ss'),'live',?,?)";
    	int ordernumber = getOrderNumber();
    	int itemnumber = getItemNumber();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,remark);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,itemInformation);
    	pstmt.setString(4,deadline);
    	pstmt.setString(5, getCurrentDate());
    	pstmt.setString(6,userId);
    	pstmt.setString(7, picture);
    	rs = pstmt.executeQuery();
    	//ITEM_INFORMATION資料填入
    	String sql2 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql2);
    	pstmt.setInt(1, itemnumber);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();
    	if(rs.next()){close(); return 1;}
    	else{close(); return 0;}
    }
    
    //食物訂單2
    public int BuildOrder2(String remark,String itemInformation,String deadline,String name,String max,String money,String min,String userId,String name2,String money2,String picture) throws Exception{
    	//BUILD_ORDER資料填入
    	String sql ="INSERT INTO SSD.BUILD_ORDER (ITEM_INFORMATION,ORDER_NUMBER,REMARK,DEADLINE,BUILD_TIME,BUILD_STATE,USER_ID,PICTURE) VALUES(?,?,?,to_date(?,'YYYY-MM-DD hh24:mi'),to_date(?,'YYYY-MM-DD hh24:mi:ss'),'live',?,?)";
    	int ordernumber = getOrderNumber();
    	int itemnumber = getItemNumber();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,remark);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,itemInformation);
    	pstmt.setString(4,deadline);
    	pstmt.setString(5,getCurrentDate());
    	pstmt.setString(6,userId);
    	pstmt.setString(7, picture);
    	rs = pstmt.executeQuery();
    	//ITEM_INFORMATION資料填入
    	String sql2 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql2);
    	pstmt.setInt(1, itemnumber);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();
    	
    	String sql3 ="INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql3);
    	pstmt.setInt(1, itemnumber+1);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name2);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money2);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();	
    	if(rs.next()){close(); return 1;}
    	else{close(); return 0;}
    }
  //食物訂單3
    public int BuildOrder3(String remark,String itemInformation,String deadline,String name,String max,String money,String min,String userId,String name2,String money2,String name3,String money3,String picture) throws Exception{
    	//BUILD_ORDER資料填入
    	String sql = "INSERT INTO SSD.BUILD_ORDER (ITEM_INFORMATION,ORDER_NUMBER,REMARK,DEADLINE,BUILD_TIME,BUILD_STATE,USER_ID) VALUES(?,?,?,to_date(?,'YYYY-MM-DD hh24:mi'),to_date(?,'YYYY-MM-DD hh24:mi:ss'),'live',?)";
    	int ordernumber = getOrderNumber();
    	int itemnumber = getItemNumber();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,remark);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,itemInformation);
    	pstmt.setString(4,deadline);
    	pstmt.setString(5, getCurrentDate());
    	pstmt.setString(6,userId);
    	rs = pstmt.executeQuery();
    	//ITEM_INFORMATION資料填入
    	String sql2 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql2);
    	pstmt.setInt(1, itemnumber);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();
    	
    	String sql3 ="INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql3);
    	pstmt.setInt(1, itemnumber+1);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name2);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money2);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();	
    	String sql4 ="INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql4);
    	pstmt.setInt(1, itemnumber+2);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name3);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money3);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();	
    	if(rs.next()){close(); return 1;}
    	else{close(); return 0;}
    }
    
  //食物訂單4
    public int BuildOrder4(String remark,String itemInformation,String deadline,String name,String max,String money,String min,String userId,String name2,String money2,String name3,String money3,String name4,String money4,String picture) throws Exception{
    	//BUILD_ORDER資料填入
    	String sql = "INSERT INTO SSD.BUILD_ORDER (ITEM_INFORMATION,ORDER_NUMBER,REMARK,DEADLINE,BUILD_TIME,BUILD_STATE,USER_ID,PICTURE) VALUES(?,?,?,to_date(?,'YYYY-MM-DD hh24:mi'),to_date(?,'YYYY-MM-DD hh24:mi:ss'),'live',?,?)";
    	int ordernumber = getOrderNumber();
    	int itemnumber = getItemNumber();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,remark);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,itemInformation);
    	pstmt.setString(4,deadline);
    	pstmt.setString(5,getCurrentDate());
    	pstmt.setString(6,userId);
    	pstmt.setString(7, picture);
    	rs = pstmt.executeQuery();
    	//ITEM_INFORMATION資料填入
    	String sql2 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql2);
    	pstmt.setInt(1, itemnumber);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();
    	
    	String sql3 ="INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql3);
    	pstmt.setInt(1, itemnumber+1);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name2);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money2);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();	
    	
    	String sql4 ="INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql4);
    	pstmt.setInt(1, itemnumber+2);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name3);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money3);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();	
    	
    	String sql5 ="INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,MONEY,MIN,USER_ID) VALUES(?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql5);
    	pstmt.setInt(1, itemnumber+3);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name4);
    	pstmt.setString(4, max);
    	pstmt.setString(5,money4);
    	pstmt.setString(6, min);
    	pstmt.setString(7, userId);
    	rs = pstmt.executeQuery();	
    	if(rs.next()){close(); return 1;}
    	else{close(); return 0;}
    }

    
    //飲品訂單建立
    public int BuildOrderDrink(String itemInformation,String remark,String deadline,String userId,String name,String max,String bigMoney,String midMoney,String smallMoney,String min,String ice,String sugar,String size,String picture) throws Exception{
    	//BUILD_ORDER資料填入
    	String sql = "INSERT INTO SSD.BUILD_ORDER (ITEM_INFORMATION,ORDER_NUMBER,REMARK,DEADLINE,BUILD_TIME,BUILD_STATE,USER_ID,PICTURE) VALUES(?,?,?,to_date(?,'YYYY-MM-DD hh24:mi'),to_date(?,'YYYY-MM-DD hh24:mi:ss'),'live',?,?)";
    	int ordernumber = getOrderNumber();
    	int itemnumber = getItemNumber();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,remark);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,itemInformation);
    	pstmt.setString(4,deadline);
    	pstmt.setString(5, getCurrentDate());
    	pstmt.setString(6,userId);
    	pstmt.setString(7, picture);
    	rs = pstmt.executeQuery();
    	//ITEM_INFORMATION資料填入
    	String sql2 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql2);
    	pstmt.setInt(1, itemnumber);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name);
    	pstmt.setString(4, max);
    	pstmt.setString(5,bigMoney);
    	pstmt.setString(6,midMoney);
    	pstmt.setString(7,smallMoney);
    	pstmt.setString(8, min);
    	pstmt.setString(9, userId);
    	pstmt.setString(10, ice);
    	pstmt.setString(11, sugar);
    	pstmt.setString(12, size);
    	rs = pstmt.executeQuery();
    	if(rs.next()){close(); return 1;}
    	else{close(); return 0;}
    }
    //飲料訂單2
    public int BuildOrderDrink2(String itemInformation,String remark,String deadline,String userId,String name,String max,String bigMoney,String midMoney,String smallMoney,String min,String ice,String sugar,String size,String name2,String bigMoney2,String midMoney2,String smallMoney2,String ice2,String sugar2,String size2,String picture) throws Exception{
    	//BUILD_ORDER資料填入
    	String sql = "INSERT INTO SSD.BUILD_ORDER (ITEM_INFORMATION,ORDER_NUMBER,REMARK,DEADLINE,BUILD_TIME,BUILD_STATE,USER_ID,PICTURE) VALUES(?,?,?,to_date(?,'YYYY-MM-DD hh24:mi'),to_date(?,'YYYY-MM-DD hh24:mi:ss'),'live',?,?)";
    	int ordernumber = getOrderNumber();
    	int itemnumber = getItemNumber();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,remark);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,itemInformation);
    	pstmt.setString(4,deadline);
    	pstmt.setString(5, getCurrentDate());
    	pstmt.setString(6,userId);
    	pstmt.setString(7, picture);
    	rs = pstmt.executeQuery();
    	//ITEM_INFORMATION資料填入
    	String sql2 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql2);
    	pstmt.setInt(1, itemnumber);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name);
    	pstmt.setString(4, max);
    	pstmt.setString(5,bigMoney);
    	pstmt.setString(6,midMoney);
    	pstmt.setString(7,smallMoney);
    	pstmt.setString(8, min);
    	pstmt.setString(9, userId);
    	pstmt.setString(10, ice);
    	pstmt.setString(11, sugar);
    	pstmt.setString(12, size);
    	rs = pstmt.executeQuery();
    		
    	
       	String sql3 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    	pstmt = conn.prepareStatement(sql3);
    	pstmt.setInt(1, itemnumber+1);
    	pstmt.setInt(2,ordernumber);
    	pstmt.setString(3,name2);
    	pstmt.setString(4, max);
    	pstmt.setString(5,bigMoney2);
    	pstmt.setString(6,midMoney2);
    	pstmt.setString(7,smallMoney2);
    	pstmt.setString(8, min);
    	pstmt.setString(9, userId);
    	pstmt.setString(10, ice2);
    	pstmt.setString(11, sugar2);
    	pstmt.setString(12, size2);
    	rs = pstmt.executeQuery();
    	if(rs.next()){close(); return 1;}
    	else{close(); return 0;}
    }   
	   
	
	
	//飲料訂單3
	
		public int BuildOrderDrink3(String itemInformation,String remark,String deadline,String userId,String name,String max,String bigMoney,String midMoney,String smallMoney,String min,String ice,String sugar,String size,String name2,String bigMoney2,String midMoney2,String smallMoney2,String ice2,String sugar2,String size2,String name3,String bigMoney3,String midMoney3,String smallMoney3,String ice3,String sugar3,String size3,String picture) throws Exception{
	    	//BUILD_ORDER資料填入
	    	String sql = "INSERT INTO SSD.BUILD_ORDER (ITEM_INFORMATION,ORDER_NUMBER,REMARK,DEADLINE,BUILD_TIME,BUILD_STATE,USER_ID,PICTURE) VALUES(?,?,?,to_date(?,'YYYY-MM-DD hh24:mi'),to_date(?,'YYYY-MM-DD hh24:mi:ss'),'live',?,?)";
	    	int ordernumber = getOrderNumber();
	    	int itemnumber = getItemNumber();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1,remark);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,itemInformation);
	    	pstmt.setString(4,deadline);
	    	pstmt.setString(5, getCurrentDate());
	    	pstmt.setString(6,userId);
	    	pstmt.setString(7, picture);
	    	rs = pstmt.executeQuery();
	    	//ITEM_INFORMATION資料填入
	    	String sql2 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	    	pstmt = conn.prepareStatement(sql2);
	    	pstmt.setInt(1, itemnumber);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,name);
	    	pstmt.setString(4, max);
	    	pstmt.setString(5,bigMoney);
	    	pstmt.setString(6,midMoney);
	    	pstmt.setString(7,smallMoney);
	    	pstmt.setString(8, min);
	    	pstmt.setString(9, userId);
	    	pstmt.setString(10, ice);
	    	pstmt.setString(11, sugar);
	    	pstmt.setString(12, size);
	    	rs = pstmt.executeQuery();
	    		
	    	
	       	String sql3 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	    	pstmt = conn.prepareStatement(sql3);
	    	pstmt.setInt(1, itemnumber+1);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,name2);
	    	pstmt.setString(4, max);
	    	pstmt.setString(5,bigMoney2);
	    	pstmt.setString(6,midMoney2);
	    	pstmt.setString(7,smallMoney2);
	    	pstmt.setString(8, min);
	    	pstmt.setString(9, userId);
	    	pstmt.setString(10, ice2);
	    	pstmt.setString(11, sugar2);
	    	pstmt.setString(12, size2);
	    	rs = pstmt.executeQuery();
	    	
	    	
	    	
	       	String sql4 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	    	pstmt = conn.prepareStatement(sql4);
	    	pstmt.setInt(1, itemnumber+2);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,name3);
	    	pstmt.setString(4, max);
	    	pstmt.setString(5,bigMoney3);
	    	pstmt.setString(6,midMoney3);
	    	pstmt.setString(7,smallMoney3);
	    	pstmt.setString(8, min);
	    	pstmt.setString(9, userId);
	    	pstmt.setString(10, ice3);
	    	pstmt.setString(11, sugar3);
	    	pstmt.setString(12, size3);
	    	rs = pstmt.executeQuery();
	    	
	    	
	    	
	    	
	    	if(rs.next()){close(); return 1;}
	    	else{close(); return 0;}
	    }   
	//飲料訂單4	   
		public int BuildOrderDrink4(String itemInformation,String remark,String deadline,String userId,String name,String max,String bigMoney,String midMoney,String smallMoney,String min,String ice,String sugar,String size,String name2,String bigMoney2,String midMoney2,String smallMoney2,String ice2,String sugar2,String size2,String name3,String bigMoney3,String midMoney3,String smallMoney3,String ice3,String sugar3,String size3,String name4,String bigMoney4,String midMoney4,String smallMoney4,String ice4,String sugar4,String size4,String picture) throws Exception{
	    	//BUILD_ORDER資料填入
	    	String sql = "INSERT INTO SSD.BUILD_ORDER (ITEM_INFORMATION,ORDER_NUMBER,REMARK,DEADLINE,BUILD_TIME,BUILD_STATE,USER_ID,PICTURE) VALUES(?,?,?,to_date(?,'YYYY-MM-DD hh24:mi'),to_date(?,'YYYY-MM-DD hh24:mi:ss'),'live',?,?)";
	    	int ordernumber = getOrderNumber();
	    	int itemnumber = getItemNumber();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setString(1,remark);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,itemInformation);
	    	pstmt.setString(4,deadline);
	    	pstmt.setString(5, getCurrentDate());
	    	pstmt.setString(6,userId);
	    	pstmt.setString(7, picture);
	    	rs = pstmt.executeQuery();
	    	//ITEM_INFORMATION資料填入
	    	String sql2 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	    	pstmt = conn.prepareStatement(sql2);
	    	pstmt.setInt(1, itemnumber);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,name);
	    	pstmt.setString(4, max);
	    	pstmt.setString(5,bigMoney);
	    	pstmt.setString(6,midMoney);
	    	pstmt.setString(7,smallMoney);
	    	pstmt.setString(8, min);
	    	pstmt.setString(9, userId);
	    	pstmt.setString(10, ice);
	    	pstmt.setString(11, sugar);
	    	pstmt.setString(12, size);
	    	rs = pstmt.executeQuery();
	    		
	    	
	       	String sql3 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	    	pstmt = conn.prepareStatement(sql3);
	    	pstmt.setInt(1, itemnumber+1);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,name2);
	    	pstmt.setString(4, max);
	    	pstmt.setString(5,bigMoney2);
	    	pstmt.setString(6,midMoney2);
	    	pstmt.setString(7,smallMoney2);
	    	pstmt.setString(8, min);
	    	pstmt.setString(9, userId);
	    	pstmt.setString(10, ice2);
	    	pstmt.setString(11, sugar2);
	    	pstmt.setString(12, size2);
	    	rs = pstmt.executeQuery();
	    	
	    	
	    	
	       	String sql4 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	    	pstmt = conn.prepareStatement(sql4);
	    	pstmt.setInt(1, itemnumber+2);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,name3);
	    	pstmt.setString(4, max);
	    	pstmt.setString(5,bigMoney3);
	    	pstmt.setString(6,midMoney3);
	    	pstmt.setString(7,smallMoney3);
	    	pstmt.setString(8, min);
	    	pstmt.setString(9, userId);
	    	pstmt.setString(10, ice3);
	    	pstmt.setString(11, sugar3);
	    	pstmt.setString(12, size3);
	    	rs = pstmt.executeQuery();
	    	
	       	String sql5 = "INSERT INTO SSD.ITEM_INFORMATION (ITEM_NUMBER,ORDER_NUMBER,NAME,MAX,BIG_PRICE,MID_PRICE,SMALL_PRICE,MIN,USER_ID,ICE,SUGER,\"SIZE\") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	    	pstmt = conn.prepareStatement(sql5);
	    	pstmt.setInt(1, itemnumber+3);
	    	pstmt.setInt(2,ordernumber);
	    	pstmt.setString(3,name4);
	    	pstmt.setString(4, max);
	    	pstmt.setString(5,bigMoney4);
	    	pstmt.setString(6,midMoney4);
	    	pstmt.setString(7,smallMoney4);
	    	pstmt.setString(8, min);
	    	pstmt.setString(9, userId);
	    	pstmt.setString(10, ice4);
	    	pstmt.setString(11, sugar4);
	    	pstmt.setString(12, size4);
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
		//創一item 物件
//		public ItemInformation createObject(ResultSet rs) throws Exception{
//			ItemInformation itemInformation = new ItemInformation();
//			itemInformation.setName(rs.getString("NAME"));
//			itemInformation.setItemNumber(rs.getString("ITEM_NUMBER"));
//			itemInformation.setMax(rs.getBigDecimal("MAX"));
//			itemInformation.setMin(rs.getBigDecimal("MIN"));
//			itemInformation.setMoney(rs.getBigDecimal("MONEY"));
//			itemInformation.setSuger(rs.getString("SUGER"));
//			itemInformation.setIce(rs.getString("ICE"));
//			itemInformation.setSize(rs.getString("SIZE"));
//			itemInformation.setBigPrice(rs.getBigDecimal("BIG_PRICE"));
//			itemInformation.setMidPrice(rs.getBigDecimal("MID_PRICE"));
//			itemInformation.setSmallPrice(rs.getBigDecimal("SMALL_PRICE"));
//			return itemInformation;
//		}
//	
//		public List<ItemInformation> findBySearch(String search) throws Exception{
//			List<ItemInformation> list= new ArrayList<ItemInformation>();
//			String sql = "select * from ITEM_INFORMATION where NAME LIKE ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%"+search+"%");
//			this.rs = pstmt.executeQuery();
//			while(rs.next()){
//				list.add(createObject(rs)); 
//			}
//			System.out.println(list);
//			close();
//			return list;
//			
//		}
		
		

	
		public SearchFoodModel createObject(ResultSet rs) throws Exception{
			SearchFoodModel searchFoodModel = new SearchFoodModel();
			searchFoodModel.setOrderNumber(rs.getString("ORDER_NUMBER"));
			searchFoodModel.setIteminformation(rs.getString("ITEM_INFORMATION"));
			searchFoodModel.setRemark(rs.getString("REMARK"));
			searchFoodModel.setPicture(rs.getString("PICTURE"));
			searchFoodModel.setName(rs.getString("NAME"));
			searchFoodModel.setMoney(rs.getBigDecimal("MONEY"));
			searchFoodModel.setMax(rs.getBigDecimal("MAX"));
			searchFoodModel.setMin(rs.getBigDecimal("MIN"));
			searchFoodModel.setBigprice(rs.getBigDecimal("big_price"));
			searchFoodModel.setMidprice(rs.getBigDecimal("mid_price"));
			searchFoodModel.setSmallprice(rs.getBigDecimal("small_price"));
			searchFoodModel.setIce(rs.getString("ice"));
			searchFoodModel.setSuger(rs.getString("suger"));
			searchFoodModel.setSize(rs.getString("SIZE"));
			return searchFoodModel;
		}
	
		public List<SearchFoodModel> findBySearch(String search)throws Exception{
			List<SearchFoodModel> list = new ArrayList<SearchFoodModel>();
			String sql = "select a.order_number,a.remark,a.item_information,a.picture,b.NAME,b.max,b.min,b.money,b.big_price,b.mid_price,b.small_price,b.suger,b.ice,b.\"SIZE\"" +
					"from build_order a,item_information b " +
					"where b.name like ? and a.order_number=b.order_number and money IS NOT NULL order by a.item_information";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			this.rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(createObject(rs)); 
			}
			close();
			return list;
		}
		
		
		public List<SearchFoodModel> findBySearch2(String search)throws Exception{
			List<SearchFoodModel> list = new ArrayList<SearchFoodModel>();
			String sql = "select a.order_number,a.remark,a.item_information,a.picture,b.NAME,b.max,b.min,b.money,b.big_price,b.mid_price,b.small_price,b.suger,b.ice,b.\"SIZE\"" +
					"from build_order a,item_information b " +
					"where b.name like ? and a.order_number=b.order_number and money IS  NULL order by a.item_information";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			this.rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(createObject(rs)); 
			}
			close();
			return list;
		}
		
		
		
		
		
		public List<SearchFoodModel> findByOrdernumber(int ordernumber)throws Exception{
			List<SearchFoodModel> list = new ArrayList<SearchFoodModel>();
			String sql = "SELECT * FROM ITEM_INFORMATION WHERE ORDER_NUMBER = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ordernumber);
			this.rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(createObject(rs)); 
			}
			close();
			return list;
		}
	
	
	
	
	
}
