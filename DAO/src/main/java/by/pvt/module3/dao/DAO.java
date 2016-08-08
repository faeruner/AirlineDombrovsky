package by.pvt.module3.dao;

import by.pvt.module3.connectpool.DBConnectionPool;
import by.pvt.module3.managers.SqlManager;

public abstract class DAO {
	protected static DBConnectionPool poolInstance;
	protected static SqlManager sqlManager;

	protected DAO() {
		poolInstance = DBConnectionPool.getInstance();
		sqlManager = SqlManager.getInstance();
	}
}
