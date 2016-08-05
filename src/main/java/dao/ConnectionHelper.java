package dao;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class ConnectionHelper {
	public static Connection getConnection() throws Exception  {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/G");
		Connection conn = ds.getConnection();
		return conn;
	}
}
