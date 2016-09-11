package by.pvt.module3.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO{
	protected static final Logger log = LogManager.getRootLogger();
	protected DAO() {
	}
}
