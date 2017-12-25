package db;

import java.sql.Connection;

public interface DBConnection {
	public Connection makeConnection();
	public void initConnect();
}
