package by.pvt.module3.dao;

import by.pvt.module3.connectpool.DBConnectionPool;
import by.pvt.module3.managers.SqlManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
	protected static DBConnectionPool poolInstance;
	protected static SqlManager sqlManager;

	protected DAO() {
		poolInstance = DBConnectionPool.getInstance();
		sqlManager = SqlManager.getInstance();
	}

	protected Integer getLastID(Connection conn)
	{
		String query = sqlManager.getProperty(SqlManager.SQL_GET_LAST_ID);
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			if(result.next())
				return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
