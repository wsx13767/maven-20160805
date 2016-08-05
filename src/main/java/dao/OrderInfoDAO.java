package dao;
import model.*;

import java.sql.*;

public class OrderInfoDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public OrderInfoDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public BuildOrder createObject(ResultSet rs) throws Exception{
		BuildOrder buildOrder = new BuildOrder();
		UserData userData = new UserData();
		userData.setAccount(rs.getString("ACCOUNT"));
		buildOrder.setOrderNumber(rs.getString("ORDER_NUMBER"));
		buildOrder.setItemInformation(rs.getString("ITEM_INFORMATION"));
		buildOrder.setBuildTimeString(rs.getString("NBUILD_TIME"));
		buildOrder.setDeadlineString(rs.getString("NDEADLINE"));
		buildOrder.setDeadline(rs.getDate("DEADLINE"));
		buildOrder.setRemark(rs.getString("REMARK"));		
		buildOrder.setBuildState(rs.getString("STATE"));
		buildOrder.setUserData(userData);
		return buildOrder;
	}

	public BuildOrder findById(String order_number) throws Exception{
		BuildOrder buildOrder = null;
		String sql = "select user_data.account, build_order.order_number, build_order.item_information, to_char(build_order.build_time, 'yyyy-mm-dd hh24:mi:ss') \"NBUILD_TIME\", to_char(build_order.deadline, 'yyyy-mm-dd hh24:mi:ss') \"NDEADLINE\", build_order.remark, decode(build_order.build_state,null,' ','live','發佈中','dead','已截止','已完結') \"STATE\",build_order.deadline "
					+"from build_order, user_data "
					+"where build_order.order_number = ? "
					+"and user_data.user_id = build_order.user_id";
		pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order_number);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			buildOrder=createObject(rs);
		}
		close();
		return buildOrder;
	}

	private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
}
