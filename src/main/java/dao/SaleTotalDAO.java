package dao;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleTotalDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public SaleTotalDAO() throws Exception{
		this.conn = ConnectionHelper.getConnection();
		this.stmt = conn.createStatement();
	}
	
	public SaleTotal createObject(ResultSet rs) throws Exception{
		SaleTotal salTotal = new SaleTotal();
		
		salTotal.setName(rs.getString("NAME"));
		salTotal.setPrice(rs.getBigDecimal("PRICE"));
		salTotal.setCount(rs.getInt("COUNT"));
		
		return salTotal;
	}

	public List<SaleTotal> findByOrderNumber(String order_number) throws Exception{
		List<SaleTotal> list = new ArrayList<SaleTotal>();
		String sql = "select item_information.name || decode(join_order.\"SIZE\",'b','(大杯)','m','(中杯)','s','(小杯)',' ') as name, (join_order.money/join_order.count) price, sum(join_order.count) count "
					+"from item_information, join_order "
					+"where join_order.order_number = ? "
					+"and item_information.order_number = ? "
					+"and item_information.item_number(+) = join_order.item_number "
					+"group by item_information.name || decode(join_order.\"SIZE\",'b','(大杯)','m','(中杯)','s','(小杯)',' '), item_information.item_number, (join_order.money/join_order.count) "
					+"order by item_information.item_number";
		pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, order_number);
        pstmt.setString(2, order_number);
		this.rs = pstmt.executeQuery();
		while(rs.next()){
			list.add(createObject(rs));
		}
		close();
		return list;
	}

	private void close() throws Exception {
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
	}
}
