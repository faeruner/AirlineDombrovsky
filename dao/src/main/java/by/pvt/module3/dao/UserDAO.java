package by.pvt.module3.dao;

import java.sql.SQLException;
import by.pvt.module3.entity.User;
import by.pvt.module3.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO extends DAO {
	private static UserDAO instance;

	private UserDAO() {
		super();
	}

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public User getUserByLogin(String login) throws SQLException {
		Session session = null;
		User entity = null;
		try {
			session = HibernateUtil.getSesson();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq(User.LOGIN, login));
			session.beginTransaction();
			entity = (User) criteria.uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			log.error(e);
		}
		return entity;
	}
}
