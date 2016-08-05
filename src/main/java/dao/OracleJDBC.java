package dao;

import java.sql.*;  
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.time.DateFormatUtils;
class OracleJDBC{  
	
	
	private static java.sql.Date getCurrentDate() throws ParseException {
		//欲轉換的日期字串
		String dateString = "20010-03-02 20:25:58";
		//設定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	
public static void main(String args[]){  
	//-------------------------------這是一個DEBUG用的物件
try{  
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","SSD","SSD");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();  
PreparedStatement pstmt = null;
//step4 execute query  
String sql="SELECT BUILD_ORDER.ORDER_NUMBER,BUILD_ORDER.DEADLINE,ITEM_INFORMATION.MONEY,BUILD_ORDER.ITEM_INFORMATION,ITEM_INFORMATION.NAME,ITEM_INFORMATION.MAX,USER_DATA.ACCOUNT,JOIN_ORDER.COUNT,BUILD_ORDER.BUILD_TIME,ITEM_INFORMATION.BIG_PRICE,ITEM_INFORMATION.MID_PRICE,ITEM_INFORMATION.SMALL_PRICE\n"+
		"FROM ITEM_INFORMATION,BUILD_ORDER,USER_DATA,JOIN_ORDER\n"+
		"WHERE ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER\n"+
		"AND ITEM_INFORMATION.USER_ID=BUILD_ORDER.USER_ID\n"+
		"AND BUILD_ORDER.USER_ID=USER_DATA.USER_ID\n"+
		"AND BUILD_ORDER.BUILD_STATE='live' "+
		"AND ITEM_INFORMATION.ITEM_NUMBER=JOIN_ORDER.ITEM_NUMBER(+)";


//SELECT   JOIN_ORDER.ITEM_NUMBER,JOIN_ORDER.ORDER_NUMBER,JOIN_ORDER.COUNT,JOIN_ORDER.JOIN_TIME,ITEM_INFORMATION.MONEY,BUILD_ORDER.ITEM_INFORMATION,BUILD_ORDER.DEADLINE,USER_DATA.ACCOUNT FROM   ITEM_INFORMATION , JOIN_ORDER ,BUILD_ORDER,USER_DATA WHERE  ITEM_INFORMATION.ORDER_NUMBER=BUILD_ORDER.ORDER_NUMBER AND  JOIN_ORDER.ITEM_NUMBER=ITEM_INFORMATION.ITEM_NUMBER AND   BUILD_ORDER.USER_ID=USER_DATA.USER_ID  AND   ITEM_INFORMATION.ORDER_NUMBER=1  ORDER BY JOIN_ORDER.ORDER_NUMBER ,ITEM_INFORMATION.MONEY









         
          
         


ResultSet rs=stmt.executeQuery(sql);  
ResultSetMetaData rsmd = rs.getMetaData();
int columnCount;
String name="";
String type="";
columnCount=rsmd.getColumnCount();
System.out.println("欄位數目: "+columnCount);
//查看資料名稱
System.out.println("SQL指令:  "+sql);
for (int i = 1; i <= columnCount; i++ ) {
	name = rsmd.getColumnName(i);
	type=rsmd.getColumnTypeName(i);
	System.out.printf("欄位名稱: %s\t\t資料型別:%s \n ",name,type);
	}
System.out.println("查詢結果:  ");
//int z=rs.getInt(1);
//System.out.print(z);
while(rs.next())  {
	System.out.print(rs.getString(2));
	System.out.println(rs.getString(1));
}
while(rs.next())  {
	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getDate(4)+"  "+rs.getInt(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8)+"  "+rs.getString(9)+"  "+rs.getString(10)+"  "+rs.getString(11)+"  "+rs.getString(12)+"  "+rs.getString(13));  

	//日期格式轉換
	Date date=rs.getDate("DEADLINE");
	SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
	formatter.format(date);
	System.out.println("轉換後的日期:"+date);

}

//step5 close the connection object  
con.close();  
  
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}  