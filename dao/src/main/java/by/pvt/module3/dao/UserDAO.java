package by.pvt.module3.dao;

import java.sql.SQLException;
import by.pvt.module3.entity.User;
import by.pvt.module3.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDAO extends CommonDAO<User> {
	public UserDAO() {
		super(User.class, HibernateUtil.getSesson());
	}

	public User getUserByLogin(String login) throws SQLException {
		Session session = HibernateUtil.getSesson();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq(User.LOGIN, login));
		Transaction transaction = session.beginTransaction();
		try {
			User entity = (User) criteria.uniqueResult();
			transaction.commit();
			return entity;
		} catch (Exception e) {
			transaction.rollback();
			log.error(e);
		}
		return null;
	}
}
